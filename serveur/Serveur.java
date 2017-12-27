package serveur;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import message.MessageDemConnexion;
import message.MessageReponseConnexion;
import modele.Utilisateur;

public class Serveur {
	int port = 1705;
	int nbThreadsTraitement = 3;
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
		Thread ecoute = new Thread(new EcouteMessagesClients(mapUtilisateurConnexion, messagessATraiter));
		ecoute.start();
		for (int i = 0; i < nbThreadsTraitement; i++) {
			Thread traitement = new Thread(new Traitement(messagessATraiter));
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
					Utilisateur utilisateur = this.getUtilisateurConnexion(messRecu.getIdUtilisateur());
					mapUtilisateurConnexion.put(utilisateur, soc);
					reponseConnexion = new MessageReponseConnexion(true, utilisateur);
				} else {
					reponseConnexion = new MessageReponseConnexion(false, null);
				}

				ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
				out.writeObject(reponseConnexion);
				out.flush();
				out.close();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	private boolean isConnectionAccepted(MessageDemConnexion message) {
		String requete = "SELECT mdp FROM Utilisateur WHERE idU = " + message.getIdUtilisateur();
		try {
			ResultSet resultat = this.requeteBDD(requete);
			return !resultat.next() || resultat.getObject("mdp").toString().equals(message.getMotDePasse());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet requeteBDD(String requete) throws SQLException {
		return bdd.requete(requete);
	}

	private Utilisateur getUtilisateurConnexion(String idUtilisateur) {
		String requete = "SELECT nom,prenom FROM Utilisateur WHERE idU = " + idUtilisateur;
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
}
