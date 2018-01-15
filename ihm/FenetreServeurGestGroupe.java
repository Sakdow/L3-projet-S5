/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import modele.Groupe;
import modele.Utilisateur;
import serveur.Serveur;

/**
 *
 * @author Lucas
 */
public class FenetreServeurGestGroupe extends javax.swing.JFrame {
	private DefaultListModel listeModeleUtil;
	private DefaultListModel listeModeleGr;
	private DefaultComboBoxModel comboGrModele;
	private DefaultComboBoxModel comboUtilModele;
	private List<Utilisateur> listeUtil;
	private String groupeSelect;
	private int indexGrSelect;
	private boolean grModifie;
	private Serveur serveur;

	/**
	 * Creates new form FenetreServeurGestGroupe
	 */
	public FenetreServeurGestGroupe(Serveur serveur) {
		this.serveur = serveur;
		ImageIcon img = new ImageIcon("icon.jpg");
		this.setIconImage(img.getImage());
		listeUtil = new ArrayList();
		groupeSelect = null;
		grModifie = false;
		initComponents();
		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment annuler (ne pas sauver) ?",
						"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		};
		this.addWindowListener(exitListener);
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

		paramGrLabel = new javax.swing.JLabel();
		listeGrList = new javax.swing.JScrollPane();
		listeGroupeList = new javax.swing.JList<>();
		jScrollPane1 = new javax.swing.JScrollPane();
		listeUtilGrList = new javax.swing.JList<>();
		searchGrField = new javax.swing.JTextField();
		supprGrButton = new javax.swing.JButton();
		modifNomLabel = new javax.swing.JLabel();
		modifNomField = new javax.swing.JTextField();
		accepterButton = new javax.swing.JButton();
		annulerButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		ajoutUtilGrLabel = new javax.swing.JLabel();
		ajoutGrUtilLabel = new javax.swing.JLabel();
		supprDuGroupeLabel = new javax.swing.JLabel();
		searchRetirerDuGrField = new javax.swing.JTextField();
		searchAjoutUtilGrField = new javax.swing.JTextField();
		searchAjoutGrUtilField = new javax.swing.JTextField();
		retirerDuGrButton = new javax.swing.JButton();
		ajoutUtilGrButton = new javax.swing.JButton();
		ajoutGrUtilButton = new javax.swing.JButton();
		searchGrLabel = new javax.swing.JLabel();
		searchUtilLabel = new javax.swing.JLabel();
		ajoutUtilCombo = new javax.swing.JComboBox<>();
		ajoutGrCombo = new javax.swing.JComboBox<>();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Gestion de groupes");
		setMinimumSize(new java.awt.Dimension(800, 600));
		setPreferredSize(new java.awt.Dimension(800, 600));
		getContentPane().setLayout(new java.awt.GridBagLayout());

		paramGrLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		paramGrLabel.setText("Liste des groupes");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 68, 0, 0);
		getContentPane().add(paramGrLabel, gridBagConstraints);

