package message;

import modele.MessageConversation;
import modele.Ticket;

public class MessageNouveauMessage extends Message{
	private static final long serialVersionUID = 1L;
	private Ticket ticket;
	private MessageConversation nouveauMessage;
	
	public MessageNouveauMessage(Ticket ticket, MessageConversation nouveauMessage){
		super();
		this.ticket=ticket;
		this.nouveauMessage=nouveauMessage;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public MessageConversation getNouveauMessage() {
		return nouveauMessage;
	}
}
