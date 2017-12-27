/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projets5;

/**
 *
 * @author Lucas
 */
public class FenetreServeurGestGroupe extends javax.swing.JFrame {

    /**
     * Creates new form FenetreServeurGestGroupe
     */
    public FenetreServeurGestGroupe() {
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
        ajoutUtilGrField = new javax.swing.JTextField();
        ajoutGrUtilField = new javax.swing.JTextField();
        retirerDuGrButton = new javax.swing.JButton();
        ajoutUtilGrButton = new javax.swing.JButton();
        ajoutGrUtilButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de groupes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paramGrLabel.setText("Liste des groupes");
        getContentPane().add(paramGrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 34, -1, -1));

        listeGroupeList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listeGrList.setViewportView(listeGroupeList);

        getContentPane().add(listeGrList, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 120, 140));

        listeUtilGrList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listeUtilGrList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 130, 140));

        searchGrField.setText("search");
        searchGrField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchGrFieldActionPerformed(evt);
            }
        });
        getContentPane().add(searchGrField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 80, -1));

        supprGrButton.setText("Supprimer");
        getContentPane().add(supprGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, -1, -1));

        modifNomLabel.setText("Modifier le nom :");
        getContentPane().add(modifNomLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));
        getContentPane().add(modifNomField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 80, -1));

        accepterButton.setText("Accepter");
        getContentPane().add(accepterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        getContentPane().add(annulerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, -1, -1));

        jLabel1.setText("Liste des utilisateurs du groupe");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        ajoutUtilGrLabel.setText("Ajouter un utilisateur au groupe :");
        getContentPane().add(ajoutUtilGrLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        ajoutGrUtilLabel.setText("Ajouter un groupe pour l'utilisateur :");
        getContentPane().add(ajoutGrUtilLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 320, -1, -1));

        supprDuGroupeLabel.setText("Supprimer du groupe :");
        getContentPane().add(supprDuGroupeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, -1, -1));

        searchRetirerDuGrField.setText("search");
        getContentPane().add(searchRetirerDuGrField, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 80, -1));

        ajoutUtilGrField.setText("search");
        getContentPane().add(ajoutUtilGrField, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 80, -1));

        ajoutGrUtilField.setText("search");
        getContentPane().add(ajoutGrUtilField, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 80, -1));

        retirerDuGrButton.setText("Supprimer");
        getContentPane().add(retirerDuGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, -1, -1));

        ajoutUtilGrButton.setText("Ajouter");
        getContentPane().add(ajoutUtilGrButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, -1, -1));

        ajoutGrUtilButton.setText("Ajouter");
        getContentPane().add(ajoutGrUtilButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchGrFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchGrFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchGrFieldActionPerformed

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreServeurGestGroupe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreServeurGestGroupe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accepterButton;
    private javax.swing.JButton ajoutGrUtilButton;
    private javax.swing.JTextField ajoutGrUtilField;
    private javax.swing.JLabel ajoutGrUtilLabel;
    private javax.swing.JButton ajoutUtilGrButton;
    private javax.swing.JTextField ajoutUtilGrField;
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
    private javax.swing.JTextField searchGrField;
    private javax.swing.JTextField searchRetirerDuGrField;
    private javax.swing.JLabel supprDuGroupeLabel;
    private javax.swing.JButton supprGrButton;
    // End of variables declaration//GEN-END:variables
}