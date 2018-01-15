package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;

import message.Message;
import message.MessageDeconnexion;
import message.MessageMessageConversation;
import message.MessageMiseAJourEtat;
import message.MessageTicket;

class ThreadEcouteTraitementClient implements Runnable {

	Serveur serveur;
	AssocUtilisateurSocket utilisateurSocket;

	public ThreadEcouteTraitementClient(Serveur serveur, AssocUtilisateurSocket utilisateurSocket) {
		this.serveur = serveur;
		this.utilisateurSocket = utilisateurSocket;
	}

	@Override
	public void run() {
		ObjectInputStream in = utilisateurSocket.getIn();
		for (; !Thread.interrupted();) {
			try {
				Message messageRecu = (Message) in.readObject();
				if (messageRecu instanceof MessageDeconnexion) {
					serveur.deconnexionUtilisateur(utilisateurSocket);
					return;
				} else if (messageRecu instanceof MessageTicket) {
					MessageTicket m = (MessageTicket) messageRecu;
					serveur.nouveauTicket(m.getTicket());
				} else if (messageRecu instanceof MessageMessageConversation) {
					serveur.nouveauMessageConversation((MessageMessageConversation) messageRecu);
				} else if( messageRecu instanceof MessageMiseAJourEtat ){
					serveur.messageConversationLu((MessageMiseAJourEtat) messageRecu);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				serveur.deconnexionUtilisateur(utilisateurSocket);
				return;
			}
		}
	}

}
