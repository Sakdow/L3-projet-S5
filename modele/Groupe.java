package modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Groupe implements Serializable, Comparable<Groupe> {
	private static final long serialVersionUID = 1L;
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

	public List<Utilisateur> getListeUtilisateur() {
		return listeUtilisateur;
	}

	public String getIdGroupe() {
		return idGroupe;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Groupe){
			Groupe groupe = (Groupe) obj;
			return (this.getIdGroupe() == groupe.getIdGroupe());
		}
		return false;
	}

	@Override
	public int compareTo(Groupe o) {
		return this.idGroupe.compareTo(o.idGroupe);
	}

}
