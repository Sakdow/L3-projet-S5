package client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reseaux reseaux = new Reseaux("127.0.0.1", 12000);
		Utilisateur clientUtilisateur = reseaux.connexionServeur("Olzindel", "aqwzsx").getUtilisateur();

		Client client = new Client(clientUtilisateur, reseaux);
		System.out.println(client.getUtilisateurClient().getIdUtilisateur());
		System.out.println(client.getUtilisateurClient().getNom());
		System.out.println(client.getUtilisateurClient().getPrenom());
		ThreadEcoute t = new ThreadEcoute(reseaux, client);
		t.start();
		List<Groupe> l = client.getListeGroupe();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<Groupe, List<Ticket>> ticketsRecus = client.getTicketsRecu();

		for (Iterator<Groupe> ite = l.iterator(); ite.hasNext();) {
			Groupe g = ite.next();
			System.out.println(g.getIdGroupe());
			List<Ticket> tickets = ticketsRecus.get(g);
			if (tickets != null)
				for (Ticket ti : tickets) {
					System.out.println(ti);
					System.out.println(ti.getFilDiscussion());
					System.out.println(ti.getFilDiscussion().getNombreMessage());
					for (MessageConversation m : ti.getFilDiscussion().getEnsembleMessage())
						System.out.println("Auteur : " + m.getCreateur().getIdUtilisateur() + " " + m.getDate() + "\n\t"
								+ m.getTexte() + "\n\t"+ m.getEtatGroupe());
				}
		}

	}
}