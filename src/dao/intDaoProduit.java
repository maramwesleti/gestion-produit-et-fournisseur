package dao;

import java.util.ArrayList;

import entities.Produit;

public interface intDaoProduit {
	 void ajouter(Produit e) throws ClassNotFoundException;
	    ArrayList<Produit> listerAllProduits() throws ClassNotFoundException;
	    Produit findProduitById(int idP) throws ClassNotFoundException;
	    boolean deleteProd(int idP) throws ClassNotFoundException;
	    boolean modifyProd(Produit e) throws ClassNotFoundException;

}
