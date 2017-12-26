package message;

import modele.Utilisateur;

public class MessageReponseConnexion extends Message {
	private static final long serialVersionUID = 1L;
	private boolean accepte;
	private Utilisateur utilisateur;
	
	public MessageReponseConnexion(boolean accepte, Utilisateur utilisateur){
		super();
		this.accepte=accepte;
		this.utilisateur = utilisateur;
	}

	public boolean getAccepte() {
		return this.accepte;
	}
	
	public Utilisateur getUtilisateur(){
		return utilisateur;
	}
	
}
