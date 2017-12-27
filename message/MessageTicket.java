package message;

import modele.Ticket;

public class MessageTicket extends Message {
	
	private static final long serialVersionUID = 1L;
	private Ticket ticket;
	private boolean demande;
	public MessageTicket(Ticket ticket, boolean demande){
		super();
		this.ticket=ticket;
		this.demande=demande;
	}

	public Ticket getTicket() {
		return this.ticket;
	}
	
	public boolean getDemande() {
		return this.demande;
	}
	
}