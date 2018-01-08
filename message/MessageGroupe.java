package message;

import modele.Groupe;

public class MessageGroupe extends Message {

	private static final long serialVersionUID = 1L;
	private boolean ajouter;
	private Groupe groupe;

	public MessageGroupe(Groupe groupe, boolean ajouter) {
		super();
		this.ajouter = ajouter;
		this.groupe = groupe;
	}

	public Groupe getGroupe() {
		return groupe;
	}
	
	public boolean isAjouter(){
		return ajouter;
	}
}
