package swing;

import javax.swing.*;
import dao.DaoFournisseur;
import entities.Fournisseur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechercherFournisseurUI extends JFrame {
    private DaoFournisseur daoFournisseur;
    private JTextField txtIdFournisseur;
    private JTextArea txtResult;

    public RechercherFournisseurUI() {
        setTitle("Rechercher un Fournisseur");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoFournisseur = new DaoFournisseur();
        JLabel lblIdFournisseur = new JLabel("ID Fournisseur:");
        txtIdFournisseur = new JTextField(10);
        JButton btnRechercher = new JButton("Rechercher");
        txtResult = new JTextArea(5, 20);
        txtResult.setEditable(false);
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherFournisseur();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblIdFournisseur, BorderLayout.WEST);
        panel.add(txtIdFournisseur, BorderLayout.CENTER);
        panel.add(btnRechercher, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(txtResult);
        panel.add(scrollPane, BorderLayout.SOUTH);
        add(panel);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void rechercherFournisseur() {
        try {
            int idFournisseur = Integer.parseInt(txtIdFournisseur.getText());
            Fournisseur fournisseur = daoFournisseur.findFournisseurById(idFournisseur);
            if (fournisseur != null) {
                txtResult.setText("Fournisseur trouvé :\n" + fournisseur.toString());
            } else {
                txtResult.setText("Aucun fournisseur trouvé avec cet identifiant.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un identifiant valide.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche du fournisseur.");
        }
    }

    public static void main(String[] args) {
        new RechercherFournisseurUI();
    }
}
