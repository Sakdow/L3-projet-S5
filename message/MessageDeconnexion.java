package message;

public class MessageDeconnexion extends Message {
	private static final long serialVersionUID = 1L;
	private String idUtilisateur;

	public MessageDeconnexion(String idUtilisateur) {
		super();
		this.idUtilisateur = idUtilisateur;
	}

	public String getIdUtilisateur() {
		return this.idUtilisateur;
	}

}
