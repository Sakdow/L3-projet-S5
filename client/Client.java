package client;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import message.MessageTicket;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class Client {
	private Map<Groupe, List<Ticket>> ticketsCree;
	private Map<Groupe, List<Ticket>> ticketsRecu;
	private List<Groupe> listeGroupe;
	private Utilisateur utilisateurClient;
	private Reseaux reseaux;

	public Client(Utilisateur utilisateurClient, Reseaux reseaux) {
		super();
		this.utilisateurClient = utilisateurClient;
		this.reseaux = reseaux;
		ticketsCree = new TreeMap<>();
		ticketsRecu = new TreeMap<>();
		listeGroupe = new LinkedList<>();
	}

	public void ajouterGroupe(Groupe... groupes){
		for(Groupe groupe : groupes){
			if(listeGroupe.contains(groupe)){
				listeGroupe.add(groupe);
			}
		}
	}
	
	private void ajouterTicketMap(Map<Groupe, List<Ticket>> tickets, Ticket ticket, Groupe groupe) {
		if (tickets.containsKey(groupe)) {
			boolean ajout = false;
			List<Ticket> listeTicket = tickets.get(groupe);
			for(Ticket t : listeTicket){
				if(t.getIdTicket()==ticket.getIdTicket()){
					t=ticket;
					ajout=true;
				}
			}
			if(!ajout){
				listeTicket.add(ticket);
			}
		} else {
			List<Ticket> listeTicket = new LinkedList<>();
			listeTicket.add(ticket);
			tickets.put(groupe, listeTicket);
		}
	}

	public void ajouterTicket(Ticket... tickets) {
		for (Ticket ticket : tickets) {
			Utilisateur createur = ticket.getCreateur();
			Groupe groupe = ticket.getGroupe();
			if (createur.getIdUtilisateur() == utilisateurClient.getIdUtilisateur()) {
				ajouterTicketMap(ticketsCree, ticket, groupe);
			} else {
				ajouterTicketMap(ticketsRecu, ticket, groupe);
			}
		}
	}

	public void creerTicket(Groupe groupe, String nomTicket, MessageConversation message) {
		Ticket nouveauTicket = new Ticket(-1, nomTicket, utilisateurClient, groupe, message);
		MessageTicket messageTicket = new MessageTicket(nouveauTicket, false);
		reseaux.envoyerMessage(messageTicket);
	}

	public Map<Groupe, List<Ticket>> getTicketsCree() {
		return ticketsCree;
	}

	public Map<Groupe, List<Ticket>> getTicketsRecu() {
		return ticketsRecu;
	}

	public Utilisateur getUtilisateurClient() {
		return utilisateurClient;
	}

	public Reseaux getReseaux() {
		return reseaux;
	}

	public List<Groupe> getListeGroupe() {
		return listeGroupe;
	}

}
