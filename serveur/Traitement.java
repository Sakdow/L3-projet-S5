package serveur;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import message.Message;
import message.MessageDeconnexion;
import message.MessageNouveauMessageConversation;
import message.MessageTicket;
import modele.EtatMessage;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class Traitement implements Runnable {

	private Serveur serveur;

	public Traitement(Serveur serveur) {
		this.serveur = serveur;
	}

	@Override
	public void run() {
		for (;;) {
			Queue<AssocMessageSocket> messagessATraiter = serveur.getMessagessATraiter();
			AssocMessageSocket assocMessageATraiter = messagessATraiter.poll();
			if (assocMessageATraiter != null) {
				Message message = assocMessageATraiter.getMessage();
				Socket socket = assocMessageATraiter.getSocket();
				traitementMessage(message, socket);
			}
		}
	}

	private void traitementMessage(Message message, Socket socket) {
		// TODO: Traiter le message et le renvoyer sur le bon socket
		if (message instanceof MessageDeconnexion) {
			deconnexionUtilisateur((MessageDeconnexion) message, socket);
		} else if (message instanceof MessageTicket) {
			MessageTicket m = (MessageTicket) message;
			if (!m.getDemande())
				nouveauTicket(m, socket);
		} else if (message instanceof MessageNouveauMessageConversation)
			nouveauMessageConversation((MessageNouveauMessageConversation) message);
	}

	private void deconnexionUtilisateur(MessageDeconnexion message, Socket s) {
		try {
			s.close();
			Utilisateur utilisateur = serveur.getUtilisateur(message.getIdUtilisateur());
			Map<Utilisateur, Socket> mapUtilisateurConnexion = serveur.getMapUtilisateurConnexion();
			mapUtilisateurConnexion.remove(utilisateur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void nouveauTicket(MessageTicket message, Socket s) {
		Ticket ticket = message.getTicket();
		Utilisateur createur = ticket.getCreateur();
		String idCreateur = createur.getIdUtilisateur();
		String idGroupe = ticket.getGroupe().getIdGroupe();

		try {
			ResultSet res = serveur.requeteBDD("INSERT INTO Ticket (titre) VALUES ('" + ticket.getNom() + "')");
			res.first();
			int idTicket = res.getInt(1);
			ticket.setIdTicket(idTicket);

			MessageConversation messageConv = ticket.getFilDiscussion().getEnsembleMessage().first();
			res = serveur.requeteBDD("INSERT INTO Message (texte,dateM,idT, idU) VALUES ('" + messageConv.getTexte()
					+ "'," + new java.sql.Date(new java.util.Date().getTime()) + ", '" + idTicket + "', '" + idCreateur
					+ "')");
			res.first();
			int idMessage = res.getInt(1);

			messageConv.setIdMessage(idMessage);
			messageConv.setLuParUtilisateur(false);

			res = serveur.requeteBDD("SELECT idU FROM appartenir WHERE nomG =" + idGroupe);

			Set<Socket> aEnvoyer = new HashSet<>();
			boolean tousRecus = true;

			for (; res.next();) {
				String idU = res.getString(1);
				serveur.requeteBDD("INSERT INTO participer (idU,idT,nomG,createur) VALUES ('" + idU + "', " + idTicket
						+ ", '" + idGroupe + "', '" + idCreateur + "')");
				Socket sock = serveur.getMapUtilisateurConnexion().get((new Utilisateur("", "", idU)));
				if (sock != null) {
					aEnvoyer.add(sock);
					serveur.requeteBDD("INSERT INTO recevoir (idM, idU) VALUES (" + idMessage + "," + idU + ")");
				} else
					tousRecus = false;
			}

			if (tousRecus)
				messageConv.setEtatGroupe(EtatMessage.NON_LU_PAR_TOUS);
			else
				messageConv.setEtatGroupe(EtatMessage.NON_RECU_PAR_TOUS);

			if (aEnvoyer.contains(s))
				aEnvoyer.remove(s);
			else
				serveur.requeteBDD("INSERT INTO recevoir (idM, idU) VALUES (" + idMessage + "," + idCreateur + ")");

			for (Socket soc : aEnvoyer) {
				ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
				out.writeObject(ticket);
				out.flush();
				out.close();
			}

			messageConv.setLuParUtilisateur(true);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(ticket);
			out.flush();
			out.close();
			serveur.requeteBDD("INSERT INTO lire (idM, idU) VALUES (" + idMessage + "," + idCreateur + ")");

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void nouveauMessageConversation(MessageNouveauMessageConversation message) {
		MessageConversation messageConv = message.getMessageConv();
		Utilisateur createur = messageConv.getCreateur();

		try {
			ResultSet res = serveur.requeteBDD("INSERT INTO Message (texte,dateM,idT, idU) VALUES ('"
					+ messageConv.getTexte() + "'," + new java.sql.Date(new java.util.Date().getTime()) + ", '"
					+ message.getIdTicket() + "', '" + createur.getIdUtilisateur() + "')");
			res.first();
			int idMessage = res.getInt(1);
			
			
			
			Ticket ticket = new Ticket(message.getIdTicket(),createur, idGroupeFromIdTicket(idTicket), null );
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String idGroupeFromIdTicket(int idTicket) {
		String idGroupe = "";
		try {
			ResultSet res = serveur.requeteBDD("SELECT nomG FROM participer WHERE idT = " + idTicket);
			if( res != null && res.first() )
				idGroupe = res.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idGroupe;
	}
}
