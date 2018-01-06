package modele;

import java.io.Serializable;

public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String idUtilisateur;

	public Utilisateur(String nom, String prenom, String idUtilisateur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Utilisateur ){
			Utilisateur u = (Utilisateur) obj;
			return this.idUtilisateur.equals(u.idUtilisateur);
		}
		return false;
	}

}
