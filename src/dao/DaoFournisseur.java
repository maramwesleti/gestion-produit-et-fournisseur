package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Fournisseur;



public class DaoFournisseur implements intDaoFournisseur {
    

   
     
    private Connection connection = Connexion.getInstance();

    public DaoFournisseur() {}

    @Override
    public void ajouter(Fournisseur f) throws ClassNotFoundException {
        try {
            String query = "INSERT INTO fournisseur(numf, nomf, adrF) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, f.getNumf());
            preparedStatement.setString(2, f.getNomf());
            preparedStatement.setString(3, f.getAdrF());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Fournisseur> listerAllFournisseurs() throws ClassNotFoundException {
        ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        try {
            String query = "SELECT * FROM fournisseur";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Fournisseur fournisseur = new Fournisseur(resultSet.getInt("numf"), resultSet.getString("nomf"),
                        resultSet.getString("adrF"));
                fournisseurs.add(fournisseur);
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fournisseurs;
    }

    @Override
    public Fournisseur findFournisseurById(int numf) throws ClassNotFoundException {
        Fournisseur fournisseur = null;
        try {
            String query = "SELECT * FROM fournisseur WHERE numf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numf);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fournisseur = new Fournisseur(resultSet.getInt("numf"), resultSet.getString("nomf"),
                        resultSet.getString("adrF"));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fournisseur;
    }

    @Override
    public boolean deleteFournisseur(int numf) throws ClassNotFoundException {
        boolean deleted = false;
        try {
            String query = "DELETE FROM fournisseur WHERE numf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, numf);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                deleted = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public boolean modifyFournisseur(Fournisseur f) throws ClassNotFoundException {
        boolean modified = false;
        try {
            String query = "UPDATE fournisseur SET nomf = ?, adrF = ? WHERE numf = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, f.getNomf());
            preparedStatement.setString(2, f.getAdrF());
            preparedStatement.setInt(3, f.getNumf());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                modified = true;
            }
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return modified;
    }
}
