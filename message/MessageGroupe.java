package message;

import modele.Groupe;

public class MessageGroupe extends Message {

	private static final long serialVersionUID = 1L;
	private Groupe groupe;

	public MessageGroupe(Groupe groupe) {
		super();
		this.groupe = groupe;
	}

	public Groupe getGroupe() {
		return groupe;
	}
}
