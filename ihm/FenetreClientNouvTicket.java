/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import client.Client;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import modele.EtatMessage;
import modele.Groupe;
import modele.MessageConversation;

/**
 *
 * @author Lucas
 */
public class FenetreClientNouvTicket extends javax.swing.JFrame {

    /**
     * Creates new form FenetreClientNouvTicket
     */
    private Client client;
    private List<Groupe> listeGroupes;
    public FenetreClientNouvTicket(Client client) {
        initComponents();
        this.client = client;
        listeGroupes = client.getListeGroupe();
        
    }
    //DEBUG
    public FenetreClientNouvTicket() {
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

        groupeComboBox = new javax.swing.JComboBox<>();
        groupeLabel = new javax.swing.JLabel();
        nomTicketLabel = new javax.swing.JLabel();
        nomTicketField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        messageArea = new javax.swing.JTextField();
        creerButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Création de ficket");

        groupeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Groupe 1", "Groupe 2" }));
        groupeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(getComboBoxModel()));

        groupeLabel.setText("Groupe :");

        nomTicketLabel.setText("Nom du ticket :");

        jLabel1.setText("Message :");

        creerButton.setText("Créer");
        creerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerButtonActionPerformed(evt);
            }
        });

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(messageArea, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(nomTicketLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(groupeLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(groupeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomTicketField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(237, 237, 237))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(creerButton)
                .addGap(34, 34, 34)
                .addComponent(annulerButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupeLabel))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomTicketLabel)
                    .addComponent(nomTicketField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(messageArea, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creerButton)
                    .addComponent(annulerButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        String nomTicket = nomTicketField.getText();
        String nomGr = (String) groupeComboBox.getSelectedItem();
        String message = messageArea.getText();        
        Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());        
        Groupe groupe = new Groupe(nomGr);
        for(Groupe gr : listeGroupes){
            if(groupe.equals(gr)){
                groupe = gr;
            }
        }
        MessageConversation messageConv = new MessageConversation(-1, client.getUtilisateurClient(), message, date, EtatMessage.NON_RECU_PAR_LE_SERVEUR, true);
        client.creerTicket(groupe, nomTicket, messageConv);
    }//GEN-LAST:event_creerButtonActionPerformed

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_annulerButtonActionPerformed
    private String[] getComboBoxModel(){
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
            java.util.logging.Logger.getLogger(FenetreClientNouvTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreClientNouvTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreClientNouvTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreClientNouvTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreClientNouvTicket().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerButton;
    private javax.swing.JButton creerButton;
    private javax.swing.JComboBox<String> groupeComboBox;
    private javax.swing.JLabel groupeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField messageArea;
    private javax.swing.JTextField nomTicketField;
    private javax.swing.JLabel nomTicketLabel;
    // End of variables declaration//GEN-END:variables
}
