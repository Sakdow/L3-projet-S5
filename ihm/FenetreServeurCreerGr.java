/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import modele.Groupe;
import modele.Utilisateur;

/**
 *
 * @author Lucas
 */
public class FenetreServeurCreerGr extends javax.swing.JFrame {
    private DefaultListModel listeModele;
    private DefaultComboBoxModel comboUtilModele;
    private DefaultComboBoxModel comboGrModele;
    /**
     * Creates new form FenetreServeurCreerGr
     */
    public FenetreServeurCreerGr() {
        initComponents();
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

        nomGrLabel = new javax.swing.JLabel();
        nomGrField = new javax.swing.JTextField();
        ajouterUtilLabel = new javax.swing.JLabel();
        searchUtilField = new javax.swing.JTextField();
        ajouterGroupeLabel = new javax.swing.JLabel();
        searchAjoutGrField = new javax.swing.JTextField();
        paramGrLabel = new javax.swing.JLabel();
        listeUtilGrLabel = new javax.swing.JLabel();
        supprUtilLabel = new javax.swing.JLabel();
        searchSupprUtilField = new javax.swing.JTextField();
        supprimerUtilButton = new javax.swing.JButton();
        creerButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        ajoutUtilCombo = new javax.swing.JComboBox<>();
        searchSupprUtilLabel = new javax.swing.JLabel();
        ajouterUtilButton = new javax.swing.JButton();
        ajoutGrCombo = new javax.swing.JComboBox<>();
        ajouterGrButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeUtilList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Création de groupe");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        nomGrLabel.setText("Nom du groupe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(76, 40, 0, 0);
        getContentPane().add(nomGrLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(76, 12, 0, 0);
        getContentPane().add(nomGrField, gridBagConstraints);

        ajouterUtilLabel.setText("Ajouter un utilisateur :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 20, 0, 0);
        getContentPane().add(ajouterUtilLabel, gridBagConstraints);

        searchUtilField.setText("search");
        searchUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchUtilFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 12, 0, 0);
        getContentPane().add(searchUtilField, gridBagConstraints);

        ajouterGroupeLabel.setText("Ajouter un groupe : ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 20, 0, 0);
        getContentPane().add(ajouterGroupeLabel, gridBagConstraints);

        searchAjoutGrField.setText("search");
        searchAjoutGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAjoutGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 12, 0, 0);
        getContentPane().add(searchAjoutGrField, gridBagConstraints);

        paramGrLabel.setText("Paramètres du groupe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 100, 0, 0);
        getContentPane().add(paramGrLabel, gridBagConstraints);

        listeUtilGrLabel.setText("Liste des utilisateurs du groupe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 131, 0, 0);
        getContentPane().add(listeUtilGrLabel, gridBagConstraints);

        supprUtilLabel.setText("Supprimer un utilisateur :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 141, 0, 0);
        getContentPane().add(supprUtilLabel, gridBagConstraints);

        searchSupprUtilField.setText("search");
        searchSupprUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSupprUtilFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 121, 0, 0);
        getContentPane().add(searchSupprUtilField, gridBagConstraints);

        supprimerUtilButton.setText("Supprimer");
        supprimerUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerUtilButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 18);
        getContentPane().add(supprimerUtilButton, gridBagConstraints);

        creerButton.setText("Créer");
        creerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 121, 44, 0);
        getContentPane().add(creerButton, gridBagConstraints);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 41, 44, 0);
        getContentPane().add(annulerButton, gridBagConstraints);

        ajoutUtilCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboUtilModele = new DefaultComboBoxModel();
        setComboUtilModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 64;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        getContentPane().add(ajoutUtilCombo, gridBagConstraints);

        searchSupprUtilLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 121, 0, 0);
        getContentPane().add(searchSupprUtilLabel, gridBagConstraints);

        ajouterUtilButton.setText("Ajouter");
        ajouterUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterUtilButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(70, 10, 0, 0);
        getContentPane().add(ajouterUtilButton, gridBagConstraints);

        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboGrModele = new DefaultComboBoxModel();
        setComboGrModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.ipadx = 64;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        getContentPane().add(ajoutGrCombo, gridBagConstraints);

