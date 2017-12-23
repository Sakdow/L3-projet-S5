package modele;

import java.sql.Date;

public class MessageConversation implements Comparable<MessageConversation> {
	private String auteur;
	private String texte;
	private Date date;

	public MessageConversation(String auteur, String texte, Date date) {
		super();
		this.auteur = auteur;
		this.texte = texte;
		this.date = date;
	}

	public int compareTo(MessageConversation conversationToCompare) {
		return date.compareTo(conversationToCompare.getDate());
	}

	public String getAuteur() {
		return auteur;
	}

	public String getTexte() {
		return texte;
	}

	public Date getDate() {
		return date;
	}
}
