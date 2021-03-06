/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import client.Client;
import client.ThreadEcoute;
import message.MessageMessageConversation;
import message.MessageMiseAJourEtat;
import modele.EtatMessage;
import modele.Groupe;
import modele.MessageConversation;
import modele.Ticket;
import modele.Utilisateur;

/**
 *
 * @author Lucas
 */
public class FenetreClient extends javax.swing.JFrame implements Observer {

	/**
	 * Creates new form FenetreClient
	 */
	private final Client client;
	private final ThreadEcoute thread;
	private Ticket ticketRecuSelect;
	private Ticket ticketCreeSelect;
	private Ticket ticketSelect;
	private String groupeRecuSelect;
	private String groupeCreeSelect;
	private String groupeSelect;
	private DefaultTableModel tableModele;

	public FenetreClient(Client client, ThreadEcoute thread) {
		this.client = client;
		this.thread = thread;
		initComponents();
		this.client.addObserver(this);
		thread.start();
		ImageIcon img = new ImageIcon("icon.jpg");
		this.setIconImage(img.getImage());

	}

	@Override
	public void update(Observable o, Object arg) {
		if (ticketsCreesTree != null) {
			ticketsCreesTree.setModel(new javax.swing.tree.DefaultTreeModel(getArbreModelCrees()));
		}
		if (ticketsRecusTree != null) {
			ticketsRecusTree.setModel(new javax.swing.tree.DefaultTreeModel(getArbreModelRecus()));
		}
		if (ticketsAllTree != null) {
			ticketsAllTree.setModel(new javax.swing.tree.DefaultTreeModel(getArbreModelAll()));
		}

		if (ongletsDiscu != null) {
			int onglet = ongletsDiscu.getSelectedIndex();
			if (onglet == 1) {
				if (ticketCreeSelect != null)
					setLignes(ticketCreeSelect);
			}
			if (onglet == 2) {
				if (ticketRecuSelect != null)
					setLignes(ticketRecuSelect);
			}
			if (onglet == 0) {
				if (ticketSelect != null)
					setLignes(ticketSelect);
			}
		}
		this.validate();
	}

