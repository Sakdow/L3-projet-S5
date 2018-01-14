package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.NavigableSet;
import java.util.Observable;
import java.util.Set;

import message.MessageDemConnexion;
import message.MessageGroupe;
import message.MessageReponseConnexion;
import message.MessageTicket;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

class ThreadConnexion extends Observable implements Runnable {

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

				if (!connexionAccepted || (utilisateur != null
						&& utilisateursConnectes.contains(new AssocUtilisateurSocket(utilisateur, null, null, null)))) {
					out.writeObject(new MessageReponseConnexion(false, null));
					out.flush();
					out.close();
					in.close();
					s.close();
				} else {
					System.out.println("Connexion de " + idUtilisateur + " accept�e");
					utilisateursConnectes.add(new AssocUtilisateurSocket(utilisateur, s, out, in));
					
					setChanged();
					notifyObservers();
					
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

					Set<String> groupesUtilisateur = serveur.getGroupes();
					for (String groupe : groupesUtilisateur) {
						out.writeObject(new MessageGroupe(serveur.getGroupeFromNomGroupe(groupe), true));
						out.flush();
					}

					Thread t = new Thread(e);
					serveur.listeThreads.add(t);
					t.start();
				}
			}

		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
