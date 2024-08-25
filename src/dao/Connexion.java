package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connexion {
private static Connection maCx;
private Connexion(){ 
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    maCx = DriverManager.getConnection("jdbc:mysql://localhost/commerciale", "root", "");
	} catch (SQLException | ClassNotFoundException e) {
	    e.printStackTrace();
	    System.out.println("Problème Connexion sur la BD " + e.getMessage());
	}

}
public static Connection getInstance(){
if(maCx == null)
new Connexion();
return maCx;
}
}