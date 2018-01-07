package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;

import message.MessageDemConnexion;
import message.MessageGroupe;
import message.MessageReponseConnexion;
import message.MessageTicket;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

class ThreadConnexion implements Runnable {

	Serveur serveur;

	public ThreadConnexion(Serveur serveur) {
		this.serveur = serveur;
	}

	@Override
	public void run() {
		try (ServerSocket ss = new ServerSocket(serveur.getPort())) {
			for (; !Thread.interrupted();) {
				Socket s = ss.accept();

				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());

				Object objetRecu = in.readObject();
				MessageDemConnexion messageRecu = (MessageDemConnexion) objetRecu;

				boolean connexionAccepted = serveur.isConnectionClientAccepted(messageRecu);

				String idUtilisateur = messageRecu.getIdUtilisateur();
				Utilisateur utilisateur = serveur.getUtilisateurFromId(idUtilisateur);
				System.out.println("Connexion de " + idUtilisateur);
				Set<AssocUtilisateurSocket> utilisateursConnectes = serveur.getUtilisateursConnectes();
				boolean dejaConnecte = utilisateursConnectes
						.contains(new AssocUtilisateurSocket(utilisateur, null, null, null));

				if (!connexionAccepted || dejaConnecte) {
					out.writeObject(new MessageReponseConnexion(false, null));
					out.flush();
					out.close();
					in.close();
					s.close();
				} else {
					System.out.println("Connexion de " + idUtilisateur +" acceptée");
					utilisateursConnectes.add(new AssocUtilisateurSocket(utilisateur, s, out, in));
					out.writeObject(new MessageReponseConnexion(true, utilisateur));
					out.flush();
					ThreadEcouteTraitementClient e = new ThreadEcouteTraitementClient(serveur,
							new AssocUtilisateurSocket(utilisateur, s, out, in));

					List<Ticket> listeTickets = serveur.ticketsUtilisateur(idUtilisateur);
					for (Ticket t : listeTickets) {
						NavigableSet<MessageConversation> messagesTicket = t.getFilDiscussion().getEnsembleMessage();
						for (MessageConversation m : messagesTicket) {
							if (!serveur.isMessageRecuParUtilisateur(m, idUtilisateur)) {
								serveur.messageRecu(m, idUtilisateur);
							}
						}
						out.writeObject(new MessageTicket(t));
						out.flush();
					}

					List<Groupe> groupesUtilisateur = serveur.groupesUtilisateur(idUtilisateur);
					for (Groupe groupe : groupesUtilisateur) {
						out.writeObject(new MessageGroupe(groupe));
						out.flush();
					}

					Thread t = new Thread(e);
					t.start();
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