	public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (table.getRowCount() != 0 && table.getColumnCount() != 0) {
				if (value.getClass() != MessageConversation.class) {
					this.setText((String) value);
				}
				// Gestion des couleurs des messages
				String etat = (String) table.getValueAt(row, 3);
				if (etat != null) {
					switch (etat) {
					case "LU_PAR_TOUS":
						setBackground(Color.green);
						break;
					case "NON_LU_PAR_TOUS":
						setBackground(Color.orange);
						break;
					case "NON_RECU_PAR_TOUS":
						setBackground(Color.red);
						break;
					case "NON_RECU_PAR_LE_SERVEUR":
						setBackground(Color.GRAY);
						break;
					default:
						setBackground(Color.white);
					}
				}
				// Masquer la colonne d'état et de l'objet Message
				table.getColumnModel().getColumn(3).setMinWidth(0);
				table.getColumnModel().getColumn(3).setMaxWidth(0);
				table.getColumnModel().getColumn(4).setMinWidth(0);
				table.getColumnModel().getColumn(4).setMaxWidth(0);

				this.setWrapStyleWord(true);
				this.setLineWrap(true);
				this.setColumns(10);
				int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
				int textLength = this.getText().length();
				int lines = textLength / (this.getColumns()) + 1;// +1, cause we
																	// need at
																	// least 1
																	// row.

				int height = fontHeight * lines;
				// table.setRowHeight(row, height);
				this.setEditable(false);
			}
			return this;
		}

	}

	public class MyTreeCellRender extends DefaultTreeCellRenderer {
		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			if (tree != null && value != null && tree.getRowCount() != 0) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				// On cherche le nombre de message non lu dans le ticket
				int nbNonLu = 0;
				Dimension preferredSize = new Dimension(150, 20);
				this.setPreferredSize(preferredSize);
				if (((DefaultMutableTreeNode) value).getUserObject().getClass().equals(Ticket.class)) {
					Ticket tic = (Ticket) ((DefaultMutableTreeNode) value).getUserObject();
					if (tic.getIdTicket() != -1) {
						NavigableSet<MessageConversation> ensembleMessage = tic.getFilDiscussion().getEnsembleMessage();
						// Pour chaque message, on regarde s'il a été lu
						if (ensembleMessage != null) {
							for (MessageConversation mess : ensembleMessage) {
								if (!mess.isLuParUtilisateur()) {
									nbNonLu++;
								}
							}
							if (nbNonLu > 0) {
								this.setText(value.toString() + " (" + nbNonLu + ") ");
								this.setFont(new Font("Tahoma", Font.BOLD, 12));
								nbNonLu = 0;
							} else {
								this.setFont(new Font("Tahoma", Font.PLAIN, 12));
							}
						}
					}

				}
				// Si le noeud est un groupe
				if (((DefaultMutableTreeNode) value).getUserObject().getClass().equals(Groupe.class)) {
					Groupe gr = (Groupe) ((DefaultMutableTreeNode) value).getUserObject();
					int nbNonLuGr = 0;
					if (tree.equals(ticketsCreesTree)) {
						nbNonLuGr = client.getNombreMessageGroupeNonLu(client.getTicketsCree(), gr);
					}
					if (tree.equals(ticketsRecusTree)) {
						nbNonLuGr = client.getNombreMessageGroupeNonLu(client.getTicketsRecu(), gr);
					}
					if (tree.equals(ticketsAllTree)) {
						nbNonLuGr = client.getNombreMessageGroupeNonLu(client.getTicketsTous(), gr);
					}
					if (nbNonLuGr > 0) {
						this.setText(value.toString() + " (" + nbNonLuGr + ") ");
						this.setFont(new Font("Tahoma", Font.BOLD, 12));
						nbNonLuGr = 0;
					} else {
						this.setFont(new Font("Tahoma", Font.PLAIN, 12));
					}

				}
			}

			return this;
		}

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jScrollPane4 = new javax.swing.JScrollPane();
		discussionArea = new javax.swing.JTextArea();
		creerTicketButton = new javax.swing.JButton();
		decoButton = new javax.swing.JButton();
		ongletsDiscu = new javax.swing.JTabbedPane();
		jScrollPane5 = new javax.swing.JScrollPane();
		ticketsAllTree = new javax.swing.JTree();
		jScrollPane1 = new javax.swing.JScrollPane();
		ticketsCreesTree = new javax.swing.JTree();
		jScrollPane2 = new javax.swing.JScrollPane();
		ticketsRecusTree = new javax.swing.JTree();
		jScrollPane3 = new javax.swing.JScrollPane();
		saisieDiscuArea = new javax.swing.JTextArea();
		envoyerButton = new javax.swing.JButton();
		titreDiscuLabel = new javax.swing.JLabel();
		usernameLabel = new javax.swing.JLabel();
		jScrollPane6 = new javax.swing.JScrollPane();
		discussionTable2 = new javax.swing.JTable();

		discussionArea.setColumns(20);
		discussionArea.setRows(5);
		discussionArea.setText("Peut etre generer un text area pour chaque message");
		jScrollPane4.setViewportView(discussionArea);

		setTitle("Client");
		setMinimumSize(new java.awt.Dimension(800, 600));
		setPreferredSize(new java.awt.Dimension(800, 600));
		getContentPane().setLayout(new java.awt.GridBagLayout());

		creerTicketButton.setText("Créer nouveau ticket");
		creerTicketButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				creerTicketButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
		getContentPane().add(creerTicketButton, gridBagConstraints);

		decoButton.setText("Déconnexion");
		decoButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				decoButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 4);
		getContentPane().add(decoButton, gridBagConstraints);

		ticketsAllTree.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		ticketsAllTree.setModel(new javax.swing.tree.DefaultTreeModel(getArbreModelAll()));
		ticketsAllTree.setCellRenderer(new MyTreeCellRender());
		ticketsAllTree.setRootVisible(false);
		ticketsAllTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				ticketsAllTreeValueChanged(evt);
			}
		});
		jScrollPane5.setViewportView(ticketsAllTree);

		ongletsDiscu.addTab("Tous", jScrollPane5);

		ticketsCreesTree.setModel(new javax.swing.tree.DefaultTreeModel(getArbreModelCrees()));
		ticketsCreesTree.setCellRenderer(new MyTreeCellRender());
		ticketsCreesTree.setRootVisible(false);
		ticketsCreesTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				ticketsCreesTreeValueChanged(evt);
			}
		});
		jScrollPane1.setViewportView(ticketsCreesTree);

		ongletsDiscu.addTab("Créés", jScrollPane1);

		ticketsRecusTree.setModel(new javax.swing.tree.DefaultTreeModel(getArbreModelRecus()));
		ticketsRecusTree.setCellRenderer(new MyTreeCellRender());
		ticketsRecusTree.setRootVisible(false);
		ticketsRecusTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				ticketsRecusTreeValueChanged(evt);
			}
		});
		jScrollPane2.setViewportView(ticketsRecusTree);

		ongletsDiscu.addTab("Reçus", jScrollPane2);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 145;
		gridBagConstraints.ipady = 211;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(36, 20, 0, 0);
		getContentPane().add(ongletsDiscu, gridBagConstraints);

		saisieDiscuArea.setColumns(20);
		saisieDiscuArea.setRows(5);
		saisieDiscuArea.setToolTipText("écire un message...");
		saisieDiscuArea.setMinimumSize(new java.awt.Dimension(15, 22));
		saisieDiscuArea.setPreferredSize(new java.awt.Dimension(300, 94));
		saisieDiscuArea.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				saisieDiscuAreaKeyPressed(evt);
			}
		});
		jScrollPane3.setViewportView(saisieDiscuArea);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 317;
		gridBagConstraints.ipady = 15;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(9, 10, 4, 0);
		getContentPane().add(jScrollPane3, gridBagConstraints);

		envoyerButton.setText("Envoyer");
		this.getRootPane().setDefaultButton(envoyerButton);
		envoyerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				envoyerButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(4, 10, 0, 0);
		getContentPane().add(envoyerButton, gridBagConstraints);

		titreDiscuLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		titreDiscuLabel.setText("Discussion");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.ipadx = 327;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(37, 20, 0, 0);
		getContentPane().add(titreDiscuLabel, gridBagConstraints);

		usernameLabel.setText("Prénom Nom");
		if (client != null) {
			Utilisateur util = client.getUtilisateurClient();
			if (client.getUtilisateurClient() != null) {
				usernameLabel.setText(util.getPrenom() + " " + util.getNom());
			}
		}
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.ipady = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
		getContentPane().add(usernameLabel, gridBagConstraints);

		discussionTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		discussionTable2.setRowHeight(50);
		// Gestion clic de ligne
		discussionTable2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (discussionTable2.getSelectedRow() > -1) {
					MessageConversation mess = (MessageConversation) discussionTable2
							.getValueAt(discussionTable2.getSelectedRow(), 4);
					if (!mess.isLuParUtilisateur()) {
						MessageMiseAJourEtat messMaj = new MessageMiseAJourEtat(ticketSelect.getIdTicket(),
								mess.getIdMessage(), client.getUtilisateurClient().getIdUtilisateur(),
								mess.getEtatGroupe(), true);
						// envoi du message de mise a jour lu
						client.getReseaux().envoyerMessage(messMaj);
					}
				}
			}
		});
		jScrollPane6.setViewportView(discussionTable2);
		tableModele = new DefaultTableModel();
		setTableModel();
		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment quitter ?", "Exit Confirmation",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					ThreadEcoute.stopper(thread);
					client.getReseaux().deconnexionServeur(client.getUtilisateurClient().getIdUtilisateur());
					System.exit(0);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		};
		this.addWindowListener(exitListener);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 400;
		gridBagConstraints.ipady = 243;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
		getContentPane().add(jScrollPane6, gridBagConstraints);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void envoyerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_envoyerButtonActionPerformed
		// Envoyer le message
		if (client != null && !saisieDiscuArea.getText().equals("")) {
			Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
			MessageConversation messConv = new MessageConversation(-1, client.getUtilisateurClient(),
					saisieDiscuArea.getText(), date, EtatMessage.NON_RECU_PAR_LE_SERVEUR, true);
			// Recuperer ticket selectionné dans l'arbre
			// onglet 1 = crees, onglet 2 = recus, 0 = all
			int onglet = ongletsDiscu.getSelectedIndex();
			if (onglet == 1) {
				client.ajouterMessageConv(ticketCreeSelect.getIdTicket(), ticketCreeSelect.getGroupe().getIdGroupe(),
						messConv);
				MessageMessageConversation messTicket = new MessageMessageConversation(ticketCreeSelect.getIdTicket(),
						messConv);
				client.getReseaux().envoyerMessage(messTicket);
			}
			if (onglet == 2) {
				client.ajouterMessageConv(ticketRecuSelect.getIdTicket(), ticketRecuSelect.getGroupe().getIdGroupe(),
						messConv);
				MessageMessageConversation messEnvoye = new MessageMessageConversation(ticketRecuSelect.getIdTicket(),
						messConv);
				client.getReseaux().envoyerMessage(messEnvoye);
			}
			if (onglet == 0) {
				client.ajouterMessageConv(ticketSelect.getIdTicket(), ticketSelect.getGroupe().getIdGroupe(), messConv);
				MessageMessageConversation messEnvoye = new MessageMessageConversation(ticketSelect.getIdTicket(),
						messConv);
				client.getReseaux().envoyerMessage(messEnvoye);
			}
			// On efface le texte de la saisie
			saisieDiscuArea.setText("");
		}

	}// GEN-LAST:event_envoyerButtonActionPerformed

	private void creerTicketButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_creerTicketButtonActionPerformed
		// Affichage d'une fenetre de creation de ticket
		if (client != null) {
			FenetreClientNouvTicket fenNewTicket = new FenetreClientNouvTicket(client);
			fenNewTicket.setVisible(true);
		}
	}// GEN-LAST:event_creerTicketButtonActionPerformed

	private void decoButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_decoButtonActionPerformed
		if (client != null) {
			ThreadEcoute.stopper(thread);
			client.getReseaux().deconnexionServeur(client.getUtilisateurClient().getIdUtilisateur());
			FenetreClientConnexion fen = new FenetreClientConnexion();
			fen.setVisible(true);
			this.dispose();
		}

	}// GEN-LAST:event_decoButtonActionPerformed

	private void ticketsRecusTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {// GEN-FIRST:event_ticketsRecusTreeValueChanged
		if (evt.isAddedPath()) {
			TreePath path = evt.getPath();
			Object treeNode = path.getLastPathComponent();
			Object userObject = ((DefaultMutableTreeNode) treeNode).getUserObject();
			if (userObject instanceof Ticket) {
				Ticket node1 = (Ticket) userObject;
				String node2 = evt.getNewLeadSelectionPath().getParentPath().getLastPathComponent().toString();
				ticketRecuSelect = node1;
				groupeRecuSelect = node2;
				titreDiscuLabel.setText(ticketRecuSelect.getNom());
				ticketSelect = ticketRecuSelect;
				// Afficher la discussion correspondante
				setLignes(ticketRecuSelect);

			}

		}
	}// GEN-LAST:event_ticketsRecusTreeValueChanged

	private void ticketsCreesTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {// GEN-FIRST:event_ticketsCreesTreeValueChanged
		if (evt.isAddedPath()) {
			TreePath path = evt.getPath();
			Object treeNode = path.getLastPathComponent();
			Object userObject = ((DefaultMutableTreeNode) treeNode).getUserObject();
			if (userObject instanceof Ticket) {
				Ticket node1 = (Ticket) userObject;
				String node2 = evt.getNewLeadSelectionPath().getParentPath().getLastPathComponent().toString();
				ticketCreeSelect = node1;
				groupeCreeSelect = node2;
				titreDiscuLabel.setText(ticketCreeSelect.getNom());
				ticketSelect = ticketCreeSelect;
				// Afficher la discussion correspondante
				setLignes(ticketCreeSelect);
			}
		}

	}// GEN-LAST:event_ticketsCreesTreeValueChanged

	private void ticketsAllTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {// GEN-FIRST:event_ticketsAllTreeValueChanged
		if (evt.isAddedPath()) {
			TreePath path = evt.getPath();
			Object treeNode = path.getLastPathComponent();
			Object userObject = ((DefaultMutableTreeNode) treeNode).getUserObject();
			if (userObject instanceof Ticket) {
				Ticket node1 = (Ticket) userObject;
				String node2 = evt.getNewLeadSelectionPath().getParentPath().getLastPathComponent().toString();
				ticketSelect = node1;
				groupeSelect = node2;
				titreDiscuLabel.setText(ticketSelect.getNom());
				// Afficher la discussion correspondante
				setLignes(ticketSelect);

			}

		}
	}// GEN-LAST:event_ticketsAllTreeValueChanged

	private void saisieDiscuAreaKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_saisieDiscuAreaKeyPressed
		if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
			envoyerButton.doClick();
		}
	}// GEN-LAST:event_saisieDiscuAreaKeyPressed

	private void setLignes(Ticket ticketSelect) {
		// On vide la table des discussions
		clearTable();
		NavigableSet<MessageConversation> ensembleMessage = ticketSelect.getFilDiscussion().getEnsembleMessage();
		if (ensembleMessage != null && !ensembleMessage.isEmpty()) {
			for (MessageConversation mess : ensembleMessage) {
				Object[] ligne = new Object[5];
				ligne[0] = mess.getCreateur().getPrenom() + " " + mess.getCreateur().getNom();
				ligne[1] = mess.getTexte();
				boolean lu = mess.isLuParUtilisateur();
				String estLu = "(Non lu)";
				if (lu) {
					estLu = "(Lu)";
				}
				Timestamp timeStampDate = new Timestamp(mess.getDate().getTime());
				String laDate = timeStampDate.toLocalDateTime()
						.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy kk:mm", Locale.FRENCH));
				ligne[2] = laDate + " " + estLu;
				ligne[3] = mess.getEtatGroupe().toString();
				ligne[4] = mess;
				tableModele.addRow(ligne);
			}
			discussionTable2.setModel(tableModele);
			tableModele.fireTableDataChanged();
		}
	}

	private void clearTable() {
		int nb = tableModele.getRowCount();
		if (nb > -1) {
			for (int i = 0; i < nb; i++) {
				tableModele.removeRow(0);
			}
			discussionTable2.setModel(tableModele);
		}

	}

	private DefaultMutableTreeNode getArbreModelRecus() {
		// PARTIE TICKETS RECUS
		if (client != null) {
			Map<Groupe, List<Ticket>> ticketsRecu = client.getTicketsRecu();
			// Racine
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(
					new Ticket(-2, "Tickets reçus", null, null, null));
			DefaultMutableTreeNode groupeNode;
			// Création des noeuds des groupes
			Set<Groupe> groupes = ticketsRecu.keySet();
			if (groupes != null && !groupes.isEmpty()) {
				for (Groupe gr : groupes) {
					groupeNode = new DefaultMutableTreeNode(gr);
					List<Ticket> tickets = ticketsRecu.get(gr);
					// Création des noeuds des tickets pour chaque groupe
					if (tickets != null && !tickets.isEmpty()) {
						for (Ticket tk : tickets) {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(tk);
							groupeNode.add(node);
						}
						// On relie chaque noeud de groupe a la racine
						root.add(groupeNode);
					}
				}
			}
			return root;
		}
		// si le client n'est pas initialisé
		return null;
	}

	private DefaultMutableTreeNode getArbreModelCrees() {
		// PARTIE TICKETS CREES
		if (client != null) {
			Map<Groupe, List<Ticket>> ticketsCree = client.getTicketsCree();
			// Racine
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(
					new Ticket(-2, "Tickets crées", null, null, null));
			DefaultMutableTreeNode groupeNode;
			// Création des noeuds des groupes
			Set<Groupe> groupes = ticketsCree.keySet();
			if (groupes != null && !groupes.isEmpty()) {
				for (Groupe gr : groupes) {
					groupeNode = new DefaultMutableTreeNode(gr);
					List<Ticket> tickets = ticketsCree.get(gr);
					// Création des noeuds des tickets pour chaque groupe
					if (tickets != null && !tickets.isEmpty()) {
						for (Ticket tk : tickets) {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(tk);
							groupeNode.add(node);
						}
						// On relie chaque noeud de groupe a la racine
						root.add(groupeNode);
					}
				}
			}
			return root;
		}
		// si le client n'est pas initialisé
		return null;
	}

	private DefaultMutableTreeNode getArbreModelAll() {
		if (client != null) {
			Map<Groupe, List<Ticket>> ticketsTous = client.getTicketsTous();
			// Racine
			DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Ticket(-2, "Tickets", null, null, null));
			DefaultMutableTreeNode groupeNode;
			// Création des noeuds des groupes
			Set<Groupe> groupes = ticketsTous.keySet();
			// On parcourt d'abord les groupes de tickets recu
			if (groupes != null && !groupes.isEmpty()) {
				for (Groupe gr : groupes) {
					groupeNode = new DefaultMutableTreeNode(gr);
					List<Ticket> tickets = ticketsTous.get(gr);
					// Création des noeuds des tickets cree pour chaque groupe
					if (tickets != null && !tickets.isEmpty()) {
						for (Ticket tk : tickets) {
							DefaultMutableTreeNode node = new DefaultMutableTreeNode(tk);
							groupeNode.add(node);
						}
						// On relie chaque noeud de groupe a la racine
						root.add(groupeNode);
					}
				}
			}
			return root;
		}
		// si le client n'est pas initialisé
		return null;
	}

	private void setTableModel() {
		// Création de colonnes

		tableModele.addColumn("Nom utilisateur");
		tableModele.addColumn("Message");
		tableModele.addColumn("Date");
		tableModele.addColumn("Etat");
		tableModele.addColumn("MessageObject");

		discussionTable2.setModel(tableModele);
		discussionTable2.setDefaultRenderer(Object.class, new LineWrapCellRenderer());

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton creerTicketButton;
	private javax.swing.JButton decoButton;
	private javax.swing.JTextArea discussionArea;
	private javax.swing.JTable discussionTable2;
	private javax.swing.JButton envoyerButton;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JTabbedPane ongletsDiscu;
	private javax.swing.JTextArea saisieDiscuArea;
	private javax.swing.JTree ticketsAllTree;
	private javax.swing.JTree ticketsCreesTree;
	private javax.swing.JTree ticketsRecusTree;
	private javax.swing.JLabel titreDiscuLabel;
	private javax.swing.JLabel usernameLabel;
	// End of variables declaration//GEN-END:variables
}
