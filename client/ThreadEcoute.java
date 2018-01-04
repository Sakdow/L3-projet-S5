package client;

import message.Message;
import message.MessageGroupe;
import message.MessageMessageConversation;
import message.MessageTicket;

public class ThreadEcoute extends Thread {

	private Reseaux reseaux;
	private Client client;

	public ThreadEcoute(Reseaux reseaux, Client client) {
		super();
		this.reseaux = reseaux;
		this.client = client;
	}

	public void run() {
		while (!Thread.interrupted()) {
			Message messageRecu = reseaux.ecoute();
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (messageRecu != null) {
				if (messageRecu instanceof MessageTicket) {
					MessageTicket messageTicket = (MessageTicket) messageRecu;
					client.ajouterTicket(messageTicket.getTicket());
				} else if (messageRecu instanceof MessageGroupe) {
					MessageGroupe messageGroupe = (MessageGroupe) messageRecu;
					client.ajouterGroupe(messageGroupe.getGroupe());
				} else {
					MessageMessageConversation nouveauMessConv = (MessageMessageConversation) messageRecu;
					client.ajouterMessageConv(nouveauMessConv.getIdTicket(), nouveauMessConv.getIdGroupe(),
							nouveauMessConv.getMessageConv());
				}
			}
		}
	}

	public static void stopper(ThreadEcoute t) {
		t.interrupt();
	}

}
