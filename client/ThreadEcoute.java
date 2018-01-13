package client;

import message.Message;
import message.MessageGroupe;
import message.MessageMessageConversation;
import message.MessageTicket;

public class ThreadEcoute extends Thread {

	private Client client;

	public ThreadEcoute(Client client) {
		super();
		this.client = client;
	}

	public void run() {
		while (!this.isInterrupted()) {
			Message messageRecu = client.getReseaux().ecoute();
			if (messageRecu != null) {
				if (messageRecu instanceof MessageTicket) {

					MessageTicket messageTicket = (MessageTicket) messageRecu;
                                        if(messageTicket.isAjouter()){
                                            client.ajouterTicket(messageTicket.getTicket());
                                        } else {
                                            client.supprimerTicket(messageTicket.getTicket());
                                        }
				} else if (messageRecu instanceof MessageGroupe) {
					MessageGroupe messageGroupe = (MessageGroupe) messageRecu;
					if (messageGroupe.isAjouter()) {
						client.ajouterGroupe(messageGroupe.getGroupe());
					} else {
						client.supprimerGroupe(messageGroupe.getGroupe());
					}
				} else {

					MessageMessageConversation nouveauMessConv = (MessageMessageConversation) messageRecu;
					client.ajouterMessageConv(nouveauMessConv.getIdTicket(), nouveauMessConv.getIdGroupe(),
							nouveauMessConv.getMessageConv());
				}
			} else {
				if (!this.isInterrupted()) {
					Message messageConnexion = client.getReseaux()
							.connexionServeur(client.getUtilisateurClient().getIdUtilisateur(), client.getMotDePasse());
					while (!this.isInterrupted() && messageConnexion == null) {
						messageConnexion = client.getReseaux().connexionServeur(
								client.getUtilisateurClient().getIdUtilisateur(), client.getMotDePasse());
					}
					client.renvoieMessageNonRecuParServeur();
				}
			}
		}
	}

	public static void stopper(ThreadEcoute t) {
		t.interrupt();
	}

}
