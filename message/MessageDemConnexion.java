package message;

public class MessageDemConnexion extends Message {
	private static final long serialVersionUID = 1L;
	private String idUtilisateur;
	private String motDePasse;

	public MessageDemConnexion(String idUtilisateur, String motDePasse) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.motDePasse = motDePasse;
	}

	public String getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

}
