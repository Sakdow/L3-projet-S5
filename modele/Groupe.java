package modele;

import java.util.LinkedList;
import java.util.List;

public class Groupe {
	List<Utilisateur> listeUtilisateur = new LinkedList<>();
	String idGroupe;

	public Groupe(String idGroupe) {
		super();
		this.idGroupe = idGroupe;
	}

	public void ajouterUtilisateurs(Utilisateur... utilisateurs) {
		for (Utilisateur u : utilisateurs) {
			if (!listeUtilisateur.contains(u)) {
				this.listeUtilisateur.add(u);
			}
		}
	}

	public void ajouterGroupes(Groupe... groupes) {
		for (Groupe g : groupes) {
			ajouterUtilisateurs(g.getListeUtilisateur().toArray(new Utilisateur[0]));
		}
	}

	public List<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur;
	}

	public String getIdGroupe() {
		return idGroupe;
	}

}
