package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import message.MessageDemConnexion;
import message.MessageGroupe;
import message.MessageMessageConversation;
import message.MessageReponseConnexion;
import message.MessageTicket;
import modele.EtatMessage;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class Serveur {
	private int port = 1705;
	private int nbThreadsTraitement = 3;
	private Map<Utilisateur, Socket> mapUtilisateurConnexion = new HashMap<>();
	private Queue<AssocMessageSocket> messagessATraiter = new LinkedList<AssocMessageSocket>();
	private BaseDeDonnees bdd;

	Serveur() {
		initServeur();
	}

	Serveur(int port) {
		this.port = port;
		initServeur();
	}

	// utiliser port = -1 pour port par défaut
	Serveur(int port, int nbThreadsTraitement) {
		if (port != -1)
			this.port = port;
		this.nbThreadsTraitement = nbThreadsTraitement;
		initServeur();
	}

	private void initServeur() {
		try {
			bdd = new BaseDeDonnees("jdbc:mysql://localhost:3306/Projet", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Thread ecoute = new Thread(new EcouteMessagesClients(this));
		ecoute.start();
		for (int i = 0; i < nbThreadsTraitement; i++) {
			Thread traitement = new Thread(new Traitement(this));
			traitement.start();
		}

		connexions();

	}

	private void connexions() {

		for (;;) {
			try {
				ServerSocket s = new ServerSocket(port);
				Socket soc = s.accept();

				ObjectInputStream in = new ObjectInputStream(soc.getInputStream());

				Object objetRecu = in.readObject();
				MessageDemConnexion messRecu = (MessageDemConnexion) objetRecu;
				in.close();

				boolean connexionAcceptee = isConnectionAccepted(messRecu);

				MessageReponseConnexion reponseConnexion;

				if (connexionAcceptee) {
					Utilisateur utilisateur = this.getUtilisateur(messRecu.getIdUtilisateur());
					mapUtilisateurConnexion.put(utilisateur, soc);
					reponseConnexion = new MessageReponseConnexion(true, utilisateur);
				} else {
					reponseConnexion = new MessageReponseConnexion(false, null);
				}

				ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
				out.writeObject(reponseConnexion);
				out.flush();

				List<Ticket> tousLesTickets = ticketsUtilisateur(messRecu.getIdUtilisateur(), soc);
				for (Ticket t : tousLesTickets) {
					out.writeObject(new MessageTicket(t, false));
					out.flush();
				}

				List<Groupe> groupes = groupesUtilisateur(messRecu.getIdUtilisateur());
				for (Groupe g : groupes) {
					out.writeObject(new MessageGroupe(g));
					out.flush();
				}

				out.close();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public boolean connexionServeur(String idUtilisateur, String mdp) {
		ResultSet res;
		try {
			res = requeteBDD(
					"SELECT COUNT(*) FROM administrateur WHERE idA = '" + idUtilisateur + "' AND mdp = '" + mdp + "'");
			res.first();
			return res.getInt(1) > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	List<Groupe> groupesUtilisateur(String idUtilisateur) {
		List<Groupe> groupes = new ArrayList<>();
		ResultSet res;
		try {
			res = requeteBDD("SELECT nomG FROM appartenir WHERE idU = '" + idUtilisateur + "'");
			for (; res.next();) {
				groupes.add(new Groupe(res.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return groupes;
	}

	private List<Ticket> ticketsUtilisateur(String idUtilisateur, Socket s) {
		List<Ticket> tousLesTickets = new ArrayList<>();
		try {
			ResultSet res = requeteBDD("SELECT idT,createur FROM participer WHERE idU = '" + idUtilisateur + "'");

			for (; res.next();) {
				Ticket ticket = buildTicket(res.getInt(1), res.getString(2), s, idUtilisateur);
				tousLesTickets.add(ticket);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tousLesTickets;
	}

	private Ticket buildTicket(int idTicket, String createur, Socket s, String idUtilisateur) {
		ResultSet res;
		Ticket ticket = null;

		try {
			res = requeteBDD("SELECT titre FROM ticket WHERE idT = " + idTicket);
			res.first();
			String nom = res.getString(1);
			String nomGroupe = nomGroupeFromIdTicket(idTicket);

			ticket = new Ticket(idTicket, nom, getUtilisateur(createur), getGroupeFromNomGroupe(nomGroupe), null);

			res = requeteBDD("SELECT idM, texte, dateM, idU WHERE idT = " + idTicket);

			Set<String> participants = getParticipantsTickets(idTicket);
			int nombreParticipants = participants.size();

			for (; res.next();) {
				int idM = res.getInt(1);
				Utilisateur auteur = getUtilisateur(res.getString(4));
				Date date = res.getDate(3);
				String texte = res.getString(2);
				EtatMessage etatMessage;
				boolean luParUtilisateur;

				boolean changementEtatReceptionU = false;
				ResultSet receiveU = requeteBDD(
						"SELECT COUNT(*) FROM recevoir WHERE idM = " + idM + " AND idU = '" + idUtilisateur + "'");
				receiveU.first();
				if (receiveU.getInt(1) == 0) {
					requeteBDD("INSERT INTO recevoir (idM, idU) VALUES (" + idM + ", '" + idUtilisateur + "')");
					changementEtatReceptionU = true;
				}

				ResultSet r = requeteBDD("SELECT COUNT(*) FROM recevoir WHERE idM = " + idM);

				r.first();
				if (r.getInt(1) == nombreParticipants) {
					if (changementEtatReceptionU) {
						etatMessage = EtatMessage.NON_LU_PAR_TOUS;
						for (String idU : participants) {
							if (isConnected(new Utilisateur("", "", idU)) && idU != idUtilisateur) {
								r = requeteBDD(
										"SELECT COUNT(*) FROM lire WHERE idM = " + idM + " AND idU = '" + idU + "'");
								r.first();
								boolean luParticipant = false;
								if (r.getInt(1) != 0)
									luParticipant = true;
								Socket socket = mapUtilisateurConnexion.get(new Utilisateur("", "", idU));
								ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
								out.writeObject(new MessageMessageConversation(idTicket, new MessageConversation(idM,
										auteur, texte, date, EtatMessage.NON_LU_PAR_TOUS, luParticipant)));
								out.flush();
							}
						}
					} else {
						r = requeteBDD("SELECT COUNT(*) FROM lire WHERE idM = " + idM);
						r.first();
						if (r.getInt(1) == nombreParticipants)
							etatMessage = EtatMessage.LU_PAR_TOUS;
						else
							etatMessage = EtatMessage.NON_LU_PAR_TOUS;
					}
				} else
					etatMessage = EtatMessage.NON_RECU_PAR_TOUS;

				if (etatMessage == EtatMessage.LU_PAR_TOUS)
					luParUtilisateur = true;
				else {
					r = requeteBDD("SELECT COUNT(*) FROM lire WHERE idM = " + idM + " AND idU = '" + createur + "'");
					r.first();
					if (r.getInt(1) == 0)
						luParUtilisateur = false;
					else
						luParUtilisateur = true;
				}

				MessageConversation m = new MessageConversation(idM, auteur, texte, date, etatMessage,
						luParUtilisateur);
				ticket.ajouterMessage(m);
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ticket;
	}

	Groupe getGroupeFromNomGroupe(String nomGroupe) {
		Groupe groupe = new Groupe(nomGroupe);

		try {
			ResultSet setIdU = requeteBDD("SELECT idU FROM appartenir WHERE nomG = '" + nomGroupe + "'");

			for (; setIdU.next();) {
				String idU = setIdU.getString(1);
				ResultSet res = requeteBDD("SELECT nom,prenom FROM utilisateur WHERE idU = '" + idU + "'");
				res.first();
				groupe.ajouterUtilisateurs(new Utilisateur(res.getString("nom"), res.getString("prenom"), idU));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return groupe;
	}

	String nomGroupeFromIdTicket(int idTicket) {
		String idGroupe = "";
		try {
			ResultSet res = requeteBDD("SELECT nomG FROM participer WHERE idT = " + idTicket);
			if (res != null && res.first())
				idGroupe = res.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idGroupe;
	}

	private boolean isConnectionAccepted(MessageDemConnexion message) {
		String requete = "SELECT mdp FROM utilisateur WHERE idU = " + message.getIdUtilisateur();
		try {
			ResultSet resultat = this.requeteBDD(requete);
			return resultat.next() && resultat.getObject("mdp").toString().equals(message.getMotDePasse());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Set<String> getGroupes() {
		Set<String> groupes = new HashSet<>();
		ResultSet res;
		try {
			res = requeteBDD("SELECT nomG FROM groupe");
			for (; res.next();)
				groupes.add(res.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return groupes;
	}
	
	public Set<Utilisateur> getUtilisateurs(){
		Set<Utilisateur> utilisateurs = new HashSet<>();
		try {
			ResultSet res = requeteBDD("SELECT idU FROM utilisateur");
			for( ; res.next() ; )
				utilisateurs.add(getUtilisateur(res.getString(1)));
			return utilisateurs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return utilisateurs;
	}
	
	public ResultSet requeteBDD(String requete) throws SQLException {
		Pattern insert = Pattern.compile("^INSERT INTO *");
		Matcher insertM = insert.matcher(requete);
		if (insertM.find())
			return bdd.requeteInsert(requete);
		return bdd.requete(requete);
	}

	Set<String> getParticipantsTickets(int idTicket) {

		Set<String> participants = new HashSet<>();
		try {
			ResultSet res = requeteBDD("SELECT idU,createur FROM participer WHERE idT = " + idTicket);
			if (res.next()) {
				participants.add(res.getString("idU"));
				participants.add(res.getString("createur"));
			}

			for (; res.next();)
				participants.add(res.getString("idU"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return participants;
	}

	Utilisateur getUtilisateur(String idUtilisateur) {
		String requete = "SELECT nom,prenom FROM utilisateur WHERE idU = " + idUtilisateur;
		try {
			ResultSet resultat = this.requeteBDD(requete);
			if (!resultat.next())
				return null;
			String nom = resultat.getObject("nom").toString();
			String prenom = resultat.getObject("prenom").toString();

			return new Utilisateur(nom, prenom, idUtilisateur);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	Map<Utilisateur, Socket> getMapUtilisateurConnexion() {
		return mapUtilisateurConnexion;
	}

	Queue<AssocMessageSocket> getMessagessATraiter() {
		return messagessATraiter;
	}

	BaseDeDonnees getBdd() {
		return bdd;
	}

	public boolean isConnected(Utilisateur utilisateur) {
		return mapUtilisateurConnexion.containsKey(utilisateur);
	}
}
