package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Queue;

import message.Message;
import modele.Utilisateur;

public class EcouteMessagesClients implements Runnable {
	Map<Utilisateur, Socket> mapUtilisateurConnexion;
	Queue<AssocMessageSocket> messagessATraiter;

	public EcouteMessagesClients(Map<Utilisateur, Socket> mapUtilisateurConnexion,
			Queue<AssocMessageSocket> messagessATraiter) {
		this.mapUtilisateurConnexion = mapUtilisateurConnexion;
		this.messagessATraiter = messagessATraiter;
	}

	public void run() {
		for (;;) {
			for (Socket s : mapUtilisateurConnexion.values()) {
				// TODO: Gestion de la fermeture prématurée du socket
				ObjectInputStream in;
				try {
					in = new ObjectInputStream(s.getInputStream());
					if (in.available() > 0) {
						Message mess = (Message) in.readObject();
						messagessATraiter.add(new AssocMessageSocket(mess, s));
					}
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
