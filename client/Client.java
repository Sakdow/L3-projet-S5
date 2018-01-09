package client;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeMap;
import message.MessageMessageConversation;
import message.MessageReponseConnexion;

import message.MessageTicket;
import modele.EtatMessage;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class Client extends Observable {
	private final Map<Groupe, List<Ticket>> ticketsCree;
	private final Map<Groupe, List<Ticket>> ticketsRecu;
	private final List<Groupe> listeGroupe;
	private final Utilisateur utilisateurClient;
	private final Reseaux reseaux;
        private String motDePasse;

	public Client(Utilisateur utilisateurClient, Reseaux reseaux, String motDePasse) {
		super();
		this.utilisateurClient = utilisateurClient;
		this.reseaux = reseaux;
                this.motDePasse = motDePasse;
		this.ticketsCree = new TreeMap<>();
		this.ticketsRecu = new TreeMap<>();
		this.listeGroupe = new LinkedList<>();
	}
        
        
        private void rechercheEtEnvoieMessageNonRecuServeur(Set<Groupe> set){
            for (Groupe gr : set){
                for(Ticket tk : ticketsCree.get(gr)){
                    for(MessageConversation messConv : tk.getFilDiscussion().getEnsembleMessage()){
                        if(messConv.getEtatGroupe().equals(EtatMessage.NON_RECU_PAR_LE_SERVEUR)){
                            reseaux.envoyerMessage(new MessageMessageConversation(tk.getIdTicket(), messConv));
                        }
                    }
                }
            }
            
        }
        
        public void renvoieMessageNonRecuParServeur(){
            Set<Groupe> setCree = ticketsCree.keySet();
            Set<Groupe> setRecu = ticketsRecu.keySet();
            rechercheEtEnvoieMessageNonRecuServeur(setCree);
            rechercheEtEnvoieMessageNonRecuServeur(setRecu);
        }

        public static Client lancer (String ip, int port, String idUtilisateur, String motDePasse){
            Reseaux reseau = new Reseaux(ip, port);
            MessageReponseConnexion messReponse = reseau.connexionServeur(idUtilisateur, motDePasse);
            if(messReponse.getAccepte()){
                return new Client(messReponse.getUtilisateur(), reseau, motDePasse);
            } else {
                return null;
            }
        }
        
	public void ajouterGroupe(Groupe groupe) {
		if (!listeGroupe.contains(groupe)) {
			listeGroupe.add(groupe);
			setChanged();
			notifyObservers();
		}
	}

	public void supprimerGroupe(Groupe groupe) {
		if (listeGroupe.contains(groupe)) {
			listeGroupe.remove(groupe);
			setChanged();
			notifyObservers();
		}
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o); // To change body of generated methods, choose
								// Tools | Templates.
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
                        System.out.println("blablabla");
			ajoute = rechercheEtModificationMessageConv(ticketsCree, ticket, groupe, messConv);
		}

		if (!ajoute && ticketsRecu.containsKey(groupe)) {
			rechercheEtModificationMessageConv(ticketsRecu, ticket, groupe, messConv);
		}

		setChanged();
		notifyObservers();
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
		setChanged();
		notifyObservers();
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

        public String getMotDePasse() {
		return motDePasse;
	}

	public List<Groupe> getListeGroupe() {

		return listeGroupe;

	}

}
