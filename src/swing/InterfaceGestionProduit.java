package swing ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestionProduit extends JFrame {
    public InterfaceGestionProduit() {
        setTitle("Gestion des Produits");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnAjouter = new JButton("Ajouter Produit");
        JButton btnLister = new JButton("Lister Produits");
        JButton btnRechercher = new JButton("Rechercher Produit");
        JButton btnModifier = new JButton("Modifier Produit");
        JButton btnSupprimer = new JButton("Supprimer Produit");
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterProduitUI ajouterProduitUI = new AjouterProduitUI();
                ajouterProduitUI.setVisible(true);
            }
        });

        btnLister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListerProduitsUI listerProduitsUI = new ListerProduitsUI();
                listerProduitsUI.setVisible(true);
            }
        });

        btnRechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RechercherProduitUI rechercherProduitUI = new RechercherProduitUI();
                rechercherProduitUI.setVisible(true);
            }
        });
        
        btnModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifierProduitUI modifierProduitUI = new ModifierProduitUI();
                modifierProduitUI.setVisible(true);
            }
        });

        btnSupprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupprimerProduitUI supprimerProduitUI = new SupprimerProduitUI();
                supprimerProduitUI.setVisible(true);
            }
        });

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(btnAjouter);
        panel.add(btnLister);
        panel.add(btnRechercher);
        panel.add(btnModifier);
        panel.add(btnSupprimer);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new InterfaceGestionProduit();
    }
}
