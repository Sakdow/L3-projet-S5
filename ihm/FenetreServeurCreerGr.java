/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;

/**
 *
 * @author Lucas
 */
public class FenetreServeurCreerGr extends javax.swing.JFrame {
    private DefaultListModel listeModele;
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomGrLabel.setText("Nom du groupe :");
        getContentPane().add(nomGrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        getContentPane().add(nomGrField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 120, -1));

        ajouterUtilLabel.setText("Ajouter un utilisateur :");
        getContentPane().add(ajouterUtilLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        searchUtilField.setText("search");
        searchUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchUtilFieldKeyReleased(evt);
            }
        });
        getContentPane().add(searchUtilField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 120, -1));

        ajouterGroupeLabel.setText("Ajouter un groupe : ");
        getContentPane().add(ajouterGroupeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        searchAjoutGrField.setText("search");
        searchAjoutGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAjoutGrFieldKeyReleased(evt);
            }
        });
        getContentPane().add(searchAjoutGrField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 120, -1));

        paramGrLabel.setText("Paramètres du groupe");
        getContentPane().add(paramGrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        listeUtilGrLabel.setText("Liste des utilisateurs du groupe");
        getContentPane().add(listeUtilGrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        supprUtilLabel.setText("Supprimer un utilisateur :");
        getContentPane().add(supprUtilLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

        searchSupprUtilField.setText("search");
        searchSupprUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSupprUtilFieldKeyReleased(evt);
            }
        });
        getContentPane().add(searchSupprUtilField, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 110, -1));

        supprimerUtilButton.setText("Supprimer");
        supprimerUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerUtilButtonActionPerformed(evt);
            }
        });
        getContentPane().add(supprimerUtilButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, -1, -1));

        creerButton.setText("Créer");
        getContentPane().add(creerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, -1));

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        getContentPane().add(annulerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, -1, -1));

        ajoutUtilCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ajoutUtilCombo.setModel(new javax.swing.DefaultComboBoxModel<>(getComboBoxModelUtil()));
        getContentPane().add(ajoutUtilCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 120, -1));

        searchSupprUtilLabel.setText("Recherche");
        getContentPane().add(searchSupprUtilLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, -1));

        ajouterUtilButton.setText("Ajouter");
        ajouterUtilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterUtilButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ajouterUtilButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(getComboBoxModelGr()));
        getContentPane().add(ajoutGrCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 120, -1));

        ajouterGrButton.setText("Ajouter");
        ajouterGrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterGrButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ajouterGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        listeUtilList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listeModele = new DefaultListModel();
        setListeModel();
        jScrollPane1.setViewportView(listeUtilList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 160, 180));

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
        //pour chaque util, faire
        //if(!listeModel.contains(util)){
        //listeModele.addElement(util);
        //}
        //listeUtilList.setModel(listeModele);
    }//GEN-LAST:event_ajouterGrButtonActionPerformed

    private void supprimerUtilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerUtilButtonActionPerformed
        int index = listeUtilList.getSelectedIndex();
        listeModele.remove(index);
        listeUtilList.setModel(listeModele);
    }//GEN-LAST:event_supprimerUtilButtonActionPerformed
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
    
    private String[] getComboBoxModelGr(){
        //Requete pour recuperer tous les groupes
        String req = "SELECT....";
        //ResultSet res = Serveur.requeteBDD(req);
        /*String groupes[];
        int i = 0;
        while(res.next()){
            groupes[i] = res.getString("nom");
            i ++;
        }
        return groupes;*/
        return new String[] { "Groupe 1", "Groupe 2" };
    }
    
    private String[] getComboBoxModelUtil(){
        //Requete pour recuperer tous les utilisateurs
        String req = "SELECT....";
        //ResultSet res = Serveur.requeteBDD(req);
        /*String util[];
        int i = 0;
        while(res.next()){
            util[i] = res.getString("prenom") + " " + res.getString("nom");
            i ++;
        }
        return util;*/
        return new String[] { "Util 1", "Util 2" };
    }
    
    private void setListeModel(){
        //Requete pour recuperer tous les utilisateurs du groupe
        String req = "SELECT....";
        //ResultSet res = Serveur.requeteBDD(req);
        /*        
        while(res.next()){
            modele.addElement(res.getString("prenom") + " " + res.getString("nom"));            
        }
        */
        //A REMPLACER PAR CI DESSUS
        listeModele.addElement("1");
        listeModele.addElement("2");
        //
        listeUtilList.setModel(listeModele);
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
