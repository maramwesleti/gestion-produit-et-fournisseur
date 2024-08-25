package swing;

import javax.swing.*;
import dao.DaoFournisseur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupprimerFournisseurUI extends JFrame implements ActionListener {
    private JTextField txtNumFournisseur;
    private JButton btnSupprimer;
    private DaoFournisseur daoFournisseur;

    public SupprimerFournisseurUI() {
        setTitle("Supprimer un Fournisseur");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoFournisseur = new DaoFournisseur();

        txtNumFournisseur = new JTextField(10);
        btnSupprimer = new JButton("Supprimer");

        btnSupprimer.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Numéro Fournisseur à supprimer:"));
        panel.add(txtNumFournisseur);
        panel.add(new JLabel());
        panel.add(btnSupprimer);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSupprimer) {
            supprimerFournisseur();
        }
    }

    private void supprimerFournisseur() {
        try {
            int numFournisseur = Integer.parseInt(txtNumFournisseur.getText());
            boolean supprime = daoFournisseur.deleteFournisseur(numFournisseur);
            if (supprime) {
                JOptionPane.showMessageDialog(this, "Fournisseur supprimé avec succès !");
            
                this.dispose();
            
                new ListerFournisseursUI();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression du fournisseur.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un numéro de fournisseur valide.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du fournisseur.");
        }
    }

    public static void main(String[] args) {
        new SupprimerFournisseurUI();
    }
}

