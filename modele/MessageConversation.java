package modele;

import java.io.Serializable;
import java.sql.Date;

public class MessageConversation implements Serializable, Comparable<MessageConversation> {
	private static final long serialVersionUID = 1L;
	private int idMessage;
	private Utilisateur createur;
	private String texte;
	private EtatMessage etatGroupe;
	private Date date;
	private boolean luParUtilisateur;

	public MessageConversation(int idMessage, Utilisateur createur, String texte, Date date, EtatMessage etatGroupe,
			boolean luParUtilisateur) {
		super();
		this.idMessage = idMessage;
		this.createur = createur;
		this.texte = texte;
		this.date = date;
		this.etatGroupe = etatGroupe;
		this.luParUtilisateur = luParUtilisateur;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public boolean equals(Object obj) {
		if (obj instanceof MessageConversation) {
			MessageConversation messConv = (MessageConversation) obj;
			return this.getIdMessage() == messConv.getIdMessage();
		}
		return false;
	}

	public int compareTo(MessageConversation conversationToCompare) {
		if (this.equals(conversationToCompare))
			return 0;
		int res = date.compareTo(conversationToCompare.getDate());
		if (res != 0)
			return res;
		return this.idMessage - conversationToCompare.idMessage;
	}

	public EtatMessage getEtatGroupe() {
		return etatGroupe;
	}

	public void setEtatGroupe(EtatMessage etatGroupe) {
		this.etatGroupe = etatGroupe;
	}

	public boolean isLuParUtilisateur() {
		return luParUtilisateur;
	}

	public void setLuParUtilisateur(boolean luParUtilisateur) {
		this.luParUtilisateur = luParUtilisateur;
	}

	public Utilisateur getCreateur() {
		return createur;
	}

	public String getTexte() {
		return texte;
	}

	public Date getDate() {
		return date;
	}

}
