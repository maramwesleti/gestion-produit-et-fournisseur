package swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestionFournisseur extends JFrame implements ActionListener {
    private JButton btnAjouter;
    private JButton btnLister;
    private JButton btnRechercher;
    private JButton btnModifier;
    private JButton btnSupprimer;

    public InterfaceGestionFournisseur() {
        setTitle("Gestion des Fournisseurs");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnAjouter = new JButton("Ajouter Fournisseur");
      
        btnLister = new JButton("Lister Fournisseurs");
        btnRechercher = new JButton("Rechercher Fournisseur");
        btnModifier = new JButton("Modifier Fournisseur");
        btnSupprimer = new JButton("Supprimer Fournisseur");
        btnAjouter.addActionListener(this);
        btnLister.addActionListener(this);
        btnRechercher.addActionListener(this);
        btnModifier.addActionListener(this);
        btnSupprimer.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            AjouterFournisseurUI ajouterFournisseurUI = new AjouterFournisseurUI();
            ajouterFournisseurUI.setVisible(true);
        
        
    } else if (e.getSource() == btnLister) {
        ListerFournisseursUI listerFournisseursUI = new ListerFournisseursUI();
        listerFournisseursUI.setVisible(true);
    } else if (e.getSource() == btnRechercher) {
        RechercherFournisseurUI rechercherFournisseurUI = new RechercherFournisseurUI();
        rechercherFournisseurUI.setVisible(true);
    } else if (e.getSource() == btnModifier) {
        ModifierFournisseurUI modifierFournisseurUI = new ModifierFournisseurUI();
        modifierFournisseurUI.setVisible(true);
    } else if (e.getSource() == btnSupprimer) {
        SupprimerFournisseurUI supprimerFournisseurUI = new SupprimerFournisseurUI();
        supprimerFournisseurUI.setVisible(true);
    }
    }

    public static void main(String[] args) {
        new InterfaceGestionFournisseur();
    }
}

