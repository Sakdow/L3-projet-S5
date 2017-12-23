package client;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class Client {
	private Map<Groupe,List<Ticket>> ticketsCree;
	private Map<Groupe,List<Ticket>> ticketsRecu;
	private Utilisateur utilisateurClient;
	/* Client id ? Tickets creer ticket reçu ?*/
	public Client (Utilisateur utilisateurClient){
		super();
		this.utilisateurClient=utilisateurClient;
		ticketsCree=new TreeMap<>();
		ticketsRecu=new TreeMap<>();
	}
	
	
	private void ajouterTicketMap(Map<Groupe,List<Ticket>> tickets, Ticket ticket, Groupe groupe){
		if(tickets.containsKey(groupe)){
			List<Ticket> listeTicket = tickets.get(groupe);
			listeTicket.add(ticket);
		} else {
			List<Ticket> listeTicket = new LinkedList<>();
			listeTicket.add(ticket);
			tickets.put(groupe, listeTicket);
		}
	}
	
	public void ajouterTicket(Ticket... tickets){
		for(Ticket ticket : tickets){
			Utilisateur createur=ticket.getCreateur();
			Groupe groupe= ticket.getGroupe();
			if(createur.getIdUtilisateur() == utilisateurClient.getIdUtilisateur()){
				ajouterTicketMap(ticketsCree, ticket, groupe);
			} else {
				ajouterTicketMap(ticketsRecu, ticket, groupe);
			}
		}
	}
	
	public void creerTicket(Groupe groupe, String nomTicket , MessageConversation message){
		Ticket nouveauTicket = new Ticket(-1, nomTicket, utilisateurClient, groupe, message);
		/*envoyerMessage(String adresseIp, Ticket ticket, MessageConversation nouveauMessage)*/
	}
	
	public void envoyerMessage(MessageConversation message){
		/*todo*/
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



	
}
