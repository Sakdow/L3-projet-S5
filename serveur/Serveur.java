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
import java.util.Observable;
import java.util.Observer;
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

public class Serveur extends Observable implements Observer {

	private BaseDeDonnees baseDeDonnees;
	private int port = 12000;
	private NavigableSet<AssocUtilisateurSocket> utilisateursConnectes = new TreeSet<>();
	List<Thread> listeThreads = new ArrayList<>();

	public Serveur() {
		initBDD();
	}

	public Serveur(int port) {
		if (port > 0)
			this.port = port;

		initBDD();
	}

	public Serveur(int port, String adresseBDD, int PortBDD, String nomBDD, String adminBDD, String mdpBDD) {
		if (port > 0)
			this.port = port;

		initBDD(adresseBDD, PortBDD, nomBDD, adminBDD, mdpBDD);

	}

	private void initBDD() {
		try {
			baseDeDonnees = new BaseDeDonnees("jdbc:mysql://localhost:3306/Projet", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void initBDD(String adresseBDD, int PortBDD, String nomBDD, String adminBDD, String mdpBDD) {
		try {
			baseDeDonnees = new BaseDeDonnees("jdbc:mysql://" + adresseBDD + ": + " + PortBDD + "/" + nomBDD, adminBDD,
					mdpBDD);
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
				System.out.println("Connexion accept�e - Allumage serveur");
				ThreadConnexion tc = new ThreadConnexion(this);
				tc.addObserver(this);
				Thread t = new Thread(tc);
				listeThreads.add(t);
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

	public Set<Utilisateur> utilisateursConnectes() {
		Set<Utilisateur> utilisateursCo = new HashSet<>();
		for (AssocUtilisateurSocket assoc : utilisateursConnectes)
			utilisateursCo.add(assoc.getUtilisateur());

		return utilisateursCo;
	}

	private ResultSet requeteBaseDeDonnees(String requete) throws SQLException {
		Pattern pattern = Pattern.compile("^INSERT INTO *");
		Matcher matcher = pattern.matcher(requete);
		if (matcher.find()) {
			baseDeDonnees.requeteInsertWithoutReturn(requete);
			return null;
		}

		pattern = Pattern.compile("^(UPDATE|DELETE) *");
		matcher = pattern.matcher(requete);
		if (matcher.find()) {
			baseDeDonnees.requeteUpdate(requete);
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
		String requete = "SELECT nom,prenom FROM Utilisateur WHERE idU = '" + texteToTexteSQL(idUtilisateur) + "'";

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
					"SELECT idT,createur,nomG FROM participer WHERE idU = '" + texteToTexteSQL(idUtilisateur) + "'");

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
				int idM = res.getInt("idM");
				boolean estLu = isMessageLuParUtilisateur(idM, idUtilisateur);
				MessageConversation m = new MessageConversation(idM, getUtilisateurFromId(res.getString("idU")),
						res.getString("texte"), new Date(res.getTimestamp("dateM").getTime()),
						EtatMessage.NON_RECU_PAR_TOUS, estLu);
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
			ResultSet res = requeteBaseDeDonnees("SELECT * FROM lire WHERE idM = " + m.getIdMessage() + " AND idU = '"
					+ texteToTexteSQL(idUtilisateur) + "'");
			return res.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	boolean isMessageLuParUtilisateur(int idM, String idUtilisateur) {
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT * FROM lire WHERE idM = " + idM + " AND idU = '" + texteToTexteSQL(idUtilisateur) + "'");
			return res.first();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return false;
	}

	boolean isMessageRecuParUtilisateur(MessageConversation m, String idUtilisateur) {
		try {
			ResultSet res = requeteBaseDeDonnees("SELECT * FROM recevoir WHERE idM = " + m.getIdMessage()
					+ " AND idU = '" + texteToTexteSQL(idUtilisateur) + "'");
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
			requeteBaseDeDonnees("INSERT INTO recevoir (idM, idU) VALUES (" + idMessage + ",'"
					+ texteToTexteSQL(idUtilisateur) + "')");

			m.setEtatGroupe(EtatMessage.NON_RECU_PAR_TOUS);
			m.setLuParUtilisateur(isMessageLuParUtilisateur(m, idUtilisateur));

			if (isMessageRecuParTous(idMessage)) {
				ResultSet res = requeteBaseDeDonnees("SELECT idT FROM message WHERE idM = " + idMessage);
				res.first();
				int idTicket = res.getInt(1);
				Set<String> participants = getParticipantsTickets(idTicket);

				if (!isMessageLuParTous(idMessage))
					m.setEtatGroupe(EtatMessage.NON_LU_PAR_TOUS);
				else
					m.setEtatGroupe(EtatMessage.LU_PAR_TOUS);

				res = requeteBaseDeDonnees("SELECT nomG FROM participer WHERE idT = " + idTicket);
				res.first();
				String nomGroupe = res.getString(1);

				for (String idU : participants) {
					Utilisateur u = getUtilisateurFromId(idU);
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().equals(u)) {
							ObjectOutputStream out = assoc.getOut();
							MessageConversation messageMain = new MessageConversation(m.getIdMessage(), m.getCreateur(),
									m.getTexte(), m.getDate(), m.getEtatGroupe(), isMessageLuParUtilisateur(m, idU));
							MessageMessageConversation mA = new MessageMessageConversation(idTicket, nomGroupe,
									messageMain);
							out.writeObject(mA);
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

	public List<Groupe> groupesUtilisateur(String idUtilisateur) {
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
			ResultSet res = requeteBaseDeDonnees("SELECT idU FROM participer WHERE idT = " + idTicket);

			for (; res.next();)
				participants.add(res.getString(1));

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
		setChanged();
		notifyObservers();
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
								+ "', '" + texteToTexteSQL(idCreateur) + "')",
						true);
				res.first();
				int idMessage = res.getInt(1);
				m.setIdMessage(idMessage);

				requeteBaseDeDonnees("INSERT INTO  lire (idM, idU) VALUES (" + m.getIdMessage() + ", '"
						+ texteToTexteSQL(idCreateur) + "')");
			}

			Set<String> participants = utilisateursGroupe(idGroupe);
			participants.add(idCreateur);

			for (String u : participants) {
				requeteBaseDeDonnees("INSERT INTO participer (idU,idT,nomG, createur) VALUES ('" + texteToTexteSQL(u)
						+ "', " + idTicket + ", '" + texteToTexteSQL(idGroupe) + "', '" + texteToTexteSQL(idCreateur)
						+ "')");
			}

			envoyerNouveauTicketConnectes(ticket, participants);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void envoyerNouveauTicketConnectes(Ticket ticket, Collection<String> idParticipants) {
		Set<String> connectes = new HashSet<>();

		for (String idU : idParticipants) {
			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				if (assoc.getUtilisateur().getIdUtilisateur().equals(idU)) {
					connectes.add(idU);
					ObjectOutputStream out = assoc.getOut();
					try {
						Set<MessageConversation> ensembleMessages = ticket.getFilDiscussion().getEnsembleMessage();
						for (MessageConversation m : ensembleMessages) {
							if (isMessageRecuParTous(m.getIdMessage())) {
								if (!isMessageLuParTous(m.getIdMessage()))
									m.setEtatGroupe(EtatMessage.NON_LU_PAR_TOUS);
								else
									m.setEtatGroupe(EtatMessage.LU_PAR_TOUS);
							} else {
								m.setEtatGroupe(EtatMessage.NON_RECU_PAR_TOUS);
							}
							m.setLuParUtilisateur(isMessageLuParUtilisateur(m, idU));
						}
						out.writeObject(new MessageTicket(ticket));
						out.flush();
						break;
					} catch (IOException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				}
			}
		}

		for (String u : connectes) {
			for (MessageConversation m : ticket.getFilDiscussion().getEnsembleMessage()) {
				messageRecu(m, u);
			}
		}
	}

	Set<String> utilisateursGroupe(String nomGroupe) {
		Set<String> ensembleUtilisateurs = new HashSet<>();
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idU FROM appartenir WHERE nomG = '" + texteToTexteSQL(nomGroupe) + "'");
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
							+ "', '" + new java.sql.Timestamp(new java.util.Date().getTime()) + "' , "
							+ message.getIdTicket() + ", '" + texteToTexteSQL(createur.getIdUtilisateur()) + "')",
					true);
			res.first();
			int idMessage = res.getInt(1);
			messageConv.setIdMessage(idMessage);

			requeteBaseDeDonnees("INSERT INTO lire (idM, idU) VALUES (" + idMessage + ", '"
					+ texteToTexteSQL(createur.getIdUtilisateur()) + "')");
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

					messageConversation.setLuParUtilisateur(idU.equals(idCreateur));
					messageConversation.setEtatGroupe(EtatMessage.NON_RECU_PAR_TOUS);
					messageConversation.setLuParUtilisateur(isMessageLuParUtilisateur(messageConversation, idU));
					try {
						out.writeObject(new MessageMessageConversation(idTicket, nomGroupe, messageConversation));
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					messageRecu(messageConversation, idU);

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
			MessageConversation messageLu = messageConversationFromId(idM);
			requeteBaseDeDonnees(
					"INSERT INTO lire (idM, idU) VALUES (" + idM + ", '" + texteToTexteSQL(idUtilisateur) + "')");

			ResultSet res = requeteBaseDeDonnees("SELECT idT FROM message WHERE idM = " + idM);
			res.first();

			int idTicket = res.getInt(1);

			res = requeteBaseDeDonnees("SELECT nomG FROM participer WHERE idT = " + idTicket);
			res.first();
			String nomGroupe = res.getString(1);

			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				if (assoc.getUtilisateur().getIdUtilisateur().equals(idUtilisateur)) {
					messageLu.setLuParUtilisateur(true);
					definirEtatMessageConversation(messageLu, idTicket, idUtilisateur);
					ObjectOutputStream out = assoc.getOut();
					out.writeObject(new MessageMessageConversation(idTicket, nomGroupe, messageLu));
					out.flush();
					break;
				}
			}

			if (isMessageLuParTous(idM)) {

				Set<String> participants = getParticipantsTickets(idTicket);

				messageLu.setEtatGroupe(EtatMessage.LU_PAR_TOUS);

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

		String requete = "SELECT mdp FROM Utilisateur WHERE idU = '" + texteToTexteSQL(message.getIdUtilisateur())
				+ "'";

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
			res = requeteBaseDeDonnees("SELECT idU FROM appartenir WHERE nomG = 'Administrateur'");
			for (; res.next();) {
				if (res.getString(1).equals(idUtilisateur)) {
					ResultSet r = requeteBaseDeDonnees(
							"SELECT mdp FROM Utilisateur WHERE idU = '" + texteToTexteSQL(idUtilisateur) + "'");
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
			requeteBaseDeDonnees("INSERT INTO groupe (nomG) VALUES ('" + texteToTexteSQL(nomGroupe) + "')");
			Groupe g = new Groupe(nomGroupe);

			for (Utilisateur u : utilisateurs) {
				requeteBaseDeDonnees("INSERT INTO appartenir (idU, nomG) VALUES ('"
						+ texteToTexteSQL(u.getIdUtilisateur()) + "', '" + texteToTexteSQL(nomGroupe) + "')");
				g.ajouterUtilisateurs(u);
			}

			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				ObjectOutputStream out = assoc.getOut();
				out.writeObject(new MessageGroupe(g, true));
				out.flush();
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Groupe getGroupeFromNomGroupe(String nomGroupe) throws SQLException {
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT * FROM groupe WHERE nomG = '" + texteToTexteSQL(nomGroupe) + "'");
			if (res.first()) {
				Groupe g = new Groupe(nomGroupe);
				res = requeteBaseDeDonnees(
						"SELECT idU FROM appartenir WHERE nomG = '" + texteToTexteSQL(nomGroupe) + "'");
				for (; res.next();) {
					g.ajouterUtilisateurs(getUtilisateurFromId(res.getString(1)));
				}

				return g;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return null;
	}

	public Set<String> getGroupes() {
		Set<String> ensembleGroupes = new HashSet<>();
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT nomG FROM groupe");

			for (; res.next();) {
				ensembleGroupes.add(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ensembleGroupes;
	}

	public Set<Utilisateur> getUtilisateurs() {
		Set<Utilisateur> ensembleUtilisateurs = new HashSet<>();
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idU FROM utilisateur");

			for (; res.next();) {
				ensembleUtilisateurs.add(getUtilisateurFromId(res.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ensembleUtilisateurs;
	}

	public void creerUtilisateur(String idU, String nom, String prenom, String mdp, Collection<String> nomGroupes) {
		try {
			requeteBaseDeDonnees("INSERT INTO utilisateur (idU, nom, prenom, mdp) VALUES ('" + texteToTexteSQL(idU)
					+ "', '" + texteToTexteSQL(nom) + "','" + texteToTexteSQL(prenom) + "', '" + texteToTexteSQL(mdp)
					+ "')");

			for (String nomG : nomGroupes) {
				requeteBaseDeDonnees("INSERT INTO appartenir (idU, nomG) VALUES ('" + texteToTexteSQL(idU) + "', '"
						+ texteToTexteSQL(nomG) + "')");
				ResultSet res = requeteBaseDeDonnees("SELECT idT,createur FROM participer WHERE nomG = '"
						+ texteToTexteSQL(nomG) + "' GROUP BY idT");
				for (; res.next();) {
					int idTicket = res.getInt("idT");
					String createur = res.getString("createur");
					requeteBaseDeDonnees("INSERT INTO participer (idU, idT, nomG, createur) VALUES ('"
							+ texteToTexteSQL(idU) + "', " + idTicket + ", '" + texteToTexteSQL(nomG) + "', '"
							+ texteToTexteSQL(createur) + "')");

					Set<String> utilisateurs = getParticipantsTickets(idTicket);

					for (String u : utilisateurs) {
						for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
							AssocUtilisateurSocket assoc = ite.next();
							if (assoc.getUtilisateur().getIdUtilisateur().equals(u)) {
								Ticket t = buildTicket(idTicket, createur, nomG, u);
								ObjectOutputStream out = assoc.getOut();
								out.writeObject(new MessageTicket(t, true));
								out.flush();
								break;
							}
						}
					}

				}
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modificationGroupe(String nomGroupe, String nouveauNomGroupe, Collection<Utilisateur> utilisateurs) {

		boolean modificationNomGroupe = !nomGroupe.equals(nouveauNomGroupe);

		Groupe ancienG = new Groupe(nomGroupe);
		Groupe nouveauG = new Groupe(nouveauNomGroupe);

		try {
			Set<String> ancienUtilisateursGroupe = getUtilisateursGroupe(nomGroupe);

			if (modificationNomGroupe) {
				requeteBaseDeDonnees("INSERT INTO groupe (nomG) VALUES ('" + texteToTexteSQL(nouveauNomGroupe) + "')");

				ResultSet res = requeteBaseDeDonnees(
						"SELECT idU FROM appartenir WHERE nomG = '" + texteToTexteSQL(nomGroupe) + "'");
				for (; res.next();) {
					requeteBaseDeDonnees(
							"INSERT INTO appartenir (idU, nomG) VALUES ('" + texteToTexteSQL(res.getString("idU"))
									+ "', '" + texteToTexteSQL(nouveauNomGroupe) + "')");

				}

				res = requeteBaseDeDonnees(
						"SELECT idU, idT,createur FROM participer WHERE nomG = '" + texteToTexteSQL(nomGroupe) + "'");
				for (; res.next();) {
					requeteBaseDeDonnees("INSERT INTO participer (idU, nomG, idT, createur) VALUES ('"
							+ texteToTexteSQL(res.getString("idU")) + "', '" + texteToTexteSQL(nouveauNomGroupe) + "', "
							+ res.getInt("idT") + ", '" + texteToTexteSQL(res.getString("createur")) + "')");

				}

				requeteBaseDeDonnees("DELETE FROM groupe WHERE nomG = '" + texteToTexteSQL(nomGroupe) + "'");
			}

			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				ObjectOutputStream out = assoc.getOut();
				out.writeObject(new MessageGroupe(ancienG, false));
				out.flush();
				out.writeObject(new MessageGroupe(nouveauG, true));
			}

			// Si l'utilisateur ne fait plus parti des participants
			for (String u : ancienUtilisateursGroupe) {
				if (!containsUtilisateurById(utilisateurs, u)) {
					ResultSet res = requeteBaseDeDonnees(
							"SELECT idT FROM participer WHERE idU = '" + texteToTexteSQL(u) + "' AND idU != createur");

					for (; res.next();) {
						int idTicket = res.getInt(1);
						requeteBaseDeDonnees("DELETE FROM participer WHERE idT = " + idTicket + " AND idU = '"
								+ texteToTexteSQL(u) + "'");
					}

					requeteBaseDeDonnees("DELETE FROM appartenir WHERE nomG = '" + texteToTexteSQL(nouveauNomGroupe)
							+ "' AND idU = '" + texteToTexteSQL(u) + "'");
				}
			}

			// Si l'utilisateur est nouvellement ajouté au groupe
			for (Utilisateur utilisateur : utilisateurs) {
				String u = utilisateur.getIdUtilisateur();

				if (!ancienUtilisateursGroupe.contains(u)) {
					requeteBaseDeDonnees("INSERT INTO appartenir (idU, nomG) VALUES ('" + texteToTexteSQL(u) + "', '"
							+ texteToTexteSQL(nouveauNomGroupe) + "')");

					ResultSet res = requeteBaseDeDonnees("SELECT idT, createur FROM participer WHERE nomG = '"
							+ texteToTexteSQL(nouveauNomGroupe) + "' AND createur != '" + texteToTexteSQL(u) + "'");

					for (; res.next();) {
						requeteBaseDeDonnees("INSERT INTO participer (idU, nomG, idT, createur) VALUES ('"
								+ texteToTexteSQL(u) + "', '" + texteToTexteSQL(nouveauNomGroupe) + "', "
								+ res.getInt("idT") + ", '" + texteToTexteSQL(res.getString("createur")) + "')");
					}
				}
			}

			// Envoi des tickets

			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				ObjectOutputStream out = assoc.getOut();
				Utilisateur utilisateur = assoc.getUtilisateur();
				String u = utilisateur.getIdUtilisateur();
				List<Ticket> ticketsU = ticketsGroupeUtilisateur(nouveauNomGroupe, u);
				for (Ticket t : ticketsU) {
					NavigableSet<MessageConversation> messagesTicket = t.getFilDiscussion().getEnsembleMessage();
					for (MessageConversation m : messagesTicket) {
						if (!isMessageRecuParUtilisateur(m, u)) {
							messageRecu(m, u);
						}
					}
					out.writeObject(new MessageTicket(t));
					out.flush();
				}
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<Ticket> ticketsGroupeUtilisateur(String idGroupe, String idU) {
		List<Ticket> listeTickets = new ArrayList<>();

		try {
			ResultSet res = requeteBaseDeDonnees("SELECT * FROM participer WHERE nomG = '" + texteToTexteSQL(idGroupe)
					+ "' AND idU = '" + texteToTexteSQL(idU) + "'");

			for (; res.next();) {
				Ticket t = buildTicket(res.getInt("idT"), res.getString("createur"), res.getString("nomG"),
						res.getString("idU"));
				listeTickets.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeTickets;
	}

	private boolean containsUtilisateurById(Collection<? extends Utilisateur> collection, String id) {
		for (Iterator<? extends Utilisateur> ite = collection.iterator(); ite.hasNext();) {
			Utilisateur u = ite.next();
			if (u.getIdUtilisateur().equals(id))
				return true;
		}

		return false;
	}

	private Set<String> getUtilisateursGroupe(String nomGroupe) throws SQLException {
		Set<String> utilisateurs = new HashSet<>();
		ResultSet res;
		try {
			res = requeteBaseDeDonnees("SELECT idU FROM appartenir WHERE nomG = '" + nomGroupe + "'");
			for (; res.next();)
				utilisateurs.add(res.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return utilisateurs;
	}

	public void modificationUtilisateur(String idU, String nom, String prenom, String mdp, Collection<String> groupes) {
		try {
			if (!mdp.equals(""))
				requeteBaseDeDonnees("UPDATE utilisateur SET nom = '" + nom + "', prenom = '" + prenom + "', mdp = '"
						+ mdp + "'	 WHERE idU = '" + idU + "'");
			else
				requeteBaseDeDonnees("UPDATE utilisateur SET nom = '" + nom + "', prenom = '" + prenom
						+ "' WHERE idU = '" + idU + "'");

			ResultSet res = requeteBaseDeDonnees("SELECT idT,createur, nomG FROM participer WHERE idU = '"
					+ texteToTexteSQL(idU) + "' GROUP BY idT");

			List<Groupe> groupesUt = groupesUtilisateur(idU);

			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				if (assoc.getUtilisateur().getIdUtilisateur().equals(idU)) {
					ObjectOutputStream out = assoc.getOut();
					for (Groupe g : groupesUt) {
						if (!groupes.contains(g.getIdGroupe())) {
							out.writeObject(new MessageGroupe(g, false));
						}
					}
				}
			}

			for (Groupe g : groupesUt) {
				if (!groupes.contains(g.getIdGroupe())) {
					supprimerUtilisateurGroupe(idU, g);
				}
			}

			for (String g : groupes) {
				if (!groupesUt.contains(new Groupe(g))) {
					Collection<String> idUtilisateursGroupe = getUtilisateursGroupe(g);
					Set<Utilisateur> utilisateursGroupe = new HashSet<>();
					idUtilisateursGroupe.add(idU);
					for (String u : idUtilisateursGroupe)
						utilisateursGroupe.add(getUtilisateurFromId(u));

					modificationGroupe(g, g, utilisateursGroupe);
				}
			}

			for (; res.next();) {
				int idTicket = res.getInt("idT");
				Set<String> participantsTicket = getParticipantsTickets(idTicket);
				for (String utilisateur : participantsTicket) {
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().getIdUtilisateur().equals(utilisateur)) {
							Ticket t = buildTicket(idTicket, res.getString("createur"), res.getString("nomG"),
									utilisateur);
							NavigableSet<MessageConversation> messagesTicket = t.getFilDiscussion()
									.getEnsembleMessage();
							for (MessageConversation m : messagesTicket) {
								if (!isMessageRecuParUtilisateur(m, utilisateur)) {
									messageRecu(m, utilisateur);
								}
							}
							ObjectOutputStream out = assoc.getOut();
							out.writeObject(new MessageTicket(t));
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

	private void supprimerUtilisateurGroupe(String idU, Groupe g) {
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT A.idM FROM recevoir AS A JOIN (SELECT idM FROM message AS A JOIN (SELECT * FROM participer WHERE nomG = '"
							+ texteToTexteSQL(g.getIdGroupe())
							+ "') AS B ON A.idT = B.idT Group BY idM ) AS B ON A.idM = B.idM WHERE idU = '"
							+ texteToTexteSQL(idU) + "'");
			for (; res.next();) {
				requeteBaseDeDonnees("DELETE FROM recevoir WHERE idM = " + res.getInt(1) + " AND idU = '" + idU + "'");
			}

			res = requeteBaseDeDonnees(
					"SELECT lire.idM FROM lire JOIN (SELECT idM FROM message AS B JOIN (SELECT idT FROM participer WHERE nomG = '"
							+ texteToTexteSQL(g.getIdGroupe())
							+ "') AS A ON B.idT = A.idT) AS A ON lire.idM = A.idM WHERE idU = '" + texteToTexteSQL(idU)
							+ "' GROUP BY lire.idM");
			for (; res.next();) {
				requeteBaseDeDonnees("DELETE FROM lire WHERE idM = " + res.getInt(1) + " AND idU = '" + idU + "'");
			}

			res = requeteBaseDeDonnees(
					"SELECT idT,createur FROM participer WHERE nomG = '" + texteToTexteSQL(g.getIdGroupe())
							+ "' AND ( idU = '" + texteToTexteSQL(idU) + "' AND idU != createur ) GROUP BY idT");

			for (; res.next();) {
				int idTicket = res.getInt("idT");
				requeteBaseDeDonnees(
						"DELETE FROM participer WHERE idT = " + idTicket + " AND idU = '" + texteToTexteSQL(idU) + "'");
				for (String idUtilisateur : getParticipantsTickets(idTicket)) {
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().getIdUtilisateur().equals(idUtilisateur)) {
							Ticket t = buildTicket(idTicket, res.getString("createur"), g.getIdGroupe(), idUtilisateur);
							ObjectOutputStream out = assoc.getOut();
							out.writeObject(new MessageTicket(t));
							out.flush();
							break;
						}
					}

				}
			}
			requeteBaseDeDonnees("DELETE FROM appartenir WHERE nomG ='" + texteToTexteSQL(g.getIdGroupe())
					+ "' AND idU = '" + texteToTexteSQL(idU) + "'");

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprimerUtilisateur(String idUtilisateur) {
		try {
			ResultSet res = requeteBaseDeDonnees(
					"SELECT nomG FROM appartenir WHERE idU = '" + texteToTexteSQL(idUtilisateur) + "'");
			for (; res.next();) {
				supprimerUtilisateurGroupe(idUtilisateur, new Groupe(res.getString(1)));
			}

			res = requeteBaseDeDonnees("SELECT idT, nomG FROM participer WHERE idU = '" + texteToTexteSQL(idUtilisateur)
					+ "' AND idU = createur");

			for (; res.next();) {
				int idTicket = res.getInt(1);
				for (String idU : getParticipantsTickets(idTicket)) {
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().getIdUtilisateur().equals(idU)) {
							Ticket t = buildTicket(idTicket, idUtilisateur, res.getString("nomG"), idU);
							ObjectOutputStream out = assoc.getOut();
							out.writeObject(new MessageTicket(t, false));
							out.flush();
							break;
						}
					}

				}
				requeteBaseDeDonnees("DELETE FROM ticket WHERE idT = " + res.getInt(1));
			}

			res = requeteBaseDeDonnees(
					"SELECT idT FROM message WHERE idU = '" + texteToTexteSQL(idUtilisateur) + "' GROUP BY idT");

			requeteBaseDeDonnees("DELETE FROM utilisateur WHERE idU = '" + texteToTexteSQL(idUtilisateur) + "'");

			for (; res.next();) {
				int idTicket = res.getInt(1);

				ResultSet r = requeteBaseDeDonnees(
						"SELECT createur FROM participer WHERE idT = " + idTicket + " GROUP BY idT");
				r.first();
				String idCreateur = r.getString(1);

				for (String idU : getParticipantsTickets(idTicket)) {
					for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
						AssocUtilisateurSocket assoc = ite.next();
						if (assoc.getUtilisateur().getIdUtilisateur().equals(idU)) {
							Ticket t = buildTicket(idTicket, idCreateur, nomGroupeFromIdTicket(idTicket), idU);
							NavigableSet<MessageConversation> messagesTicket = t.getFilDiscussion()
									.getEnsembleMessage();
							for (MessageConversation m : messagesTicket) {
								if (!isMessageRecuParUtilisateur(m, idU)) {
									messageRecu(m, idU);
								}
							}
							ObjectOutputStream out = assoc.getOut();
							out.writeObject(new MessageTicket(t, false));
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

	public void supprimerGroupe(String nomG) throws SQLException, IOException {
		try {
			ResultSet res = requeteBaseDeDonnees("SELECT idT FROM participer WHERE nomG = '" + nomG + "' GROUP BY idT");
			for (; res.next();) {
				requeteBaseDeDonnees("DELETE FROM ticket WHERE idT = " + res.getInt(1));
			}

			for (Iterator<AssocUtilisateurSocket> ite = utilisateursConnectes.iterator(); ite.hasNext();) {
				AssocUtilisateurSocket assoc = ite.next();
				ObjectOutputStream out = assoc.getOut();
				out.writeObject(new MessageGroupe(new Groupe(nomG), false));
			}
			requeteBaseDeDonnees("DELETE FROM groupe WHERE nomG = '" + nomG + "'");
		} catch (SQLException |

				IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	public void deconnecterServeur() {
		for (AssocUtilisateurSocket assoc : utilisateursConnectes)
			deconnexionUtilisateur(assoc);
		baseDeDonnees.close();
		for (Thread t : listeThreads) {
			t.interrupt();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
}
