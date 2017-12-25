package message;

import java.net.InetAddress;

import modele.MessageConversation;
import modele.Ticket;

public class MessageNouveauMessage extends Message{
	private static final long serialVersionUID = 1L;
	private Ticket ticket;
	private MessageConversation nouveauMessage;
	
	public MessageNouveauMessage(InetAddress adresseIp, Ticket ticket, MessageConversation nouveauMessage){
		super(adresseIp);
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
