package main;

import java.sql.Connection;
import java.util.ArrayList;

import dao.Connexion;
import entities.Produit;
import dao.DaoProduit;
import dao.intDaoProduit;
import dao.DaoFournisseur;
import dao.intDaoFournisseur;
import entities.Fournisseur;
public class Testmain {
	public static void main(String[] args) throws ClassNotFoundException {
	    intDaoProduit dao = new DaoProduit(); // Instanciation de l'interface
	    // Tester la connexion
	    Connection macon = Connexion.getInstance();
	    System.out.println("Connexion: " + macon);

	    // Lister tous les produits
	    ArrayList<Produit> produits = new ArrayList<>();
	    produits = dao.listerAllProduits();
	    System.out.println("Liste des Produits : " + produits);

	    // Recherche d'un produit
	    Produit p = dao.findProduitById(1);
	    System.out.println("Produit recherché : " + p);

	    // Ajout d'un produit
	    Produit nouveauProduit = new Produit(2, "produit test", 125.5,0.2);
	    dao.ajouter(nouveauProduit);
	    
	    boolean suppressionReussie = dao.deleteProd(1);
        if (suppressionReussie) {
            System.out.println("Le produit a été supprimé avec succès.");
        } else {
            System.out.println("La suppression du produit a échoué.");
        }
        
        Produit produitAModifier = new Produit(122, "Nouveau nom", 150.0, 15.0); // Créez un nouvel objet Produit avec les nouvelles valeurs
        boolean modificationReussie = dao.modifyProd(produitAModifier);
        if (modificationReussie) {
            System.out.println("Le produit a été modifié avec succès.");
        } else {
            System.out.println("La modification du produit a échoué.");
        }
        
        
        intDaoFournisseur daoFournisseur = new DaoFournisseur(); // Créez une instance de DaoFournisseur

        // Ajouter un fournisseur
        Fournisseur nouveauFournisseur = new Fournisseur(1, "mouhamed", "Beja"); // Créez un nouvel objet Fournisseur
        daoFournisseur.ajouter(nouveauFournisseur); // Ajoutez le fournisseur à la base de données
        Fournisseur nouveauFournisseur2 = new Fournisseur(2, "semi", "sousse"); // Créez un nouvel objet Fournisseur
        daoFournisseur.ajouter(nouveauFournisseur2);
        ArrayList<Fournisseur> Fournisseurs = new ArrayList<>();
        Fournisseurs = daoFournisseur.listerAllFournisseurs();
	    System.out.println("Liste des Fournisseurs : " + Fournisseurs);


        // Rechercher un fournisseur par ID
        int idFournisseur = 1; // Spécifiez l'ID du fournisseur à rechercher
        Fournisseur fournisseurRecherche = daoFournisseur.findFournisseurById(idFournisseur);
        System.out.println("Fournisseur recherché : " + fournisseurRecherche);

        // Modifier un fournisseur
        Fournisseur fournisseurAModifier = new Fournisseur(1, "noura", "teboursouk"); // Créez un nouvel objet Fournisseur avec les nouvelles valeurs
        boolean modificationReussie2 = daoFournisseur.modifyFournisseur(fournisseurAModifier);
        if (modificationReussie2) {
            System.out.println("Le fournisseur a été modifié avec succès.");
        } else {
            System.out.println("La modification du fournisseur a échoué.");
        }

        // Supprimer un fournisseur
        boolean suppressionReussie2 = daoFournisseur.deleteFournisseur(2);
        if (suppressionReussie2) {
            System.out.println("Le fournisseur a été supprimé avec succès.");
        } else {
            System.out.println("La suppression du fournisseur a échoué.");
        }
    }
	    
	


}
