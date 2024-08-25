package dao;

import java.util.ArrayList;

import entities.Fournisseur;

public interface intDaoFournisseur {
    void ajouter(Fournisseur f) throws ClassNotFoundException;
    ArrayList<Fournisseur> listerAllFournisseurs() throws ClassNotFoundException;
    Fournisseur findFournisseurById(int numf) throws ClassNotFoundException;
    boolean deleteFournisseur(int numf) throws ClassNotFoundException;
    boolean modifyFournisseur(Fournisseur f) throws ClassNotFoundException;

}
