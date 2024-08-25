package entities;

public class Produit {
	private int idProd;
    private String libProd;
    private double puProd;
    private double tvaProd;

    public Produit(int idProd, String libProd, double puProd, double tvaProd) {
        this.idProd = idProd;
        this.libProd = libProd;
        this.puProd = puProd;
        this.tvaProd = tvaProd;
    }
    public Produit() {
        // Constructeur par défaut
    }
    public int getIdProd() {
        return idProd;
    }

    public String getLibProd() {
        return libProd;
    }

    public double getPuProd() {
        return puProd;
    }

    public double getTvaProd() {
        return tvaProd;
    }
    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public void setLibProd(String libProd) {
        this.libProd = libProd;
    }

    public void setPuProd(double puProd) {
        this.puProd = puProd;
    }

    public void setTvaProd(double tvaProd) {
        this.tvaProd = tvaProd;
    }
    
    public String toString() {
        return "Produit{" +
                "idProd=" + idProd +
                ", libProd='" + libProd + '\'' +
                ", puProd=" + puProd +
                ", tvaProd=" + tvaProd +
                '}';
    }

}
