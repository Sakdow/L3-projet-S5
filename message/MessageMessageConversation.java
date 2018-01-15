package message;

import modele.MessageConversation;

public class MessageMessageConversation extends Message {
	private static final long serialVersionUID = 1L;
	private int idTicket;
	private MessageConversation messageConv;
	private String idGroupe;

	public MessageMessageConversation(int idTicket, MessageConversation messageConv) {
		super();
		this.idTicket = idTicket;
		this.messageConv = messageConv;
	}

	public MessageMessageConversation(int idTicket, String idGroupe, MessageConversation messageConv) {
		super();
		this.idTicket = idTicket;
		this.messageConv = messageConv;
		this.idGroupe = idGroupe;
	}

	public String getIdGroupe() {
		return idGroupe;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public MessageConversation getMessageConv() {
		return messageConv;
	}

}
