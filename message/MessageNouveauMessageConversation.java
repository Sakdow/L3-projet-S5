package message;

import modele.Groupe;
import modele.MessageConversation;

<<<<<<< HEAD
public class MessageNouveauMessageConversation extends Message{
=======
public class MessageNouveauMessageConversation extends Message {
>>>>>>> 5a4cf7dc8de3769c915793fe81758bf5304b559a
	private static final long serialVersionUID = 1L;
	private int idTicket;
	private MessageConversation messageConv;
	private Groupe groupe;

	public MessageNouveauMessageConversation(int idTicket, MessageConversation messageConv) {
		super();
		this.idTicket = idTicket;
		this.messageConv = messageConv;
	}
	
	public MessageNouveauMessageConversation(int idTicket, Groupe groupe,MessageConversation messageConv) {
		super();
		this.idTicket = idTicket;
		this.messageConv = messageConv;
		this.groupe = groupe;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public MessageConversation getMessageConv() {
		return messageConv;
	}

	public Groupe getGroupe() {
		return groupe;
	}
	
	
}
