package swing;

import javax.swing.*;
import dao.DaoProduit;
import entities.Produit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterProduitUI extends JFrame implements ActionListener {
    private JTextField txtIdProd;
    private JTextField txtLibProd;
    private JTextField txtPuProd;
    private JTextField txtTvaProd;
    private JButton btnAjouter;
    private DaoProduit daoProduit;

    public AjouterProduitUI() {
        setTitle("Ajouter un Produit");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        daoProduit = new DaoProduit();
        txtIdProd = new JTextField(10);
        txtLibProd = new JTextField(10);
        txtPuProd = new JTextField(10);
        txtTvaProd = new JTextField(10);
        btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("ID Produit:"));
        panel.add(txtIdProd);
        panel.add(new JLabel("Libellé Produit:"));
        panel.add(txtLibProd);
        panel.add(new JLabel("Prix unitaire:"));
        panel.add(txtPuProd);
        panel.add(new JLabel("TVA:"));
        panel.add(txtTvaProd);
        panel.add(btnAjouter);
        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
          
            int idProd = Integer.parseInt(txtIdProd.getText());
            String libProd = txtLibProd.getText();
            double puProd = Double.parseDouble(txtPuProd.getText());
            double tvaProd = Double.parseDouble(txtTvaProd.getText());

          
            Produit produit = new Produit(idProd, libProd, puProd, tvaProd);

            
            try {
                daoProduit.ajouter(produit);
                JOptionPane.showMessageDialog(this, "Produit ajouté avec succès !");
             
                this.dispose();
                new ListerProduitsUI();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du produit.");
            }

        }
    }

    public static void main(String[] args) {
        new AjouterProduitUI();
    }
}
