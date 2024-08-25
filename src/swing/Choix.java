package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Choix extends JFrame implements ActionListener {
    private JButton btnFournisseur;
    private JButton btnProduit;
    private Image backgroundImage;

    public Choix() {
        setTitle("Choix Fournisseur/Produit");
        setSize(400, 300); // Ajuster la taille pour mieux afficher l'image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Charger l'image de fond depuis les ressources du projet
        backgroundImage = loadImage("/imageproduit.jpg");

        // Créer les boutons
        btnFournisseur = new JButton("Gestion Fournisseur");
        btnProduit = new JButton("Gestion Produit");

        // Définir la couleur bleu pétrole
        Color bluePetroleum = new Color(0x004B49);

        // Appliquer la couleur bleu pétrole aux boutons
        btnFournisseur.setBackground(bluePetroleum);
        btnProduit.setBackground(bluePetroleum);

        btnFournisseur.setForeground(Color.WHITE); // Texte en blanc pour contraste
        btnProduit.setForeground(Color.WHITE); // Texte en blanc pour contraste

        btnFournisseur.addActionListener(this);
        btnProduit.addActionListener(this);

        // Créer un panel personnalisé pour l'image de fond
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Créer un panel pour les boutons et les placer en bas
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Rendre le panel transparent
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnFournisseur);
        buttonPanel.add(btnProduit);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(backgroundPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Méthode pour charger l'image depuis les ressources
    private Image loadImage(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFournisseur) {
            new InterfaceGestionFournisseur();
        } else if (e.getSource() == btnProduit) {
            new InterfaceGestionProduit();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Choix());
    }

    // Classe interne pour dessiner l'image de fond
    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
