package modele;

import java.io.Serializable;

public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTicket;
	private String nom;
	private Groupe groupe;
	private Utilisateur createur;
	private FilDiscussion filDiscussion;

	public Ticket(int idTicket, String nom, Utilisateur createur, Groupe groupe, MessageConversation premierMessage) {
		super();
		this.idTicket = idTicket;
		this.nom = nom;
		this.createur = createur;
		this.groupe = groupe;
		this.filDiscussion = new FilDiscussion(premierMessage);
	}

	public void ajouterMessage(MessageConversation message) {
		filDiscussion.ajouterMessage(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ticket) {
			Ticket ticket = (Ticket) obj;
			if (this.getIdTicket() == ticket.getIdTicket())
				return true;
			return this.nom.equals(ticket.nom) && this.groupe.equals(ticket.groupe)
					&& this.createur.equals(ticket.createur) && this.filDiscussion.equals(ticket.filDiscussion);
		}
		return false;
	}

	public String toString() {
		return nom;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public String getNom() {
		return nom;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public Utilisateur getCreateur() {
		return createur;
	}

	public FilDiscussion getFilDiscussion() {
		return filDiscussion;
	}

}