		listeModeleGr = new DefaultListModel();
		setListeModelGr();
		listeGroupeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				listeGroupeListValueChanged(evt);
			}
		});
		listeGrList.setViewportView(listeGroupeList);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 7;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 225;
		gridBagConstraints.ipady = 168;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(16, 38, 0, 0);
		getContentPane().add(listeGrList, gridBagConstraints);

		listeModeleUtil = new DefaultListModel();
		setListeModelUtil();
		jScrollPane1.setViewportView(listeUtilGrList);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 21;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 204;
		gridBagConstraints.ipady = 142;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(16, 197, 0, 0);
		getContentPane().add(jScrollPane1, gridBagConstraints);

		searchGrField.setToolTipText("Recherche");
		searchGrField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				searchGrFieldKeyReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 134;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(5, 48, 0, 0);
		getContentPane().add(searchGrField, gridBagConstraints);

		supprGrButton.setText("Supprimer");
		supprGrButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				supprGrButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridwidth = 8;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.ipadx = 9;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(7, 20, 0, 0);
		getContentPane().add(supprGrButton, gridBagConstraints);

		modifNomLabel.setText("Modifier le nom :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 12;
		gridBagConstraints.gridheight = 8;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(15, 48, 0, 0);
		getContentPane().add(modifNomLabel, gridBagConstraints);

		modifNomField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				modifNomFieldKeyTyped(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 27;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.gridheight = 10;
		gridBagConstraints.ipadx = 134;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(16, 48, 0, 0);
		getContentPane().add(modifNomField, gridBagConstraints);

		accepterButton.setText("Accepter");
		this.getRootPane().setDefaultButton(accepterButton);
		accepterButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				accepterButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 50;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(20, 232, 13, 0);
		getContentPane().add(accepterButton, gridBagConstraints);

		annulerButton.setText("Annuler");
		annulerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				annulerButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 50;
		gridBagConstraints.gridwidth = 16;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(20, 15, 13, 0);
		getContentPane().add(annulerButton, gridBagConstraints);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel1.setText("Liste des utilisateurs du groupe");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 20;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 222, 0, 0);
		getContentPane().add(jLabel1, gridBagConstraints);

		ajoutUtilGrLabel.setText("Ajouter un utilisateur :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(7, 197, 0, 0);
		getContentPane().add(ajoutUtilGrLabel, gridBagConstraints);

		ajoutGrUtilLabel.setText("Ajouter les utilisateurs d'un groupe :");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 36;
		gridBagConstraints.gridwidth = 9;
		gridBagConstraints.gridheight = 11;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 197, 0, 0);
		getContentPane().add(ajoutGrUtilLabel, gridBagConstraints);

		supprDuGroupeLabel.setText("Supprimer du groupe");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(12, 257, 0, 0);
		getContentPane().add(supprDuGroupeLabel, gridBagConstraints);

		searchRetirerDuGrField.setToolTipText("Recherche");
		searchRetirerDuGrField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				searchRetirerDuGrFieldKeyReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 94;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 197, 0, 0);
		getContentPane().add(searchRetirerDuGrField, gridBagConstraints);

		searchAjoutUtilGrField.setToolTipText("Recherche");
		searchAjoutUtilGrField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				searchAjoutUtilGrFieldKeyReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridheight = 4;
		gridBagConstraints.ipadx = 94;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 197, 0, 0);
		getContentPane().add(searchAjoutUtilGrField, gridBagConstraints);

		searchAjoutGrUtilField.setToolTipText("Recherche");
		searchAjoutGrUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				searchAjoutGrUtilFieldKeyReleased(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 47;
		gridBagConstraints.ipadx = 94;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(6, 197, 0, 0);
		getContentPane().add(searchAjoutGrUtilField, gridBagConstraints);

		retirerDuGrButton.setText("Supprimer");
		retirerDuGrButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				retirerDuGrButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 15;
		gridBagConstraints.gridheight = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
		getContentPane().add(retirerDuGrButton, gridBagConstraints);

		ajoutUtilGrButton.setText("Ajouter");
		ajoutUtilGrButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ajoutUtilGrButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridwidth = 10;
		gridBagConstraints.gridheight = 7;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
		getContentPane().add(ajoutUtilGrButton, gridBagConstraints);

		ajoutGrUtilButton.setText("Ajouter");
		ajoutGrUtilButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ajoutGrUtilButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 14;
		gridBagConstraints.gridy = 47;
		gridBagConstraints.gridwidth = 10;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
		getContentPane().add(ajoutGrUtilButton, gridBagConstraints);

		searchGrLabel.setText("Recherche");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.ipadx = 119;
		gridBagConstraints.ipady = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(8, 48, 0, 0);
		getContentPane().add(searchGrLabel, gridBagConstraints);

		searchUtilLabel.setText("Recherche");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.ipadx = 99;
		gridBagConstraints.ipady = 6;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(6, 197, 0, 0);
		getContentPane().add(searchUtilLabel, gridBagConstraints);

		ajoutUtilCombo.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		comboUtilModele = new DefaultComboBoxModel();
		setComboUtilModel();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 19;
		gridBagConstraints.gridwidth = 14;
		gridBagConstraints.gridheight = 9;
		gridBagConstraints.ipadx = 134;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(7, 197, 0, 0);
		getContentPane().add(ajoutUtilCombo, gridBagConstraints);

		ajoutGrCombo.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		comboGrModele = new DefaultComboBoxModel();
		setComboGrModel();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 11;
		gridBagConstraints.gridy = 49;
		gridBagConstraints.gridwidth = 14;
		gridBagConstraints.ipadx = 134;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(7, 197, 0, 0);
		getContentPane().add(ajoutGrCombo, gridBagConstraints);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_annulerButtonActionPerformed
		int confirm = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment annuler (ne pas sauver) ?",
				"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			this.dispose();
		} else {
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}// GEN-LAST:event_annulerButtonActionPerformed

	private void searchGrFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_searchGrFieldKeyReleased
		String text = searchGrField.getText();
		searchJList(text, listeGroupeList, searchGrLabel);
	}// GEN-LAST:event_searchGrFieldKeyReleased

	private void searchRetirerDuGrFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_searchRetirerDuGrFieldKeyReleased
		String text = searchRetirerDuGrField.getText();
		searchJList(text, listeUtilGrList, searchUtilLabel);
	}// GEN-LAST:event_searchRetirerDuGrFieldKeyReleased

	private void searchAjoutUtilGrFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_searchAjoutUtilGrFieldKeyReleased
		String text = searchAjoutUtilGrField.getText();
		searchCombo(text, ajoutUtilCombo);

	}// GEN-LAST:event_searchAjoutUtilGrFieldKeyReleased

	private void searchAjoutGrUtilFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_searchAjoutGrUtilFieldKeyReleased
		String text = searchAjoutGrUtilField.getText();
		searchCombo(text, ajoutGrCombo);
	}// GEN-LAST:event_searchAjoutGrUtilFieldKeyReleased

	private void supprGrButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_supprGrButtonActionPerformed

		int index = listeGroupeList.getSelectedIndex();
		if (index != -1) {
			if (groupeSelect.equals("Administrateur")) {
				JOptionPane.showMessageDialog(null, "Impossible de supprimer ce groupe");
			} else {
				int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {
					try {
						// On supprime le groupe
						serveur.supprimerGroupe(groupeSelect);
						// Rafraichissement
						listeModeleGr.removeAllElements();
						setListeModelGr();

					} catch (SQLException ex) {
						Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}

	}// GEN-LAST:event_supprGrButtonActionPerformed

	private void retirerDuGrButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_retirerDuGrButtonActionPerformed
		if (listeModeleUtil.getSize() == 1 && groupeSelect.equals("Administrateur")) {
			JOptionPane.showMessageDialog(null, "Il doit rester au moins un utilisateur dans ce groupe.");
		} else {
			int index = listeUtilGrList.getSelectedIndex();
			listeModeleUtil.remove(index);
			listeUtilGrList.setModel(listeModeleUtil);
			// On supprime l'utilisateur à l'index index car l'ordre est le
			// même
			listeUtil.remove(index);
			grModifie = true;
		}

	}// GEN-LAST:event_retirerDuGrButtonActionPerformed

	private void listeGroupeListValueChanged(javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_listeGroupeListValueChanged
		// Si un autre groupe était deja selectionné (liste util non vide) et
		// modifications ont eu lieu
		if ((groupeSelect != null) && grModifie) {
			int response = JOptionPane.showConfirmDialog(null, "Voulez-vous sauvegarder ?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				// verif de doublons et modification
				lancerModification();
				// Mise à jour du nouveau nom eventuel dans la liste
				listeModeleGr.set(indexGrSelect, modifNomField.getText());
			}
			// sinon on n'envoie rien au serveur
		}
		clearList();
		grModifie = false;
		indexGrSelect = listeGroupeList.getSelectedIndex();
		if (indexGrSelect != -1) {
			listeUtil.removeAll(listeUtil);
			groupeSelect = listeGroupeList.getSelectedValue();
			// On affiche la liste du nouveau groupe selectionné
			setListeModelUtil();
			modifNomField.setText(groupeSelect);
			if (groupeSelect.equals("Administrateur")) {
				modifNomField.setEditable(false);
			} else {
				modifNomField.setEditable(true);
			}
		}

	}// GEN-LAST:event_listeGroupeListValueChanged

	private void lancerModification() {
		String nouvNom = modifNomField.getText();
		if (!nouvNom.equals("")) {
			serveur.modificationGroupe(groupeSelect, nouvNom, listeUtil);
		}
	}

	private void ajoutUtilGrButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ajoutUtilGrButtonActionPerformed
		if (!listeModeleUtil.contains(ajoutUtilCombo.getSelectedItem())) {
			listeModeleUtil.addElement(ajoutUtilCombo.getSelectedItem());
			listeUtilGrList.setModel(listeModeleUtil);
			listeUtil.add((Utilisateur) ajoutUtilCombo.getSelectedItem());
			grModifie = true;
		}
	}// GEN-LAST:event_ajoutUtilGrButtonActionPerformed

	private void ajoutGrUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ajoutGrUtilButtonActionPerformed
		String nomGr = (String) ajoutGrCombo.getSelectedItem();

		Groupe gr = null;
		try {
			gr = serveur.getGroupeFromNomGroupe(nomGr);
		} catch (SQLException ex) {
			Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(Level.SEVERE, null, ex);
		}
		// On recupère les utilisateurs du groupe qu'on veut fusionner
		List<Utilisateur> utils = gr.getListeUtilisateur();
		// On get la liste correspondant au groupe dans lequel on est
                if(utils != null){
                    for (Utilisateur ut : utils) {
			// Vérification doublons
			if (!listeModeleUtil.contains(ut)) {
				listeModeleUtil.addElement(ut);
				// On ajoute l'utilisateur à la liste
				listeUtil.add((Utilisateur) ut);
				grModifie = true;
			}
                    }
                    listeUtilGrList.setModel(listeModeleUtil);
                }
		
	}// GEN-LAST:event_ajoutGrUtilButtonActionPerformed

	private void accepterButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accepterButtonActionPerformed
		int response = JOptionPane.showConfirmDialog(null, "Voulez-vous sauvegarder ?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			// verif de doublons et modification
			lancerModification();
			this.dispose();
		}
		// sinon on n'envoie rien au serveur
	}// GEN-LAST:event_accepterButtonActionPerformed

	private void modifNomFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_modifNomFieldKeyTyped
		if (!modifNomField.getText().equals(groupeSelect)) {
			grModifie = true;
		}
	}// GEN-LAST:event_modifNomFieldKeyTyped

	public void searchJList(String text, JList liste, JLabel label) {

		// Get number of items in the list
		int size = liste.getModel().getSize();
		text = text.toUpperCase();
		// Get all item objects
		for (int i = 0; i < size; i++) {
			String item = liste.getModel().getElementAt(i).toString();
			if (item.contains(text)) {
				int index = i;
				liste.setSelectedIndex(index);
				label.setText(text + " trouvé à l'index " + index);
				// Une fois trouvé, la boucle s'arrete
				i = size + 1;
			} else {
				liste.clearSelection();
				label.setText(text + " non trouvé");
			}
		}
	}

	public void searchCombo(String text, JComboBox box) {

		// Get number of items in the list
		int size = box.getModel().getSize();
		text = text.toUpperCase();
		// Get all item objects
		for (int i = 0; i < size; i++) {
			String item = box.getModel().getElementAt(i).toString();
			if (item.contains(text)) {
				int index = i;
				box.setSelectedIndex(index);
				// Une fois trouvé, on arrête la boucle
				i = size + 1;
			}
		}
	}

	private void setListeModelGr() {

		Set<String> groupes = serveur.getGroupes();
		// Ajout de chaque groupe dans la JList
		for (String gr : groupes) {
			listeModeleGr.addElement(gr);
		}
		listeGroupeList.setModel(listeModeleGr);
	}

	private void setListeModelUtil() {
		String nomGr = listeGroupeList.getSelectedValue();
		if (nomGr != null) {
			Groupe gr = null;
			try {
				gr = serveur.getGroupeFromNomGroupe(nomGr);
				listeUtil = gr.getListeUtilisateur();
				// Ajout de chaque utilisateur dans la JList
				if (listeUtil != null && !listeUtil.isEmpty()) {
					for (Utilisateur ut : listeUtil) {
						listeModeleUtil.addElement(ut);
					}
				}

			} catch (SQLException ex) {
				Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(Level.SEVERE, null, ex);
			}

			listeUtilGrList.setModel(listeModeleUtil);
		}

	}

	private void setComboGrModel() {

		Set<String> groupes = serveur.getGroupes();
		for (String gr : groupes) {
			comboGrModele.addElement(gr);
		}
		ajoutGrCombo.setModel(comboGrModele);
	}

	private void setComboUtilModel() {

		Set<Utilisateur> utils = serveur.getUtilisateurs();
		for (Utilisateur ut : utils) {
			comboUtilModele.addElement(ut);
		}
		ajoutUtilCombo.setModel(comboUtilModele);
	}

	private void clearList() {
		listeModeleUtil.removeAllElements();
		listeUtilGrList.setModel(listeModeleUtil);
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton accepterButton;
	private javax.swing.JComboBox<String> ajoutGrCombo;
	private javax.swing.JButton ajoutGrUtilButton;
	private javax.swing.JLabel ajoutGrUtilLabel;
	private javax.swing.JComboBox<String> ajoutUtilCombo;
	private javax.swing.JButton ajoutUtilGrButton;
	private javax.swing.JLabel ajoutUtilGrLabel;
	private javax.swing.JButton annulerButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane listeGrList;
	private javax.swing.JList<String> listeGroupeList;
	private javax.swing.JList<String> listeUtilGrList;
	private javax.swing.JTextField modifNomField;
	private javax.swing.JLabel modifNomLabel;
	private javax.swing.JLabel paramGrLabel;
	private javax.swing.JButton retirerDuGrButton;
	private javax.swing.JTextField searchAjoutGrUtilField;
	private javax.swing.JTextField searchAjoutUtilGrField;
	private javax.swing.JTextField searchGrField;
	private javax.swing.JLabel searchGrLabel;
	private javax.swing.JTextField searchRetirerDuGrField;
	private javax.swing.JLabel searchUtilLabel;
	private javax.swing.JLabel supprDuGroupeLabel;
	private javax.swing.JButton supprGrButton;
	// End of variables declaration//GEN-END:variables
}
