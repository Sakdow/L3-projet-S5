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
		while (!this.isInterrupted()) {
			Message messageRecu = reseaux.ecoute();
			if (messageRecu != null) {
				if (messageRecu instanceof MessageTicket) {
					MessageTicket messageTicket = (MessageTicket) messageRecu;
					client.ajouterTicket(messageTicket.getTicket());
				} else if (messageRecu instanceof MessageGroupe) {
					MessageGroupe messageGroupe = (MessageGroupe) messageRecu;
					if(messageGroupe.isAjouter()){
						client.ajouterGroupe(messageGroupe.getGroupe());
					} else {
						client.supprimerGroupe(messageGroupe.getGroupe());
					}
				} else {
					MessageMessageConversation nouveauMessConv = (MessageMessageConversation) messageRecu;
                                        System.out.println(nouveauMessConv.getMessageConv().getEtatGroupe());
					client.ajouterMessageConv(nouveauMessConv.getIdTicket(), nouveauMessConv.getIdGroupe(),
							nouveauMessConv.getMessageConv());
				}
			} else {
                            if(!this.isInterrupted()){
                                Message messageConnexion = reseaux.connexionServeur(client.getUtilisateurClient().getIdUtilisateur(), client.getMotDePasse());
                                while(!this.isInterrupted() && messageConnexion == null ){
                                    messageConnexion = reseaux.connexionServeur(client.getUtilisateurClient().getIdUtilisateur(), client.getMotDePasse());
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
