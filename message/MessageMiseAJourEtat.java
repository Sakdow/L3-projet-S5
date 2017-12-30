package message;

import modele.EtatMessage;

public class MessageMiseAJourEtat extends Message {

	private static final long serialVersionUID = 1L;
	private int idTicket;
	private int idMessage;
	private EtatMessage etat;
	private String idUtilisateur;

	public MessageMiseAJourEtat(int idTicket, int idMessage, String idUtilisateur ,EtatMessage etat, boolean luParUtilisateur) {
		super();
		this.idTicket = idTicket;
		this.idMessage = idMessage;
		this.idUtilisateur= idUtilisateur;
		this.etat = etat;
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

	public String getIdUtilisateur() {
		return idUtilisateur;
	}

	
	
	

}