        ajouterGrButton.setText("Ajouter");
        ajouterGrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterGrButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 10, 0, 0);
        getContentPane().add(ajouterGrButton, gridBagConstraints);

        listeUtilList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listeModele = new DefaultListModel();
        jScrollPane1.setViewportView(listeUtilList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 137;
        gridBagConstraints.ipady = 157;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 141, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerButtonActionPerformed

    private void ajouterUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterUtilButtonActionPerformed
        
        if(!listeModele.contains(ajoutUtilCombo.getSelectedItem())){
            listeModele.addElement(ajoutUtilCombo.getSelectedItem());
            listeUtilList.setModel(listeModele);
        }
        
    }//GEN-LAST:event_ajouterUtilButtonActionPerformed

    private void searchUtilFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchUtilFieldKeyReleased
        String text = searchUtilField.getText();        
        searchCombo(text, ajoutUtilCombo);
    }//GEN-LAST:event_searchUtilFieldKeyReleased

    private void searchAjoutGrFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchAjoutGrFieldKeyReleased
        String text = searchAjoutGrField.getText();        
        searchCombo(text, ajoutGrCombo);
    }//GEN-LAST:event_searchAjoutGrFieldKeyReleased

    private void searchSupprUtilFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchSupprUtilFieldKeyReleased
        String text = searchSupprUtilField.getText();        
        searchJList(text, listeUtilList, searchSupprUtilLabel);
    }//GEN-LAST:event_searchSupprUtilFieldKeyReleased

    private void ajouterGrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterGrButtonActionPerformed
        //Faire une requete pour recuperer les utilisateurs du groupe
        String nomGr = (String) ajoutGrCombo.getSelectedItem();
        Groupe gr = serveur.getGroupeFromNomGroupe(nomGr);
        List<Utilisateur> utils = gr.getListeUtilisateur();
        //Ajout de chaque utilisateur à la JList
        for(Utilisateur ut : utils){
            //Verif de doublons
            if(!listeModele.contains(ut)){
                listeModele.addElement(ut);
            }
        }
        //On met à jour la liste avec son modele
        listeUtilList.setModel(listeModele);
    }//GEN-LAST:event_ajouterGrButtonActionPerformed

    private void supprimerUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerUtilButtonActionPerformed
        int index = listeUtilList.getSelectedIndex();
        listeModele.remove(index);
        listeUtilList.setModel(listeModele);
    }//GEN-LAST:event_supprimerUtilButtonActionPerformed

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        List<Utilisateur> listUtil = new ArrayList();
        for(int i = 0; i < listeModele.size(); i++){
            listUtil.add((Utilisateur) listeModele.get(i));
        }
    }//GEN-LAST:event_creerButtonActionPerformed
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
    
    private void setComboGrModel(){
        
        Set<String> groupes = serveur.getGroupes();
        for(String gr : groupes){
            comboGrModele.addElement(gr);
        }
        ajoutGrCombo.setModel(comboGrModele);        
    }
    
    private void setComboUtilModel(){
        
        Set<Utilisateur> utils = serveur.getUtilisateurs();
        for(Utilisateur ut : utils){
            comboUtilModele.addElement(ut);
        }
        ajoutUtilCombo.setModel(comboUtilModele);        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerGr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerGr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerGr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerGr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreServeurCreerGr().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ajoutGrCombo;
    private javax.swing.JComboBox<String> ajoutUtilCombo;
    private javax.swing.JButton ajouterGrButton;
    private javax.swing.JLabel ajouterGroupeLabel;
    private javax.swing.JButton ajouterUtilButton;
    private javax.swing.JLabel ajouterUtilLabel;
    private javax.swing.JButton annulerButton;
    private javax.swing.JButton creerButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listeUtilGrLabel;
    private javax.swing.JList<String> listeUtilList;
    private javax.swing.JTextField nomGrField;
    private javax.swing.JLabel nomGrLabel;
    private javax.swing.JLabel paramGrLabel;
    private javax.swing.JTextField searchAjoutGrField;
    private javax.swing.JTextField searchSupprUtilField;
    private javax.swing.JLabel searchSupprUtilLabel;
    private javax.swing.JTextField searchUtilField;
    private javax.swing.JLabel supprUtilLabel;
    private javax.swing.JButton supprimerUtilButton;
    // End of variables declaration//GEN-END:variables
}
