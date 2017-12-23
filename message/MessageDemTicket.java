package message;

import modele.Ticket;

public class MessageDemTicket extends Message {
	
	private static final long serialVersionUID = 1L;
	private Ticket ticket;
	private boolean demande;
	public MessageDemTicket(String adresseIp, Ticket ticket, boolean demande){
		super(adresseIp);
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