package swing;

import javax.swing.*;
import dao.DaoFournisseur;
import entities.Fournisseur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierFournisseurUI extends JFrame {
    private DaoFournisseur daoFournisseur;
    private JTextField txtNumFournisseur;
    private JTextField txtNouveauNom;
    private JTextField txtNouvelleAdresse;

    public ModifierFournisseurUI() {
        setTitle("Modifier un Fournisseur");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoFournisseur = new DaoFournisseur();
        JLabel lblNumFournisseur = new JLabel("Numéro Fournisseur:");
        txtNumFournisseur = new JTextField(10);
        JLabel lblNouveauNom = new JLabel("Nouveau Nom:");
        txtNouveauNom = new JTextField(10);
        JLabel lblNouvelleAdresse = new JLabel("Nouvelle Adresse:");
        txtNouvelleAdresse = new JTextField(10);
        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierFournisseur();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(lblNumFournisseur);
        panel.add(txtNumFournisseur);
        panel.add(lblNouveauNom);
        panel.add(txtNouveauNom);
        panel.add(lblNouvelleAdresse);
        panel.add(txtNouvelleAdresse);
        panel.add(btnModifier);
        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void modifierFournisseur() {
        try {
            int numFournisseur = Integer.parseInt(txtNumFournisseur.getText());
            String nouveauNom = txtNouveauNom.getText();
            String nouvelleAdresse = txtNouvelleAdresse.getText();

            Fournisseur fournisseur = new Fournisseur(numFournisseur, nouveauNom, nouvelleAdresse);
            boolean modifie = daoFournisseur.modifyFournisseur(fournisseur);

            if (modifie) {
                JOptionPane.showMessageDialog(this, "Fournisseur modifié avec succès !");
                this.dispose();
                new ListerFournisseursUI();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la modification du fournisseur.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir des valeurs numériques valides.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification du fournisseur.");
        }
    }

    public static void main(String[] args) {
        new ModifierFournisseurUI();
    }
}
