package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import message.Message;
import message.MessageDeconnexion;import message.MessageMessageConversation;
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
		ObjectOutputStream out = utilisateurSocket.getOut();
		ObjectInputStream in = utilisateurSocket.getIn();
		
		try {
			Message messageRecu = (Message) in.readObject();
			if( messageRecu instanceof MessageDeconnexion ){
				serveur.deconnexionUtilisateur(utilisateurSocket);
				return;
			} else if( messageRecu instanceof MessageTicket ) {
				MessageTicket m = (MessageTicket) messageRecu;
				serveur.nouveauTicket(m.getTicket());
			} else if( messageRecu instanceof MessageMessageConversation ){
				serveur.nouveauMessage((MessageMessageConversation) messageRecu);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
