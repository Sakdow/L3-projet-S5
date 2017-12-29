package client;

import message.Message;
import message.MessageGroupe;
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
			if(messageRecu != null){
				if(messageRecu instanceof MessageTicket){
					MessageTicket messageTicket = (MessageTicket) messageRecu;
					client.ajouterTicket(  messageTicket.getTicket() );
				} else {
					MessageGroupe messageGroupe = (MessageGroupe) messageRecu;
					client.ajouterGroupe( messageGroupe.getGroupe() );
				}
			}
		}
	}
	
	public static void stopper( ThreadEcoute t ) {
		t.interrupt();
	}

}
