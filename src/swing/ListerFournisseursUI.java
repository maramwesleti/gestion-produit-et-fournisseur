package swing;

import javax.swing.*;
import dao.DaoFournisseur;
import entities.Fournisseur;
import java.awt.*;
import java.util.ArrayList;

public class ListerFournisseursUI extends JFrame {
    private DaoFournisseur daoFournisseur;

    public ListerFournisseursUI() {
        setTitle("Liste des Fournisseurs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        daoFournisseur = new DaoFournisseur();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnActualiser = new JButton("Actualiser");
        btnActualiser.addActionListener(e -> {
           
            try {
                ArrayList<Fournisseur> fournisseurs = daoFournisseur.listerAllFournisseurs();
                if (fournisseurs.isEmpty()) {
                    textArea.setText("Aucun fournisseur trouvé.");
                } else {
                    StringBuilder message = new StringBuilder("Liste des fournisseurs :\n");
                    for (Fournisseur fournisseur : fournisseurs) {
                        message.append(fournisseur).append("\n");
                    }
                    textArea.setText(message.toString());
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                textArea.setText("Erreur lors de la récupération des fournisseurs.");
            }
        });

        panel.add(btnActualiser, BorderLayout.SOUTH);

        add(panel);

        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new ListerFournisseursUI();
    }
}

