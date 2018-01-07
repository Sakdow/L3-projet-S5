package serveur;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import message.MessageDemConnexion;
import message.MessageGroupe;
import message.MessageMessageConversation;
import message.MessageMiseAJourEtat;
import message.MessageTicket;
import modele.EtatMessage;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

public class Serveur {

	private BaseDeDonnees baseDeDonnees;
	private int port = 12000;
	private NavigableSet<AssocUtilisateurSocket> utilisateursConnectes = new TreeSet<>();

	public Serveur() {
		initServeur();
	}

	public Serveur(int port) {
		if (port > 0)
			this.port = port;

		initServeur();
	}

	private void initServeur() {
		try {
			baseDeDonnees = new BaseDeDonnees("jdbc:mysql://localhost:3306/Projet", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public boolean lancer(String idUtilisateur, String mdp) {
		try {
			if (isConnexionServeurAcceptee(idUtilisateur, mdp)) {
				Thread t = new Thread(new ThreadConnexion(this));
				t.start();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	Set<AssocUtilisateurSocket> getUtilisateursConnectes() {
		return utilisateursConnectes;
	}

	private ResultSet requeteBaseDeDonnees(String requete) throws SQLException {
		Pattern pattern = Pattern.compile("^INSERT INTO *");
		Matcher matcher = pattern.matcher(requete);
		if (matcher.find()) {
			baseDeDonnees.requeteInsertWithoutReturn(requete);
			return null;
		}
		return baseDeDonnees.requete(requete);
	}

	private ResultSet requeteBaseDeDonnees(String requete, boolean insertAndReturnKey) throws SQLException {
		if (insertAndReturnKey)
			return baseDeDonnees.requeteInsertReturnKey(requete);
		baseDeDonnees.requeteInsertWithoutReturn(requete);
		return null;
	}

	private String texteToTexteSQL(String texte) {
		texte = texte.replaceAll("'", "''");
		return texte;
	}

	Utilisateur getUtilisateurFromId(String idUtilisateur) {
		String requete = "SELECT nom,prenom FROM Utilisateur WHERE idU = '" + idUtilisateur + "'";

		try {
			ResultSet resultat = this.requeteBaseDeDonnees(requete);
			if (!resultat.next())
				return null;
			String nom = resultat.getObject("nom").toString();
			String prenom = resultat.getObject("prenom").toString();

			return new Utilisateur(nom, prenom, idUtilisateur);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	List<Ticket> ticketsUtilisateur(String idUtilisateur) {
		List<Ticket> tousLesTickets = new ArrayList<>();
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT idT,createur,nomG FROM participer WHERE idU = '" + idUtilisateur + "'");

			for (; res.next();) {
				Ticket t = buildTicket(res.getInt("idT"), res.getString("createur"), res.getString("nomG"),
						idUtilisateur);
				tousLesTickets.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tousLesTickets;
	}

	private Ticket buildTicket(int idTicket, String createur, String nomG, String idUtilisateur) {
		Ticket t = null;
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT titre FROM ticket WHERE idT = " + idTicket);
			res.first();
			String titre = res.getString(1);
			t = new Ticket(idTicket, titre, getUtilisateurFromId(createur), new Groupe(nomG), null);
			res = requeteBaseDeDonnees("SELECT idM, texte, dateM, idU FROM message WHERE idT = " + idTicket);
			for (; res.next();) {
				MessageConversation m = new MessageConversation(res.getInt("idM"),
						getUtilisateurFromId(res.getString("idU")), res.getString("texte"), res.getDate("dateM"),
						EtatMessage.NON_RECU_PAR_TOUS, false);
				definirEtatMessageConversation(m, idTicket, idUtilisateur);
				t.ajouterMessage(m);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t;

	}

	private void definirEtatMessageConversation(MessageConversation m, int idTicket, String idUtilisateur) {
		Set<String> participants = getParticipantsTickets(idTicket);
		int nombreParticipants = participants.size();
		try {
			ResultSet res;
			res = requeteBaseDeDonnees("SELECT COUNT(*) FROM recevoir WHERE idM = " + m.getIdMessage());
			res.first();
			if (res.getInt(1) >= nombreParticipants) {
				res = requeteBaseDeDonnees("SELECT COUNT(*) FROM lire WHERE idM = " + m.getIdMessage());
				res.first();
				if (res.getInt(1) >= nombreParticipants) {
					m.setEtatGroupe(EtatMessage.LU_PAR_TOUS);
				} else
					m.setEtatGroupe(EtatMessage.NON_LU_PAR_TOUS);
			} else
				m.setEtatGroupe(EtatMessage.NON_RECU_PAR_TOUS);
			m.setLuParUtilisateur(isMessageLuParUtilisateur(m, idUtilisateur));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	boolean isMessageLuParUtilisateur(MessageConversation m, String idUtilisateur) {
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT * FROM lire WHERE idM = " + m.getIdMessage() + " AND idU = '" + idUtilisateur + "'");
			return res.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	boolean isMessageRecuParUtilisateur(MessageConversation m, String idUtilisateur) {
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT * FROM recevoir WHERE idM = " + m.getIdMessage() + " AND idU = '" + idUtilisateur + "'");
			return res.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	boolean isMessageRecuParTous(int idMessage) throws SQLException {
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idT FROM message WHERE idM = " + idMessage);
			res.first();
			int idTicket = res.getInt(1);
			Set<String> participants = getParticipantsTickets(idTicket);
			int nombreParticipants = participants.size();

			res = requeteBaseDeDonnees("SELECT COUNT(*) FROM recevoir WHERE idM = " + idMessage);
			res.next();
			return res.getInt(1) >= nombreParticipants;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	boolean isMessageLuParTous(int idMessage) throws SQLException {
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idT FROM message WHERE idM = " + idMessage);
			res.first();
			int idTicket = res.getInt(1);
			Set<String> participants = getParticipantsTickets(idTicket);
			int nombreParticipants = participants.size();

			res = requeteBaseDeDonnees("SELECT COUNT(*) FROM lire WHERE idM = " + idMessage);
			res.next();
			return res.getInt(1) >= nombreParticipants;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	void messageRecu(MessageConversation m, String idUtilisateur) {
		try {
			int idMessage = m.getIdMessage();
			requeteBaseDeDonnees("INSERT INTO recevoir (idM, idU) VALUES (" + idMessage + ",'" + idUtilisateur + "')");

			if (isMessageRecuParTous(idMessage)) {
				ResultSet res = requeteBaseDeDonnees("SELECT idT FROM message WHERE idM = " + idMessage);
				res.first();
				int idTicket = res.getInt(1);
				Set<String> participants = getParticipantsTickets(idTicket);
				participants.remove(idUtilisateur);

				m.setEtatGroupe(EtatMessage.NON_LU_PAR_TOUS);

				res = requeteBaseDeDonnees("SELECT nomG FROM participer WHERE idT = " + idTicket);
				res.first();
				String nomGroupe = res.getString(1);

				for (String idU : participants) {
					Utilisateur u = getUtilisateurFromId(idU);
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().equals(u)) {
							m.setLuParUtilisateur(isMessageLuParUtilisateur(m, idU));
							ObjectOutputStream out = assoc.getOut();
							out.writeObject(new MessageMessageConversation(idTicket, nomGroupe, m));
							out.flush();
							break;
						}
					}
				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	List<Groupe> groupesUtilisateur(String idUtilisateur) {
		List<Groupe> groupes = new ArrayList<>();
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT nomG FROM appartenir WHERE idU = '" + idUtilisateur + "'");
			for (; res.next();) {
				groupes.add(new Groupe(res.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groupes;
	}

	Set<String> getParticipantsTickets(int idTicket) {

		Set<String> participants = new HashSet<>();
		try {
			ResultSet res = requeteBaseDeDonnees("SELECT idU,createur FROM participer WHERE idT = " + idTicket);
			if (res.next()) {
				participants.add(res.getString("idU"));
				participants.add(res.getString("createur"));
			}

			for (; res.next();)
				participants.add(res.getString("idU"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return participants;
	}

	void deconnexionUtilisateur(AssocUtilisateurSocket assoc) {
		try {
			assoc.getOut().close();
			assoc.getIn().close();
			assoc.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		utilisateursConnectes.remove(assoc);
	}

	void nouveauTicket(Ticket ticket) {
		ResultSet res;
		int idTicket;
		Utilisateur createur = ticket.getCreateur();
		String idCreateur = createur.getIdUtilisateur();
		String idGroupe = ticket.getGroupe().getIdGroupe();
		try {
			res = requeteBaseDeDonnees("INSERT INTO ticket (titre) VALUES ('" + texteToTexteSQL(ticket.getNom()) + "')",
					true);
			res.first();
			idTicket = res.getInt(1);

			ticket.setIdTicket(idTicket);
			NavigableSet<MessageConversation> ensembleMessages = ticket.getFilDiscussion().getEnsembleMessage();

			for (MessageConversation m : ensembleMessages) {
				res = requeteBaseDeDonnees(
						"INSERT INTO message (texte,dateM,idT, idU) VALUES ('" + texteToTexteSQL(m.getTexte()) + "',"
								+ "'" + new java.sql.Timestamp(new java.util.Date().getTime()) + "', '" + idTicket
								+ "', '" + idCreateur + "')",
						true);
				res.first();
				int idMessage = res.getInt(1);
				m.setIdMessage(idMessage);

				requeteBaseDeDonnees(
						"INSERT INTO  lire (idM, idU) VALUES (" + m.getIdMessage() + ", '" + idCreateur + "')");
			}

			Set<String> participants = utilisateursGroupe(idGroupe);
			participants.remove(idCreateur);

			for (String u : participants) {
				requeteBaseDeDonnees("INSERT INTO participer (idU,idT,nomG, createur) VALUES ('" + u + "', " + idTicket
						+ ", '" + idGroupe + "', '" + idCreateur + "')");
			}

			requeteBaseDeDonnees("INSERT INTO participer (idU,idT,nomG, createur) VALUES ('" + idCreateur + "', "
					+ idTicket + ", '" + idGroupe + "', '" + idCreateur + "')");

			System.out.println(participants.size());
			envoyerNouveauTicketConnectes(ticket, participants);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void envoyerNouveauTicketConnectes(Ticket ticket, Collection<String> idParticipants) {
		for (String idU : idParticipants) {
			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				if (assoc.getUtilisateur().getIdUtilisateur().equals(idU)) {
					ObjectOutputStream out = assoc.getOut();
					try {
						Set<MessageConversation> ensembleMessages = ticket.getFilDiscussion().getEnsembleMessage();
						for (MessageConversation m : ensembleMessages) {
							messageRecu(m, idU);
						}
						out.writeObject(new MessageTicket(ticket));
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				}
			}
		}
	}

	Set<String> utilisateursGroupe(String nomGroupe) {
		Set<String> ensembleUtilisateurs = new HashSet<>();
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idU FROM appartenir WHERE nomG = '" + nomGroupe + "'");
			for (; res.next();)
				ensembleUtilisateurs.add(res.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ensembleUtilisateurs;
	}

	void nouveauMessageConversation(MessageMessageConversation message) {
		MessageConversation messageConv = message.getMessageConv();
		Utilisateur createur = messageConv.getCreateur();
		int idTicket = message.getIdTicket();

		ResultSet res;
		try {
			res = requeteBaseDeDonnees(
					"INSERT INTO message (texte,dateM,idT, idU) VALUES ('" + texteToTexteSQL(messageConv.getTexte())
							+ "', '" + new java.sql.Timestamp(new java.util.Date().getTime()) + "' , '"
							+ message.getIdTicket() + "', '" + createur.getIdUtilisateur() + "')",
					true);
			res.first();
			int idMessage = res.getInt(1);
			messageConv.setIdMessage(idMessage);

			requeteBaseDeDonnees(
					"INSERT INTO lire (idM, idU) VALUES (" + idMessage + ", '" + createur.getIdUtilisateur() + "')");
			envoyerNouveauMessageConnectes(messageConv, idTicket);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void envoyerNouveauMessageConnectes(MessageConversation messageConversation, int idTicket) {
		Set<String> idParticipants = getParticipantsTickets(idTicket);
		Utilisateur createur = messageConversation.getCreateur();
		String idCreateur = createur.getIdUtilisateur();
		String nomGroupe = nomGroupeFromIdTicket(idTicket);

		for (String idU : idParticipants) {
			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				if (assoc.getUtilisateur().getIdUtilisateur().equals(idU)) {
					ObjectOutputStream out = assoc.getOut();

					messageRecu(messageConversation, idU);
					messageConversation.setLuParUtilisateur(idU.equals(idCreateur));

					try {
						out.writeObject(new MessageMessageConversation(idTicket, nomGroupe, messageConversation));
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				}
			}
		}
	}

	private String nomGroupeFromIdTicket(int idTicket) {
		String idGroupe = "";
		try {
			ResultSet res = requeteBaseDeDonnees("SELECT nomG FROM participer WHERE idT = " + idTicket);
			if (res != null && res.first())
				idGroupe = res.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idGroupe;
	}

	void messageConversationLu(MessageMiseAJourEtat message) {
		int idM = message.getIdMessage();
		String idUtilisateur = message.getIdUtilisateur();
		try {
			requeteBaseDeDonnees("INSERT INTO lire (idM, idU) VALUES (" + idM + ", '" + idUtilisateur + "')");
			if (isMessageLuParTous(idM)) {
				MessageConversation messageLu = messageConversationFromId(idM);

				ResultSet res = requeteBaseDeDonnees("SELECT idT FROM message WHERE idM = " + idM);
				res.first();
				int idTicket = res.getInt(1);

				Set<String> participants = getParticipantsTickets(idTicket);
				participants.remove(idUtilisateur);

				messageLu.setEtatGroupe(EtatMessage.LU_PAR_TOUS);

				res = requeteBaseDeDonnees("SELECT nomG FROM participer WHERE idT = " + idTicket);
				res.first();
				String nomGroupe = res.getString(1);

				for (String idU : participants) {
					Utilisateur u = getUtilisateurFromId(idU);
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().equals(u)) {
							messageLu.setLuParUtilisateur(isMessageLuParUtilisateur(messageLu, idU));
							ObjectOutputStream out = assoc.getOut();
							out.writeObject(new MessageMessageConversation(idTicket, nomGroupe, messageLu));
							out.flush();
							break;
						}
					}
				}

			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private MessageConversation messageConversationFromId(int idMessage) throws SQLException {
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT texte,dateM,idU FROM message WHERE idM = " + idMessage);
			if (!res.first())
				return null;
			String texte = res.getString("texte");
			Date date = res.getDate("dateM");
			String idAuteur = res.getString("idU");

			return new MessageConversation(idMessage, getUtilisateurFromId(idAuteur), texte, date,
					EtatMessage.NON_RECU_PAR_LE_SERVEUR, false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	boolean isConnectionClientAccepted(MessageDemConnexion message) {

		String requete = "SELECT mdp FROM Utilisateur WHERE idU = '" + message.getIdUtilisateur() + "'";

		try {
			ResultSet resultat = requeteBaseDeDonnees(requete);
			return resultat.next() && resultat.getObject("mdp").toString().equals(message.getMotDePasse());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean isConnexionServeurAcceptee(String idUtilisateur, String mdp) throws SQLException {
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idU FROM appartenir WHERE nomG = 'Aministrateur'");
			for (; res.next();) {
				if (res.getString(idUtilisateur).equals(idUtilisateur)) {
					ResultSet r = requeteBaseDeDonnees(
							"SELECT mdp FROM Utilisateur WHERE idU = '" + idUtilisateur + "'");
					return r.first() && r.getString(1).equals(mdp);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return false;
	}

	public void creerGroupe(String nomGroupe, Collection<Utilisateur> utilisateurs) {
		try {
			requeteBaseDeDonnees("INSERT INTO groupe (nomG) VALUES ('" + nomGroupe + "')");
			Groupe g = new Groupe(nomGroupe);

			for (Utilisateur u : utilisateurs) {
				requeteBaseDeDonnees("INSERT INTO appartenir (idU, nomG) VALUES ('" + u.getIdUtilisateur() + "', '"
						+ nomGroupe + "')");
				g.ajouterUtilisateurs(u);
			}

			for (Utilisateur u : utilisateurs) {
				for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
					AssocUtilisateurSocket assoc = ite.next();
					if (assoc.getUtilisateur().equals(u)) {
						ObjectOutputStream out = assoc.getOut();
						out.writeObject(new MessageGroupe(g));
						out.flush();
						break;
					}
				}
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
