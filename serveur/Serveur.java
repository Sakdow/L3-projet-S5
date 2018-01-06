package serveur;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

import message.Message;
import message.MessageMessageConversation;
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

		Thread t = new Thread(new ThreadConnexion(this));
		t.start();
	}

	public int getPort() {
		return port;
	}

	Set<AssocUtilisateurSocket> getUtilisateursConnectes() {
		return utilisateursConnectes;
	}

	ResultSet requeteBaseDeDonnees(String requete) throws SQLException {
		Pattern pattern = Pattern.compile("^INSERT INTO *");
		Matcher matcher = pattern.matcher(requete);
		if (matcher.find()) {
			baseDeDonnees.requeteInsertWithoutReturn(requete);
			return null;
		}
		return baseDeDonnees.requete(requete);
	}

	ResultSet requeteBaseDeDonnees(String requete, boolean insertAndReturnKey) throws SQLException {
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
			int nb = res.getInt(1);
			System.out.println(nb + "/" + nombreParticipants);
			return nb >= nombreParticipants;
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
				System.out.println("lu par tous : " + idUtilisateur);
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
						System.out.println("M : \n\t" + assoc.getUtilisateur().getIdUtilisateur());
						System.out.println("\t" + u.getIdUtilisateur());
						if (assoc.getUtilisateur().equals(u)) {
							System.out.println("Coucou");
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
						"INSERT INTO recevoir (idM, idU) VALUES (" + m.getIdMessage() + ", '" + idCreateur + "')");
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

	// private void nouveauMessage(MessageMessageConversation message) {
	// MessageConversation messageConv = message.getMessageConv();
	// Utilisateur createur = messageConv.getCreateur();
	// String idCreateur = createur.getIdUtilisateur();
	// // Ajout du message dans la bdd
	// ResultSet res = requeteBaseDeDonnees("INSERT INTO message
	// (texte,dateM,idT, idU) VALUES ('"
	// + messageConv.getTexte() + "'," + new java.sql.Date(new
	// java.util.Date().getTime()) + ", '"
	// + message.getIdTicket() + "', '" + createur.getIdUtilisateur() + "')",
	// true);
	// res.first();
	// int idMessage = res.getInt(1);
	//
	// int idTicket = message.getIdTicket();
	//
	// res = requeteBaseDeDonnees("SELECT titre FROM Ticket WHERE idT = " +
	// idTicket);
	// res.first();
	//
	// String nomGroupe = nomGroupeFromIdTicket(idTicket);
	//
	// Set<String> participantsTicket = new HashSet<>();
	//
	// res = serveur.requeteBDD("SELECT createur FROM participer WHERE idT = " +
	// idTicket);
	// res.first();
	// participantsTicket.add(res.getString(1));
	//
	// res = serveur.requeteBDD("SELECT idU FROM appartenir WHERE nomG = '" +
	// nomGroupe + "'");
	// }

	// EtatMessage miseAJourEtatTicket(MessageConversation message, Utilisateur
	// u, int idTicket) {
	// Set<String> participantsMessages = getParticipantsTickets(idTicket);
	// try {
	// boolean nonRecuParUtilisateur = false;
	// ResultSet res = requeteBaseDeDonnees("SELECT * FROM recevoir WHERE idM =
	// " + message.getIdMessage()
	// + " AND idU ='" + u.getIdUtilisateur() + "'");
	// if (res.next()) {
	// nonRecuParUtilisateur = true;
	// requeteBaseDeDonnees("INSERT INTO recevoir (idM, idU) VALUES (" +
	// message.getIdMessage() + ", '"
	// + u.getIdUtilisateur() + "'");
	// }
	//
	// if (nonRecuParUtilisateur) {
	// res = requeteBaseDeDonnees("SELECT COUNT(*) FROM recevoir WHERE idM = " +
	// message.getIdMessage());
	// res.first();
	// if (res.getInt(1) < participantsMessages.size()) {
	// message.setEtatGroupe(EtatMessage.NON_RECU_PAR_TOUS);
	// } else {
	// envoyerUtilisateursConnectes(new MessageMessageConversation(idTicket,
	// messageConv), utilisateur);
	// }
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// private void envoyerMessageConversationConnectes(MessageConversation m,
	// Utilisateur... utilisateurs) {
	// for (Utilisateur u : utilisateurs) {
	// for (Iterator<AssocUtilisateurSocket> ite =
	// utilisateursConnectes.iterator(); ite.hasNext();) {
	// AssocUtilisateurSocket assoc = ite.next();
	// if (assoc.utilisateur.equals(u)) {
	// ObjectOutputStream out = assoc.getOut();
	// try {
	// out.writeObject(message);
	// out.flush();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// break;
	// }
	// }
	// }
	// }

	// void envoyerUtilisateursConnectes(Message message, Utilisateur...
	// utilisateurs) {
	// for (Utilisateur u : utilisateurs) {
	// for (Iterator<AssocUtilisateurSocket> ite =
	// utilisateursConnectes.iterator(); ite.hasNext();) {
	// AssocUtilisateurSocket assoc = ite.next();
	// if (assoc.getUtilisateur().equals(u)) {
	// ObjectOutputStream out = assoc.getOut();
	// try {
	// out.writeObject(message);
	// out.flush();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// break;
	// }
	// }
	// }
	// }

}
