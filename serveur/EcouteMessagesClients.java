package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import message.Message;
import modele.Utilisateur;

public class EcouteMessagesClients implements Runnable {
	Serveur serveur;

	public EcouteMessagesClients(Serveur serveur) {
		this.serveur = serveur;
	}

	public void run() {
		for (;;) {
			Map<Utilisateur, Socket> mapUtilisateurConnexion = serveur.getMapUtilisateurConnexion();
			Queue<AssocMessageSocket> messagessATraiter = serveur.getMessagessATraiter();
			for (Iterator<Utilisateur> iter = mapUtilisateurConnexion.keySet().iterator() ; iter.hasNext() ; ) {
				Utilisateur u = iter.next();
				Socket s = mapUtilisateurConnexion.get(u);
				// TODO: Gestion de la fermeture prématurée du socket
				ObjectInputStream in;
				try {
					if (s.isInputShutdown() || s.isOutputShutdown() || s.isClosed()) {
						mapUtilisateurConnexion.remove(u);
					} else {

						in = new ObjectInputStream(s.getInputStream());
						if (in.available() > 0) {
							Message mess = (Message) in.readObject();
							messagessATraiter.add(new AssocMessageSocket(mess, s));
						}
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
