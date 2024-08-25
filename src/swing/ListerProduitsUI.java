package swing;


	import javax.swing.*;

import dao.DaoProduit;
import entities.Produit;

import java.awt.*;
	import java.util.ArrayList;

	public class ListerProduitsUI extends JFrame {
	    private DaoProduit daoProduit;

	    public ListerProduitsUI() {
	        setTitle("Liste des Produits");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        daoProduit = new DaoProduit();

	        JPanel panel = new JPanel();
	        panel.setLayout(new BorderLayout());

	        JTextArea textArea = new JTextArea();
	        textArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(textArea);
	        panel.add(scrollPane, BorderLayout.CENTER);

	        JButton btnActualiser = new JButton("Actualiser");
	        btnActualiser.addActionListener(e -> {
	            
	            try {
	                ArrayList<Produit> produits = daoProduit.listerAllProduits();
	                if (produits.isEmpty()) {
	                    textArea.setText("Aucun produit trouvé.");
	                } else {
	                    StringBuilder message = new StringBuilder("Liste des produits :\n");
	                    for (Produit produit : produits) {
	                        message.append(produit).append("\n");
	                    }
	                    textArea.setText(message.toString());
	                    
	                }
	            } catch (ClassNotFoundException ex) {
	                ex.printStackTrace();
	                textArea.setText("Erreur lors de la récupération des produits.");
	            }
	        });

	        panel.add(btnActualiser, BorderLayout.SOUTH);

	        add(panel);

	        setLocationRelativeTo(null); 
	        setVisible(true);
	        
	    }

	    public static void main(String[] args) {
	        new ListerProduitsUI();
	    }
	}


