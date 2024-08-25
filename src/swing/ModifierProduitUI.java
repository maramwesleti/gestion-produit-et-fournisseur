package swing;
import javax.swing.*;
import dao.DaoProduit;
import entities.Produit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProduitUI extends JFrame {
    private DaoProduit daoProduit;
    private JTextField txtIdProduit;
    private JTextField txtNouveauLibelle;
    private JTextField txtNouveauPrix;
    private JTextField txtNouvelleTVA;
    public ModifierProduitUI() {
        setTitle("Modifier un Produit");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoProduit = new DaoProduit();
        JLabel lblIdProduit = new JLabel("ID Produit:");
        txtIdProduit = new JTextField(10);
        JLabel lblNouveauLibelle = new JLabel("Nouveau Libellé:");
        txtNouveauLibelle = new JTextField(10);
        JLabel lblNouveauPrix = new JLabel("Nouveau Prix:");
        txtNouveauPrix = new JTextField(10);
        JLabel lblNouvelleTVA = new JLabel("Nouvelle TVA:");
        txtNouvelleTVA = new JTextField(10);
        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifierProduit();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(lblIdProduit);
        panel.add(txtIdProduit);
        panel.add(lblNouveauLibelle);
        panel.add(txtNouveauLibelle);
        panel.add(lblNouveauPrix);
        panel.add(txtNouveauPrix);
        panel.add(lblNouvelleTVA);
        panel.add(txtNouvelleTVA);
        panel.add(btnModifier);
        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void modifierProduit() {
        try {
            int idProduit = Integer.parseInt(txtIdProduit.getText());
            String nouveauLibelle = txtNouveauLibelle.getText();
            double nouveauPrix = Double.parseDouble(txtNouveauPrix.getText());
            double nouvelleTVA = Double.parseDouble(txtNouvelleTVA.getText());

            Produit produit = new Produit(idProduit, nouveauLibelle, nouveauPrix, nouvelleTVA);
            boolean modifie = daoProduit.modifyProd(produit);

            if (modifie) {
                JOptionPane.showMessageDialog(this, "Produit modifié avec succès !");
                this.dispose();
                new ListerProduitsUI();
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la modification du produit.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir des valeurs numériques valides.");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification du produit.");
        }
    }
    public static void main(String[] args) {
        new ModifierProduitUI();
    }
}

