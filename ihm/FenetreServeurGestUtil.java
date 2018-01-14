/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
public class FenetreServeurGestUtil extends javax.swing.JFrame {

    private DefaultListModel listeModeleUtil;
    private DefaultListModel listeModeleGr;
    private DefaultComboBoxModel comboGrModele;
    private Utilisateur utilSelect;
    private boolean utilModifie;
    private List<String> listeGr;
    private Serveur serveur;
    /**
     * Creates new form FenetreServeurGestGroupe
     */
    public FenetreServeurGestUtil(Serveur serveur) {
        this.serveur = serveur;
        utilSelect = null;
        listeGr = new ArrayList<>();
        initComponents();
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {               
                    int confirm = JOptionPane.showOptionDialog(
                     null, "Voulez-vous vraiment annuler (ne pas sauver) ?", 
                     "Confirmation", JOptionPane.YES_NO_OPTION, 
                     JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
                else {
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        paramGrLabel = new javax.swing.JLabel();
        listeGrList = new javax.swing.JScrollPane();
        listeUtilList = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeGrUtilList = new javax.swing.JList<>();
        searchUtilField = new javax.swing.JTextField();
        supprUtilButton = new javax.swing.JButton();
        modifNomLabel = new javax.swing.JLabel();
        modifNomField = new javax.swing.JTextField();
        accepterButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ajoutGrUtilLabel = new javax.swing.JLabel();
        supprDuGroupeLabel = new javax.swing.JLabel();
        searchSupprGrField = new javax.swing.JTextField();
        searchAjoutGrField = new javax.swing.JTextField();
        retirerGrButton = new javax.swing.JButton();
        ajoutGrUtilButton = new javax.swing.JButton();
        modifPrenomLabel = new javax.swing.JLabel();
        modifPrenomField = new javax.swing.JTextField();
        modifMdpLabel = new javax.swing.JLabel();
        confirmMdpLabel = new javax.swing.JLabel();
        searchUtilLabel = new javax.swing.JLabel();
        searchSupprGrLabel = new javax.swing.JLabel();
        ajoutGrCombo = new javax.swing.JComboBox<>();
        modifMdpField = new javax.swing.JPasswordField();
        modifMdpConfirmField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion d'utilisateurs");
        setPreferredSize(new java.awt.Dimension(800, 528));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        paramGrLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        paramGrLabel.setText("Liste des utilisateurs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 2, 0, 0);
        getContentPane().add(paramGrLabel, gridBagConstraints);

        listeModeleUtil = new DefaultListModel();
        setListeModelUtil();
        listeUtilList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listeUtilListValueChanged(evt);
            }
        });
        listeGrList.setViewportView(listeUtilList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 137;
        gridBagConstraints.ipady = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 30, 0, 0);
        getContentPane().add(listeGrList, gridBagConstraints);

        listeModeleGr = new DefaultListModel();
        jScrollPane1.setViewportView(listeGrUtilList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 167;
        gridBagConstraints.ipady = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(13, 249, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        searchUtilField.setToolTipText("Recherche");
        searchUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchUtilFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 134;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 30, 0, 0);
        getContentPane().add(searchUtilField, gridBagConstraints);

        supprUtilButton.setText("Supprimer");
        supprUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprUtilButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 23;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        getContentPane().add(supprUtilButton, gridBagConstraints);

        modifNomLabel.setText("Nom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 0);
        getContentPane().add(modifNomLabel, gridBagConstraints);

        modifNomField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modifNomFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 9, 0, 0);
        getContentPane().add(modifNomField, gridBagConstraints);

        accepterButton.setText("Accepter");
        this.getRootPane().setDefaultButton(accepterButton);
        accepterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accepterButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 269, 18, 0);
        getContentPane().add(accepterButton, gridBagConstraints);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 38;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 18, 0);
        getContentPane().add(annulerButton, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Liste des groupes de l'utilisateur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 249, 0, 39);
        getContentPane().add(jLabel1, gridBagConstraints);

        ajoutGrUtilLabel.setText("Ajouter un groupe pour l'utilisateur :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 249, 0, 0);
        getContentPane().add(ajoutGrUtilLabel, gridBagConstraints);

        supprDuGroupeLabel.setText("Supprimer du groupe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 289, 0, 0);
        getContentPane().add(supprDuGroupeLabel, gridBagConstraints);

        searchSupprGrField.setToolTipText("");
        searchSupprGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSupprGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 249, 0, 0);
        getContentPane().add(searchSupprGrField, gridBagConstraints);

        searchAjoutGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAjoutGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 249, 0, 0);
        getContentPane().add(searchAjoutGrField, gridBagConstraints);

        retirerGrButton.setText("Supprimer");
        retirerGrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retirerGrButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 38;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 39);
        getContentPane().add(retirerGrButton, gridBagConstraints);

        ajoutGrUtilButton.setText("Ajouter");
        ajoutGrUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutGrUtilButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 38;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(ajoutGrUtilButton, gridBagConstraints);

        modifPrenomLabel.setText("Prénom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(modifPrenomLabel, gridBagConstraints);

        modifPrenomField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modifPrenomFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(modifPrenomField, gridBagConstraints);

        modifMdpLabel.setText("Mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(modifMdpLabel, gridBagConstraints);

        confirmMdpLabel.setText("Confirmer mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        getContentPane().add(confirmMdpLabel, gridBagConstraints);

        searchUtilLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 89;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(searchUtilLabel, gridBagConstraints);

        searchSupprGrLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 109;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 249, 0, 0);
        getContentPane().add(searchSupprGrLabel, gridBagConstraints);

        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboGrModele = new DefaultComboBoxModel();
        setComboGrModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 36;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 249, 0, 0);
        getContentPane().add(ajoutGrCombo, gridBagConstraints);

        modifMdpField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modifMdpFieldKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(modifMdpField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 22;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 9, 0, 0);
        getContentPane().add(modifMdpConfirmField, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        int confirm = JOptionPane.showOptionDialog(
        null, "Voulez-vous vraiment annuler (ne pas sauver) ?", 
        "Confirmation", JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            this.dispose();
        }
        else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_annulerButtonActionPerformed

    private void searchUtilFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchUtilFieldKeyReleased
        String text = searchUtilField.getText();        
        searchJList(text, listeUtilList, searchUtilLabel);
    }//GEN-LAST:event_searchUtilFieldKeyReleased

    private void searchSupprGrFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchSupprGrFieldKeyReleased
        String text = searchSupprGrField.getText();
        searchJList(text, listeGrUtilList, searchSupprGrLabel);
    }//GEN-LAST:event_searchSupprGrFieldKeyReleased

    private void searchAjoutGrFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchAjoutGrFieldKeyReleased
        String text = searchAjoutGrField.getText();
        searchCombo(text, ajoutGrCombo); 
    }//GEN-LAST:event_searchAjoutGrFieldKeyReleased

