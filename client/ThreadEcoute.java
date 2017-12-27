package client;

import message.MessageTicket;

public class ThreadEcoute implements Runnable {

	private Reseaux reseaux;
	private Client client;

	public ThreadEcoute(Reseaux reseaux, Client client) {
		super();
		this.reseaux = reseaux;
		this.client = client;
	}

	public void run() {
		while (true) {
			MessageTicket messageTicket = reseaux.ecoute();
			client.ajouterTicket(messageTicket.getTicket());
		}
	}

}
