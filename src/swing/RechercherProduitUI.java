package swing;
import javax.swing.*;
import dao.DaoProduit;
import entities.Produit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RechercherProduitUI extends JFrame {
    private DaoProduit daoProduit;
    private JTextField txtIdProduit;
    private JTextArea txtResult;

    public RechercherProduitUI() {
        setTitle("Rechercher un Produit");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoProduit = new DaoProduit();
        JLabel lblIdProduit = new JLabel("ID Produit:");
        txtIdProduit = new JTextField(10);
        JButton btnRechercher = new JButton("Rechercher");
        txtResult = new JTextArea(5, 20);
        txtResult.setEditable(false);
        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechercherProduit();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(lblIdProduit, BorderLayout.WEST);
        panel.add(txtIdProduit, BorderLayout.CENTER);
        panel.add(btnRechercher, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(txtResult);
        panel.add(scrollPane, BorderLayout.SOUTH);
        add(panel);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    private void rechercherProduit() {
        try {
            int idProduit = Integer.parseInt(txtIdProduit.getText());
            Produit produit = daoProduit.findProduitById(idProduit);
            if (produit != null) {
                txtResult.setText("Produit trouvé :\n" + produit.toString());
            } else {
                txtResult.setText("Aucun produit trouvé avec cet identifiant.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un identifiant valide.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche du produit.");
        }
    }

    public static void main(String[] args) {
        new RechercherProduitUI();
    }
}
