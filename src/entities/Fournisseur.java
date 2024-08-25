package entities;

public class Fournisseur {
	 private int numf;
	    private String nomf;
	    private String adrF;

	    public Fournisseur(int numf, String nomf, String adrF) {
	        this.numf = numf;
	        this.nomf = nomf;
	        this.adrF = adrF;
	    }
	    public int getNumf() {
	        return numf;
	    }

	    public String getNomf() {
	        return nomf;
	    }

	    public String getAdrF() {
	        return adrF;
	    }
	    
	    public void setNumf(int numf) {
	        this.numf = numf;
	    }

	    public void setNomf(String nomf) {
	        this.nomf = nomf;
	    }

	    public void setAdrF(String adrF) {
	        this.adrF = adrF;
	    }
	    public String toString() {
	        return "Fournisseur{" +
	                "numf=" + numf +
	                ", nomf='" + nomf + '\'' +
	                ", adrF='" + adrF + '\'' +
	                '}';
	    }


}
