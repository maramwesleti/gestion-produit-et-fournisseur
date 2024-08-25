package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Produit;

public class DaoProduit implements intDaoProduit {
    // Initialiser la connexion vers la BD via la classe statique Connexion:
    private Connection maConnection = Connexion.getInstance();

    public DaoProduit() {}

    // Recherche d'un produit par idProd
    public Produit findProduitById(int idP) throws ClassNotFoundException {
        Produit p = new Produit();
        try {
            Statement statement = maConnection.createStatement();
            String req = "SELECT libprod, puprod, tvaProd FROM produit WHERE idProd=" + idP;
            ResultSet res = statement.executeQuery(req);
            if (res.next()) {
                p.setIdProd(idP);
                p.setLibProd(res.getString(1));
                p.setPuProd(res.getDouble(2));
                p.setTvaProd(res.getDouble(3));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return p;
    }

    public void ajouter(Produit p) throws ClassNotFoundException {
        int idProd = p.getIdProd();
        String libProd = p.getLibProd();
        double puProd = p.getPuProd();
        double tvaProd = p.getTvaProd();

        try {
            Statement statement = maConnection.createStatement();
            String req = "INSERT INTO produit(idprod, libprod, puprod, tvaProd) VALUES(" + idProd + ", '" + libProd + "', " + puProd + ", " + tvaProd + ")";
            System.out.println("Requête insertion : " + req);
            statement.executeUpdate(req);
            statement.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public ArrayList<Produit> listerAllProduits() throws ClassNotFoundException {
        ArrayList<Produit> produits = new ArrayList<>();
        try {
            Statement mon_statement = maConnection.createStatement();
            ResultSet mon_resultat = mon_statement.executeQuery("SELECT idProd, libProd, puProd, tvaProd FROM produit");
            while (mon_resultat.next()) {
                Produit p = new Produit();
                p.setIdProd(mon_resultat.getInt(1));
                p.setLibProd(mon_resultat.getString(2));
                p.setPuProd(mon_resultat.getDouble(3));
                p.setTvaProd(mon_resultat.getDouble(4));
                produits.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    public boolean deleteProd(int idP) throws ClassNotFoundException {
        boolean deleted = false;
        try {
            Statement statement = maConnection.createStatement();
            String req = "DELETE FROM produit WHERE idProd = " + idP;
            int rowsDeleted = statement.executeUpdate(req);
            if (rowsDeleted > 0) {
                deleted = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public boolean modifyProd(Produit p) throws ClassNotFoundException {
        boolean modified = false;
        try {
            Statement statement = maConnection.createStatement();
            String req = "UPDATE produit SET libProd = '" + p.getLibProd() + "', puProd = " + p.getPuProd() + ", tvaProd = " + p.getTvaProd() + " WHERE idProd = " + p.getIdProd();
            int rowsUpdated = statement.executeUpdate(req);
            if (rowsUpdated > 0) {
                modified = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modified;
    }
}
