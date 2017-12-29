package message;

import modele.MessageConversation;

public class MessageNouveauMessageConversation {
	private static final long serialVersionUID = 1L;
	private int idTicket;
	private MessageConversation messageConv;

	public MessageNouveauMessageConversation(int idTicket, MessageConversation messageConv) {
		super();
		this.idTicket = idTicket;
		this.messageConv = messageConv;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public MessageConversation getMessageConv() {
		return messageConv;
	}
	
}
