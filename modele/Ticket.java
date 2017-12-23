package modele;

public class Ticket {
	private int idTicket;
	private String nom;
	private Groupe groupe;
	private Utilisateur createur;
	private FilDiscussion filDiscussion;

	public Ticket (int idTicket, String nom, Utilisateur createur, Groupe groupe, MessageConversation premierMessage){
		super();
		this.nom=nom;
		this.idTicket=idTicket;
		this.createur=createur;
		this.groupe=groupe;
		this.filDiscussion= new FilDiscussion(premierMessage);
	}
	
	public void ajouterMessage(MessageConversation message){
		filDiscussion.ajouterMessage(message);
	}
	
	public int getIdTicket() {
		return idTicket;
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