    private void supprUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprUtilButtonActionPerformed
        //Le serveur supprime
        if(utilSelect != null){
            int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                serveur.supprimerUtilisateur(utilSelect.getIdUtilisateur());        
                //listeModeleUtil.removeElement(utilSelect);
                //listeUtilList.setModel(listeModeleUtil);
                listeModeleUtil.removeAllElements();
                setListeModelUtil();
            }
            
        }
        
    }//GEN-LAST:event_supprUtilButtonActionPerformed

    private void listeUtilListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listeUtilListValueChanged
        //Si une modification est en cours
        if((utilSelect != null) && utilModifie){
            int response = JOptionPane.showConfirmDialog(null, "Voulez-vous sauvegarder les modifications ?", "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                //On dit au serveur de modifier
                String mdp = new String(modifMdpField.getPassword());
                serveur.modificationUtilisateur(utilSelect.getIdUtilisateur(), modifNomField.getText(), modifPrenomField.getText(), mdp , listeGr);
            }            
        }
        //On affiche les groupes du nouvel user selectionne
        clearList();
        utilModifie = false;
        listeGr.removeAll(listeGr);
        int indSelect = listeUtilList.getSelectedIndex();
        if(indSelect != -1){
            utilSelect = (Utilisateur) listeModeleUtil.get(indSelect);
            //On affiche la liste du nouveau groupe selectionné
            setListeModelGr();
            //On remplit les champs 
            modifNomField.setText(utilSelect.getNom());
            modifPrenomField.setText(utilSelect.getPrenom());
        }
        
    }//GEN-LAST:event_listeUtilListValueChanged

    private void retirerGrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retirerGrButtonActionPerformed
        //groupe selectionné
        utilModifie = true;
        int index = listeGrUtilList.getSelectedIndex();
        //util selectionné
        int indUtil = listeUtilList.getSelectedIndex();
        Utilisateur utilSelect = (Utilisateur) listeModeleUtil.get(indUtil);
        listeModeleGr.remove(index);
        listeGrUtilList.setModel(listeModeleGr);        
        //On supprime le groupe de la liste
        listeGr.remove(index);        
        
    }//GEN-LAST:event_retirerGrButtonActionPerformed

    private void accepterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accepterButtonActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "Voulez-vous sauvegarder les modifications ?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            String nom = modifNomField.getText();
            String prenom = modifPrenomField.getText();
            String mdp = new String(modifMdpField.getPassword());
            String mdpC = new String(modifMdpConfirmField.getPassword());
            //Verifications
            if(!nom.isEmpty() && !prenom.isEmpty() && !mdp.isEmpty() && mdp.equals(mdpC)){
               //On dit au serveur de modifier
               serveur.modificationUtilisateur(utilSelect.getIdUtilisateur(), nom, prenom, mdp , listeGr);
               this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Erreurs dans les champs");
            }
            
            
        }
    }//GEN-LAST:event_accepterButtonActionPerformed

    private void ajoutGrUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutGrUtilButtonActionPerformed
        if(!listeModeleGr.contains(ajoutGrCombo.getSelectedItem())){
            listeModeleGr.addElement(ajoutGrCombo.getSelectedItem());
            listeGrUtilList.setModel(listeModeleGr);
            listeGr.add((String) ajoutGrCombo.getSelectedItem());
            utilModifie = true;
        }
    }//GEN-LAST:event_ajoutGrUtilButtonActionPerformed

    private void modifNomFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modifNomFieldKeyTyped
        if(!modifNomField.getText().equals(utilSelect)){
            utilModifie = true;
        }
    }//GEN-LAST:event_modifNomFieldKeyTyped

    private void modifPrenomFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modifPrenomFieldKeyTyped
        if(!modifPrenomField.getText().equals(utilSelect)){
            utilModifie = true;
        }
    }//GEN-LAST:event_modifPrenomFieldKeyTyped

    private void modifMdpFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modifMdpFieldKeyTyped
        String mdp = new String(modifMdpField.getPassword());
        if(mdp.equals(utilSelect)){
            utilModifie = true;
        }
    }//GEN-LAST:event_modifMdpFieldKeyTyped
    public void searchJList(String text, JList liste, JLabel label) {
        
        // Get number of items in the list
        int size = liste.getModel().getSize();
        text = text.toUpperCase();
        // Get all item objects
        for (int i = 0; i < size; i++) {
            String item = (String) liste.getModel().getElementAt(i);
            if(item.contains(text)) {
            int index = i;
            liste.setSelectedIndex(index);
            label.setText(text + " trouvé à l'index " + index);
            //Une fois trouvé, on arrête la boucle
            i = size +1;
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
            String item = (String) box.getModel().getElementAt(i);
            if(item.contains(text)) {
            int index = i;
            box.setSelectedIndex(index);
            //label.setText(text + " trouvé à l'index " + index);
            //Une fois trouvé, on arrête la boucle
            i = size +1;
            } else {
                //label.setText(text + " non trouvé");
            }
        }        
    }
    private void setListeModelGr(){
        int indUtil = listeUtilList.getSelectedIndex();
        if(indUtil != -1){
            utilSelect = (Utilisateur) listeModeleUtil.get(indUtil);
            List<Groupe> groupes = serveur.groupesUtilisateur(utilSelect.getIdUtilisateur());
            //Ajout de chaque groupe dans la JList
            for(Groupe gr : groupes){
                listeModeleGr.addElement(gr);
                listeGr.add(gr.getIdGroupe());
            }
            listeGrUtilList.setModel(listeModeleGr);
        }
                
    }
    
    private void setListeModelUtil(){
        
        Set<Utilisateur> utils = serveur.getUtilisateurs();
        //Ajout de chaque utilisateur dans la JList
        for(Utilisateur ut : utils){
            listeModeleUtil.addElement(ut);                       
        }
        listeUtilList.setModel(listeModeleUtil);
    }
    
    private void setComboGrModel(){
        
        Set<String> groupes = serveur.getGroupes();
        comboGrModele = new DefaultComboBoxModel();
        for(String gr : groupes){
            comboGrModele.addElement(gr);
        }
        ajoutGrCombo.setModel(comboGrModele);        
    }
    private void clearList(){
        listeModeleGr.removeAllElements();
        listeGrUtilList.setModel(listeModeleGr);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accepterButton;
    private javax.swing.JComboBox<String> ajoutGrCombo;
    private javax.swing.JButton ajoutGrUtilButton;
    private javax.swing.JLabel ajoutGrUtilLabel;
    private javax.swing.JButton annulerButton;
    private javax.swing.JLabel confirmMdpLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane listeGrList;
    private javax.swing.JList<String> listeGrUtilList;
    private javax.swing.JList<String> listeUtilList;
    private javax.swing.JPasswordField modifMdpConfirmField;
    private javax.swing.JPasswordField modifMdpField;
    private javax.swing.JLabel modifMdpLabel;
    private javax.swing.JTextField modifNomField;
    private javax.swing.JLabel modifNomLabel;
    private javax.swing.JTextField modifPrenomField;
    private javax.swing.JLabel modifPrenomLabel;
    private javax.swing.JLabel paramGrLabel;
    private javax.swing.JButton retirerGrButton;
    private javax.swing.JTextField searchAjoutGrField;
    private javax.swing.JTextField searchSupprGrField;
    private javax.swing.JLabel searchSupprGrLabel;
    private javax.swing.JTextField searchUtilField;
    private javax.swing.JLabel searchUtilLabel;
    private javax.swing.JLabel supprDuGroupeLabel;
    private javax.swing.JButton supprUtilButton;
    // End of variables declaration//GEN-END:variables
}
