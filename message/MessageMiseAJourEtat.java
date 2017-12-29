package message;

import modele.EtatMessage;

public class MessageMiseAJourEtat extends Message {

	private static final long serialVersionUID = 1L;
	private int idTicket;
	private int idMessage;
	private EtatMessage etat;
	private boolean luParUtilisateur;

	public MessageMiseAJourEtat(int idTicket, int idMessage, EtatMessage etat, boolean luParUtilisateur) {
		super();
		this.idTicket = idTicket;
		this.idMessage = idMessage;
		this.etat = etat;
		this.luParUtilisateur= luParUtilisateur;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public EtatMessage getEtat() {
		return etat;
	}

	public boolean isLuParUtilisateur() {
		return luParUtilisateur;
	}
	
	

}