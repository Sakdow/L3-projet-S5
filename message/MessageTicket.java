package message;

import modele.Ticket;

public class MessageTicket extends Message {

	private static final long serialVersionUID = 1L;
	private Ticket ticket;

	public MessageTicket(Ticket ticket) {
		super();
		this.ticket = ticket;
	}

	public Ticket getTicket() {
		return this.ticket;
	}

}