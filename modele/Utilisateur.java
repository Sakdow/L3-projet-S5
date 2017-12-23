package modele;

public class Utilisateur {
	
	private String nom;
	private String prenom;
	private String idUtilisateur;
	
	public Utilisateur(String nom, String prenom, String idUtilisateur){
		super();
		this.nom=nom;
		this.prenom=prenom;
		this.idUtilisateur=idUtilisateur;
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
	
}
