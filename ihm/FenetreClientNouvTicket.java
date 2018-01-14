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
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
    private DefaultComboBoxModel comboModele;
    public FenetreClientNouvTicket(Client client) {
        this.client = client;
        this.listeGroupes = client.getListeGroupe();
        initComponents();
        ImageIcon img = new ImageIcon("icon.jpg");
        this.setIconImage(img.getImage());        
        
        
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

        groupeComboBox = new javax.swing.JComboBox<>();
        groupeLabel = new javax.swing.JLabel();
        nomTicketLabel = new javax.swing.JLabel();
        nomTicketField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        creerButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Création de ficket");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        groupeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Groupe 1", "Groupe 2" }));
        comboModele = new DefaultComboBoxModel();
        setComboBoxModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(56, 18, 0, 0);
        getContentPane().add(groupeComboBox, gridBagConstraints);

        groupeLabel.setText("Groupe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(59, 10, 0, 0);
        getContentPane().add(groupeLabel, gridBagConstraints);

        nomTicketLabel.setText("Nom du ticket :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 10, 0, 0);
        getContentPane().add(nomTicketLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 174;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 18, 0, 0);
        getContentPane().add(nomTicketField, gridBagConstraints);

        jLabel1.setText("Message :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(44, 10, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        creerButton.setText("Créer");
        this.getRootPane().setDefaultButton(creerButton);
        creerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 82, 11, 0);
        getContentPane().add(creerButton, gridBagConstraints);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 18, 11, 10);
        getContentPane().add(annulerButton, gridBagConstraints);

        messageArea.setColumns(20);
        messageArea.setRows(5);
        jScrollPane1.setViewportView(messageArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 157;
        gridBagConstraints.ipady = 97;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(38, 18, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void creerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerButtonActionPerformed
        if(client != null){
            String nomTicket = nomTicketField.getText();
            int indGroupe = groupeComboBox.getSelectedIndex();
            String message = messageArea.getText();        
            Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());        
            Groupe groupe = listeGroupes.get(indGroupe);
            if(!nomTicket.equals("") && !message.equals("")){
                MessageConversation messageConv = new MessageConversation(-1, client.getUtilisateurClient(), message, date, EtatMessage.NON_RECU_PAR_LE_SERVEUR, true);
                client.creerTicket(groupe, nomTicket, messageConv);
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Champs manquants, veuillez les remplir.");
            }            
        }        
        
    }//GEN-LAST:event_creerButtonActionPerformed

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerButtonActionPerformed
    private void setComboBoxModel(){
        List<Groupe> groupes = client.getListeGroupe();
        if(groupes != null){
            for(Groupe gr : groupes){
                comboModele.addElement(gr.getIdGroupe());
            }            
        }
        groupeComboBox.setModel(comboModele);       
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerButton;
    private javax.swing.JButton creerButton;
    private javax.swing.JComboBox<String> groupeComboBox;
    private javax.swing.JLabel groupeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JTextField nomTicketField;
    private javax.swing.JLabel nomTicketLabel;
    // End of variables declaration//GEN-END:variables
}
