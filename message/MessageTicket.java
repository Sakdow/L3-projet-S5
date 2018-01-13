package message;

import modele.Ticket;

public class MessageTicket extends Message {

	private static final long serialVersionUID = 1L;
	private Ticket ticket;
        private boolean ajouter;

	public MessageTicket(Ticket ticket, boolean ajouter) {
		super();
                this.ticket = ticket;
                this.ajouter = ajouter;
                
	}
        
	public Ticket getTicket() {
		return this.ticket;
	}

        public boolean isAjouter(){
            return ajouter;
        }
}