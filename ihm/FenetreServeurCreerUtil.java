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
import modele.Utilisateur;

/**
 *
 * @author Lucas
 */
public class FenetreServeurCreerUtil extends javax.swing.JFrame {
    private DefaultListModel listeModele;
    private DefaultComboBoxModel comboModel;
    /**
     * Creates new form FenetreServeurCreerUtil
     */
    public FenetreServeurCreerUtil() {
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

        jLabel1 = new javax.swing.JLabel();
        confirmMdpField = new javax.swing.JTextField();
        nomField = new javax.swing.JTextField();
        prenomField = new javax.swing.JTextField();
        idField = new javax.swing.JTextField();
        mdpField = new javax.swing.JTextField();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Création d'utilisateur");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Paramètres de l'utilisateur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 53, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(confirmMdpField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 9, 0, 0);
        getContentPane().add(nomField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(prenomField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(idField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 104;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(mdpField, gridBagConstraints);

        nomLabel.setText("Nom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 40, 0, 0);
        getContentPane().add(nomLabel, gridBagConstraints);

        prenomLabel.setText("Prenom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(prenomLabel, gridBagConstraints);

        idLabel.setText("Identifiant :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(idLabel, gridBagConstraints);

        mdpLabel.setText("Mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        getContentPane().add(mdpLabel, gridBagConstraints);

        confirmMdpLabel.setText("Confirmation :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        getContentPane().add(confirmMdpLabel, gridBagConstraints);

        jLabel2.setText("Ajouter un groupe à l'utilisateur :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 2, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        searchAjoutGrField.setText("search");
        searchAjoutGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAjoutGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        getContentPane().add(searchAjoutGrField, gridBagConstraints);

        ajoutGrUtilButton.setText("Ajouter");
        ajoutGrUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutGrUtilButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 28;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 3, 0, 0);
        getContentPane().add(ajoutGrUtilButton, gridBagConstraints);

        jLabel3.setText("Liste des groupes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 45;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 250, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        listeGroupesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listeModele = new DefaultListModel();
        jScrollPane1.setViewportView(listeGroupesList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 45;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 137;
        gridBagConstraints.ipady = 167;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(35, 220, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        searchSupprGrField.setText("search");
        searchSupprGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSupprGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 45;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 3;
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
        gridBagConstraints.gridx = 49;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 24);
        getContentPane().add(supprGrButton, gridBagConstraints);

        creerButton.setText("Créer");
        creerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 45;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 210, 7, 0);
        getContentPane().add(creerButton, gridBagConstraints);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 49;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 6, 7, 0);
        getContentPane().add(annulerButton, gridBagConstraints);

        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        setComboModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 45;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 164;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 30, 0, 0);
        getContentPane().add(ajoutGrCombo, gridBagConstraints);

        searchSupprGrLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 45;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 220, 0, 0);
        getContentPane().add(searchSupprGrLabel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        List<Utilisateur> listUtil = new ArrayList();
        for(int i = 0; i < listeModele.size(); i++){
            listUtil.add((Utilisateur) listeModele.get(i));
        }
    }//GEN-LAST:event_creerButtonActionPerformed

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.dispose();
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
            java.util.logging.Logger.getLogger(FenetreServeurCreerUtil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerUtil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerUtil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurCreerUtil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreServeurCreerUtil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ajoutGrCombo;
    private javax.swing.JButton ajoutGrUtilButton;
    private javax.swing.JButton annulerButton;
    private javax.swing.JTextField confirmMdpField;
    private javax.swing.JLabel confirmMdpLabel;
    private javax.swing.JButton creerButton;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listeGroupesList;
    private javax.swing.JTextField mdpField;
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
