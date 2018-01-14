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
public class FenetreServeurCreerUtil extends javax.swing.JFrame {
    private DefaultListModel listeModele;
    private DefaultComboBoxModel comboModel;
    private List<String> listeGr;
    private Serveur serveur;
    /**
     * Creates new form FenetreServeurCreerUtil
     */
    public FenetreServeurCreerUtil(Serveur serveur) {
        this.serveur = serveur;
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

        jLabel1 = new javax.swing.JLabel();
        nomField = new javax.swing.JTextField();
        prenomField = new javax.swing.JTextField();
        idField = new javax.swing.JTextField();
        nomLabel = new javax.swing.JLabel();
        prenomLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        mdpLabel = new javax.swing.JLabel();
        confirmMdpLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchAjoutGrField = new javax.swing.JTextField();
        ajoutGrUtilButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeGroupesList = new javax.swing.JList<>();
        searchSupprGrField = new javax.swing.JTextField();
        supprGrButton = new javax.swing.JButton();
        creerButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        ajoutGrCombo = new javax.swing.JComboBox<>();
        searchSupprGrLabel = new javax.swing.JLabel();
        mdpField = new javax.swing.JPasswordField();
        mdpConfirmField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Création d'utilisateur");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Paramètres de l'utilisateur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 2, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 9, 0, 0);
        getContentPane().add(nomField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(prenomField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(idField, gridBagConstraints);

        nomLabel.setText("Nom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 10, 0, 0);
        getContentPane().add(nomLabel, gridBagConstraints);

        prenomLabel.setText("Prenom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 0);
        getContentPane().add(prenomLabel, gridBagConstraints);

        idLabel.setText("Identifiant :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 0);
        getContentPane().add(idLabel, gridBagConstraints);

        mdpLabel.setText("Mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 7, 0, 0);
        getContentPane().add(mdpLabel, gridBagConstraints);

        confirmMdpLabel.setText("Confirmation :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 10, 0, 0);
        getContentPane().add(confirmMdpLabel, gridBagConstraints);

        jLabel2.setText("Ajouter un groupe à l'utilisateur :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 4, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        searchAjoutGrField.setToolTipText("Recherche");
        searchAjoutGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAjoutGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 17, 0, 0);
        getContentPane().add(searchAjoutGrField, gridBagConstraints);

        ajoutGrUtilButton.setText("Ajouter");
        ajoutGrUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutGrUtilButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 34;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        getContentPane().add(ajoutGrUtilButton, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Liste des groupes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 47;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 250, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        listeModele = new DefaultListModel();
        listeGroupesList.setModel(listeModele);
        jScrollPane1.setViewportView(listeGroupesList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 47;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(35, 220, 0, 4);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        searchSupprGrField.setToolTipText("Recherche");
        searchSupprGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSupprGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 47;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 220, 0, 0);
        getContentPane().add(searchSupprGrField, gridBagConstraints);

        supprGrButton.setText("Supprimer");
        supprGrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprGrButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 4);
        getContentPane().add(supprGrButton, gridBagConstraints);

        creerButton.setText("Créer");
        creerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 48;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 13, 17, 0);
        getContentPane().add(creerButton, gridBagConstraints);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 8, 17, 0);
        getContentPane().add(annulerButton, gridBagConstraints);

        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboModel = new DefaultComboBoxModel();
        setComboModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 33;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 17, 0, 0);
        getContentPane().add(ajoutGrCombo, gridBagConstraints);

        searchSupprGrLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 47;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 220, 0, 0);
        getContentPane().add(searchSupprGrLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(mdpField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(mdpConfirmField, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String mdp = new String(mdpField.getPassword());
        String mdpC = new String(mdpConfirmField.getPassword());
        //Verifications
        if(!nom.isEmpty() && !prenom.isEmpty() && !mdp.isEmpty() && mdp.equals(mdpC)){              
            serveur.creerUtilisateur(idField.getText(), nomField.getText(), prenomField.getText(), mdp, listeGr);
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(null, "Erreur, il manque des champs et / ou les mots de passe ne correspondent pas.");
        }
    }//GEN-LAST:event_creerButtonActionPerformed

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

    private void searchSupprGrFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchSupprGrFieldKeyReleased
        String text = searchSupprGrField.getText();        
        searchJList(text, listeGroupesList, searchSupprGrLabel);
    }//GEN-LAST:event_searchSupprGrFieldKeyReleased

    private void searchAjoutGrFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchAjoutGrFieldKeyReleased
        String text = searchAjoutGrField.getText();        
        searchCombo(text, ajoutGrCombo);
    }//GEN-LAST:event_searchAjoutGrFieldKeyReleased

    private void supprGrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprGrButtonActionPerformed
        int index = listeGroupesList.getSelectedIndex();
        listeModele.remove(index);
        listeGroupesList.setModel(listeModele);
    }//GEN-LAST:event_supprGrButtonActionPerformed

    private void ajoutGrUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutGrUtilButtonActionPerformed
        if(!listeModele.contains(ajoutGrCombo.getSelectedItem())){
            listeModele.addElement(ajoutGrCombo.getSelectedItem());
            //Ajout du groupe a la liste
            listeGr.add((String) ajoutGrCombo.getSelectedItem());
            listeGroupesList.setModel(listeModele);
        }
    }//GEN-LAST:event_ajoutGrUtilButtonActionPerformed
    public void searchJList(String text, JList liste, JLabel label) {
        
        // Get number of items in the list
        int size = liste.getModel().getSize();
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
    
    private void setComboModel(){
        
        Set<String> groupes = serveur.getGroupes();
        for(String gr : groupes){
            comboModel.addElement(gr);
        }
        ajoutGrCombo.setModel(comboModel);
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ajoutGrCombo;
    private javax.swing.JButton ajoutGrUtilButton;
    private javax.swing.JButton annulerButton;
    private javax.swing.JLabel confirmMdpLabel;
    private javax.swing.JButton creerButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listeGroupesList;
    private javax.swing.JPasswordField mdpConfirmField;
    private javax.swing.JPasswordField mdpField;
    private javax.swing.JLabel mdpLabel;
    private javax.swing.JTextField nomField;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JTextField prenomField;
    private javax.swing.JLabel prenomLabel;
    private javax.swing.JTextField searchAjoutGrField;
    private javax.swing.JTextField searchSupprGrField;
    private javax.swing.JLabel searchSupprGrLabel;
    private javax.swing.JButton supprGrButton;
    // End of variables declaration//GEN-END:variables
}
