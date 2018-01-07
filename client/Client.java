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

	public void ajouterGroupe(Groupe groupe) {
		synchronized (listeGroupe) {
			if (!listeGroupe.contains(groupe)) {
				listeGroupe.add(groupe);
			}
		}
	}

	private boolean rechercheEtModificationMessageConv(Map<Groupe, List<Ticket>> tickets, Ticket ticket, Groupe groupe,
			MessageConversation messConv) {
		for (Ticket t : tickets.get(groupe)) {
			if (t.equals(ticket)) {
				if (t.getFilDiscussion().getEnsembleMessage().contains(messConv))
					for (MessageConversation m : t.getFilDiscussion().getEnsembleMessage()) {
						if (m.equals(messConv)) {
							m.setEtatGroupe(messConv.getEtatGroupe());
							m.setLuParUtilisateur(messConv.isLuParUtilisateur());
						}
					}
				else {
					t.getFilDiscussion().ajouterMessage(messConv);
				}
				return true;
			}
		}
		return false;
	}

	public void ajouterMessageConv(int idTicket, String idGroupe, MessageConversation messConv) {
		Ticket ticket = new Ticket(idTicket, null, null, null, null);
		Groupe groupe = new Groupe(idGroupe);
		boolean ajoute = false;

		if (ticketsCree.containsKey(groupe)) {
			ajoute = rechercheEtModificationMessageConv(ticketsCree, ticket, groupe, messConv);
		}

		if (!ajoute && ticketsRecu.containsKey(groupe)) {
			rechercheEtModificationMessageConv(ticketsRecu, ticket, groupe, messConv);
		}
	}

	private void ajouterTicketMap(Map<Groupe, List<Ticket>> tickets, Ticket ticket, Groupe groupe) {
		if (tickets.containsKey(groupe)) {
			boolean ajout = false;
			List<Ticket> listeTicket = tickets.get(groupe);
			for (Ticket t : listeTicket) {
				if (t.equals(ticket)) {
					t = ticket;
					ajout = true;
				}
			}
			if (!ajout) {
				listeTicket.add(ticket);
			}
		} else {
			List<Ticket> listeTicket = new LinkedList<>();
			listeTicket.add(ticket);
			tickets.put(groupe, listeTicket);
		}
	}

	public void ajouterTicket(Ticket ticket) {
		Utilisateur createur = ticket.getCreateur();
		Groupe groupe = ticket.getGroupe();
		if (createur.equals(utilisateurClient)) {
			ajouterTicketMap(ticketsCree, ticket, groupe);
		} else {
			ajouterTicketMap(ticketsRecu, ticket, groupe);
		}
	}

	public void creerTicket(Groupe groupe, String nomTicket, MessageConversation message) {
		Ticket nouveauTicket = new Ticket(-1, nomTicket, utilisateurClient, groupe, message);
		this.ajouterTicket(nouveauTicket);
		MessageTicket messageTicket = new MessageTicket(nouveauTicket);
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
