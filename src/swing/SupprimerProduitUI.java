package swing;

import dao.DaoProduit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupprimerProduitUI extends JFrame implements ActionListener {
    private JTextField txtIdProd;
    private JButton btnSupprimer;
    private DaoProduit daoProduit;

    public SupprimerProduitUI() {
        setTitle("Supprimer un Produit");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoProduit = new DaoProduit();

        txtIdProd = new JTextField(10);
        btnSupprimer = new JButton("Supprimer");

        btnSupprimer.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("ID Produit à supprimer:"));
        panel.add(txtIdProd);
        panel.add(new JLabel()); 
        panel.add(btnSupprimer);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSupprimer) {
            supprimerProduit();
        }
    }

    private void supprimerProduit() {
        try {
            int idProd = Integer.parseInt(txtIdProd.getText());
            boolean supprime = daoProduit.deleteProd(idProd);
            if (supprime) {
                JOptionPane.showMessageDialog(this, "Produit supprimé avec succès !");
               
                this.dispose();
              
                new ListerProduitsUI();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la suppression du produit.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un identifiant de produit valide.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression du produit.");
        }
    }

    public static void main(String[] args) {
        new SupprimerProduitUI();
    }
}

