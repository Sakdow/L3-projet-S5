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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUtilisateur == null) ? 0 : idUtilisateur.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
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
