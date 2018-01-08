package client;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;

import message.MessageMessageConversation;
import message.MessageMiseAJourEtat;
import modele.EtatMessage;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reseaux reseaux = new Reseaux("localhost", 8090);
		Utilisateur clientUtilisateur = reseaux.connexionServeur("Philatoras", "azertyuiop").getUtilisateur();

		Client client = new Client(clientUtilisateur, reseaux);
		System.out.println(client.getUtilisateurClient().getIdUtilisateur());
		System.out.println(client.getUtilisateurClient().getNom());
		System.out.println(client.getUtilisateurClient().getPrenom());
		ThreadEcoute t = new ThreadEcoute(reseaux, client);
		t.start();
		/* List<Groupe> l = client.getListeGroupe(); */
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 5; i++) {
			List<Ticket> liste = client.getTicketsRecu().get(new Groupe("Groupe4.2"));
			for (Ticket ti : liste) {
				NavigableSet<MessageConversation> message = ti.getFilDiscussion().getEnsembleMessage();
				for (Iterator<MessageConversation> ite = message.iterator(); ite.hasNext();) {
					MessageConversation m = ite.next();
					System.out.println("Texte : " + m.getTexte() + "\nEtat : " + m.getEtatGroupe());
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("dsfqsfqsfqfkqdmjfqposdjfodsqijfqd;f;isdqhfdsf");
		reseaux.envoyerMessage(new MessageMiseAJourEtat(46, 42, client.getUtilisateurClient().getIdUtilisateur(),
				EtatMessage.NON_LU_PAR_TOUS, true));

		for (int i = 0; i < 5; i++) {
			List<Ticket> liste = client.getTicketsRecu().get(new Groupe("Groupe4.2"));
			for (Ticket ti : liste) {
				NavigableSet<MessageConversation> message = ti.getFilDiscussion().getEnsembleMessage();
				for (Iterator<MessageConversation> ite = message.iterator(); ite.hasNext();) {
					MessageConversation m = ite.next();
					System.out.println("Texte : " + m.getTexte() + "\nEtat : " + m.getEtatGroupe());
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * for (int i = 0; i < 15; i++) { Map<Groupe, List<Ticket>> ticketsRecus
		 * = client.getTicketsRecu(); for (Iterator<Groupe> ite = l.iterator();
		 * ite.hasNext();) { Groupe g = ite.next();
		 * System.out.println(g.getIdGroupe()); List<Ticket> tickets =
		 * ticketsRecus.get(g); if (tickets != null) for (Ticket ti : tickets) {
		 * System.out.println(ti.getNom()); for (MessageConversation m :
		 * ti.getFilDiscussion().getEnsembleMessage())
		 * System.out.println("Auteur : " + m.getCreateur().getIdUtilisateur() +
		 * " " + m.getDate() + "\n\t" + m.getTexte() + "\n\t" +
		 * m.getEtatGroupe()); } try { Thread.sleep(4000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } }
		 * 
		 * client.getReseaux().deconnexionServeur(client.getUtilisateurClient().
		 * getIdUtilisateur());
		 */
	}

}