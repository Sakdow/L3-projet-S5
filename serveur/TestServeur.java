package serveur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Utilisateur;

public class TestServeur {

	public TestServeur() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Serveur serveur = new Serveur(8090);
		serveur.lancer("admin", "projet");
		try {
			Thread.sleep(10000);
			System.out.println("GOOOOOOOOOOOOO");
			// serveur.supprimerGroupe("MameneCrew");

			// List<Utilisateur> us = new ArrayList<>();
			// us.add(new Utilisateur("Thibault", "Timothée", "Olzindel"));
			// us.add(new Utilisateur("Roche", "Nathan", "Sakdow"));
			// serveur.creerGroupe("MameneCrew", us);
			
//			List<String> groupes = new ArrayList<>();
//			groupes.add("MameneCrew");
//			serveur.creerUtilisateur("Test", "Kevin", "Jean", "a", groupes);
			serveur.supprimerUtilisateur("Test");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
