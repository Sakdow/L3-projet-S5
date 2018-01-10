/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
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
    private Map<Utilisateur, List<Groupe>> utilMap;
    private List<Groupe> listeGr;
    private Serveur serveur;
    /**
     * Creates new form FenetreServeurGestGroupe
     */
    public FenetreServeurGestUtil(Serveur serveur) {
        this.serveur = serveur;
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
        modifMdpField = new javax.swing.JTextField();
        confirmMdpLabel = new javax.swing.JLabel();
        confirmMdpField = new javax.swing.JTextField();
        searchUtilLabel = new javax.swing.JLabel();
        searchSupprGrLabel = new javax.swing.JLabel();
        ajoutGrCombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion d'utilisateurs");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        paramGrLabel.setText("Liste des utilisateurs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 49, 0, 0);
        getContentPane().add(paramGrLabel, gridBagConstraints);

        listeUtilList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listeUtilList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listeUtilListValueChanged(evt);
            }
        });
        listeGrList.setViewportView(listeUtilList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 117;
        gridBagConstraints.ipady = 117;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 30, 0, 0);
        getContentPane().add(listeGrList, gridBagConstraints);

        listeGrUtilList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listeGrUtilList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 107;
        gridBagConstraints.ipady = 117;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        searchUtilField.setText("search");
        searchUtilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchUtilFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 74;
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
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 9, 0, 0);
        getContentPane().add(supprUtilButton, gridBagConstraints);

        modifNomLabel.setText("Nom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 30, 0, 0);
        getContentPane().add(modifNomLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 9;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 9, 0, 0);
        getContentPane().add(modifNomField, gridBagConstraints);

        accepterButton.setText("Accepter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 190, 18, 0);
        getContentPane().add(accepterButton, gridBagConstraints);

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 18, 0);
        getContentPane().add(annulerButton, gridBagConstraints);

        jLabel1.setText("Liste des groupes de l'utilisateur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(40, 250, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        ajoutGrUtilLabel.setText("Ajouter un groupe pour l'utilisateur :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 250, 0, 0);
        getContentPane().add(ajoutGrUtilLabel, gridBagConstraints);

        supprDuGroupeLabel.setText("Supprimer du groupe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 19;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 25, 0, 0);
        getContentPane().add(supprDuGroupeLabel, gridBagConstraints);

        searchSupprGrField.setText("search");
        searchSupprGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSupprGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 250, 0, 0);
        getContentPane().add(searchSupprGrField, gridBagConstraints);

        searchAjoutGrField.setText("search");
        searchAjoutGrField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchAjoutGrFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 250, 0, 0);
        getContentPane().add(searchAjoutGrField, gridBagConstraints);

        retirerGrButton.setText("Supprimer");
        retirerGrButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retirerGrButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 29);
        getContentPane().add(retirerGrButton, gridBagConstraints);

        ajoutGrUtilButton.setText("Ajouter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 21;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(ajoutGrUtilButton, gridBagConstraints);

        modifPrenomLabel.setText("Prénom :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        getContentPane().add(modifPrenomLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 13;
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 0);
        getContentPane().add(modifMdpField, gridBagConstraints);

        confirmMdpLabel.setText("Confirmer mot de passe :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 0);
        getContentPane().add(confirmMdpLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.ipadx = 114;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 9, 0, 0);
        getContentPane().add(confirmMdpField, gridBagConstraints);

        searchUtilLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 89;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        getContentPane().add(searchUtilLabel, gridBagConstraints);

        searchSupprGrLabel.setText("Recherche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 109;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 250, 0, 0);
        getContentPane().add(searchSupprGrLabel, gridBagConstraints);

        ajoutGrCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboGrModele = new DefaultComboBoxModel();
        setComboGrModel();
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 250, 0, 0);
        getContentPane().add(ajoutGrCombo, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        this.dispose();
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
        int indUtil = listeUtilList.getSelectedIndex();
        Utilisateur utilSelect = (Utilisateur) listeModeleUtil.get(indUtil);
        //Supprimer par le serveur
        //TODO
        listeModeleGr.removeElement(utilSelect);
        listeUtilList.setModel(listeModeleGr);
        //On supprime le groupe de la liste
        listeGr.remove(utilSelect);        
    }//GEN-LAST:event_supprUtilButtonActionPerformed

    private void listeUtilListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listeUtilListValueChanged
        int response = JOptionPane.showConfirmDialog(null, "Voulez-vous sauvegarder les modifications ?", "Confirm",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            //On dit au serveur de modifier
            //TODO listeGr
        }
        //On affiche les groupes du nouvel user selectionne
        setListeModelGr();
    }//GEN-LAST:event_listeUtilListValueChanged

    private void retirerGrButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retirerGrButtonActionPerformed
        //groupe selectionné
        int index = listeGrUtilList.getSelectedIndex();
        //util selectionné
        int indUtil = listeUtilList.getSelectedIndex();
        Utilisateur utilSelect = (Utilisateur) listeModeleUtil.get(indUtil);
        listeModeleGr.remove(index);
        listeGrUtilList.setModel(listeModeleGr);        
        //On supprime le groupe de la liste
        listeGr.remove(index);        
        
    }//GEN-LAST:event_retirerGrButtonActionPerformed
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
    private void setListeModelGr(){
        int indUtil = listeUtilList.getSelectedIndex();
        if(indUtil != -1){
            Utilisateur utilSelect = (Utilisateur) listeModeleUtil.get(indUtil);
            /*Set<Groupe> groupes = serveur.groupesUtilisateur(utilSelect);
            //Ajout de chaque groupe dans la JList
            for(Groupe gr : groupes){
                listeModeleGr.addElement(gr);
                //Mise a jour de la map
                List<Groupe> liste = utilMap.get(utilSelect);
                liste.add(gr);
                utilMap.put(utilSelect, liste);

            }*/
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accepterButton;
    private javax.swing.JComboBox<String> ajoutGrCombo;
    private javax.swing.JButton ajoutGrUtilButton;
    private javax.swing.JLabel ajoutGrUtilLabel;
    private javax.swing.JButton annulerButton;
    private javax.swing.JTextField confirmMdpField;
    private javax.swing.JLabel confirmMdpLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane listeGrList;
    private javax.swing.JList<String> listeGrUtilList;
    private javax.swing.JList<String> listeUtilList;
    private javax.swing.JTextField modifMdpField;
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
