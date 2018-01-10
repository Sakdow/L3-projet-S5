/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import client.Client;
import client.Reseaux;
import client.ThreadEcoute;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import message.MessageReponseConnexion;

/**
 *
 * @author Lucas
 */
public class FenetreClientConnexion extends javax.swing.JFrame {
    private FenetreClient fenetre;
    /**
     * Creates new form FenetreConnexion
     */
    public FenetreClientConnexion() {
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

        titreLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        connexionButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        usernameLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexion");
        setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titreLabel.setText("Connexion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 150, 0, 0);
        getContentPane().add(titreLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 184;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 110, 0, 100);
        getContentPane().add(usernameTextField, gridBagConstraints);

        connexionButton.setText("Connexion");
        connexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 170, 17, 0);
        getContentPane().add(connexionButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 184;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 110, 0, 100);
        getContentPane().add(passwordField, gridBagConstraints);

        passwordLabel.setText("Mot de passe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 180, 0, 0);
        getContentPane().add(passwordLabel, gridBagConstraints);

        usernameLabel1.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(51, 200, 0, 0);
        getContentPane().add(usernameLabel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionButtonActionPerformed
        Reseaux reseau = new Reseaux("77.194.181.122", 8090);
        String id = usernameTextField.getText();
        String mdp = new String(passwordField.getPassword());
        MessageReponseConnexion messReponse = reseau.connexionServeur(id, mdp);
        if(messReponse.getAccepte()){
            Client client = new Client(messReponse.getUtilisateur(), reseau);
            ThreadEcoute thread = new ThreadEcoute(reseau, client);
            fenetre = new FenetreClient(client, thread);
            fenetre.setVisible(true);
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(null, "Informations incorrectes");
        }
    }//GEN-LAST:event_connexionButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FenetreClientConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreClientConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreClientConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreClientConnexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreClientConnexion().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connexionButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel titreLabel;
    private javax.swing.JLabel usernameLabel1;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
