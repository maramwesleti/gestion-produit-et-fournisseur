package main;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MainFile {
	 private static Connection connection;

	    public static void main(String[] args) {
	        try {
	            // Connexion à la base de données   
	            connection = DriverManager.getConnection("jdbc:mysql://localhost/scolar", "root", "");

	            while (true) {
	                String choice = JOptionPane.showInputDialog("Menu:\n1. Lire le contenu du fichier des noms\n2. Lire le contenu du fichier des notes\n3. Ajouter au fichier des noms\n4. Ajouter au fichier des notes\n5. Voir les informations sur le fichier des noms\n6. Voir les informations sur le fichier des notes\n7. Voir le contenu de la table Tmoyenne\n0. Quitter");
	                if (choice == null) {
	                    // Handle the case where the user closes the input dialog
	                    JOptionPane.showMessageDialog(null, "Merci d'avoir utilisé le programme !");
	                    break; // Exit the loop
	                }

	                switch (choice) {
	                    case "1":
	                        readAndDisplayFile("fileNames.txt");
	                        break;
	                    case "2":
	                        readAndDisplayFile("fileNotes.txt");
	                        break;
	                    case "3":
	                        addToFile("fileNames.txt");
	                        updateTmoyenne(connection);
	                        break;
	                    case "4":
	                        addToFile("fileNotes.txt");
	                        updateTmoyenne(connection);
	                        break;
	                    case "5":
	                        showFileInfo("fileNames.txt");
	                        break;
	                    case "6":
	                        showFileInfo("fileNotes.txt");
	                        break;
	                    case "7":
	                        displayTmoyenne(connection);
	                        break;
	                    case "0":
	                        connection.close();
	                        System.exit(0);
	                    default:
	                        JOptionPane.showMessageDialog(null, "Choix invalide !");
	                        break;
	                }
	            }
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the connection in case of any exceptions
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    private static void readAndDisplayFile(String fileName) throws IOException {
	        List<String> lines = readLinesFromFile(fileName);
	        StringBuilder content = new StringBuilder();
	        for (String line : lines) {
	            content.append(line).append("\n");
	        }
	        JOptionPane.showMessageDialog(null, "Contenu de " + fileName + ":\n" + content.toString());
	    }

	    private static List<String> readLinesFromFile(String fileName) throws IOException {
	        List<String> lines = new ArrayList<>();
	        BufferedReader reader = new BufferedReader(new FileReader(fileName));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            lines.add(line);
	        }
	        reader.close();
	        return lines;
	    }

	    private static void addToFile(String fileName) throws IOException {
	        String newEntry = JOptionPane.showInputDialog("Entrez la nouvelle entrée :");
	        FileWriter writer = new FileWriter(fileName, true);
	        // Split the new entry by the first space to separate the first and last names
	        String[] nameParts = newEntry.split(" ", 2);
	        if (nameParts.length < 2) {
	            System.out.println("Attention: Format de nom non conforme - " + newEntry);
	            // Handle the case where the name is not in the expected format
	            JOptionPane.showMessageDialog(null, "Format de nom non conforme : " + newEntry);
	            writer.close();
	            return;
	        }
	        writer.write(newEntry + "\n");
	        writer.close();
	        JOptionPane.showMessageDialog(null, "Nouvelle entrée ajoutée avec succès à " + fileName);
	    }

	    private static void showFileInfo(String fileName) {
	        File file = new File(fileName);
	        String info = "Informations sur " + fileName + ":\n";
	        info += "Chemin absolu: " + file.getAbsolutePath() + "\n";
	        info += "Nom: " + file.getName() + "\n";
	        info += "Longueur: " + file.length() + " octets\n";
	        JOptionPane.showMessageDialog(null, info);
	    }

	    private static void updateTmoyenne(Connection connection) throws SQLException {
	        try {
	            List<String> names = readLinesFromFile("fileNames.txt");
	            List<String> notes = readLinesFromFile("fileNotes.txt");
	            List<String> moyennes = calculateAverages(notes);
	            insertDataIntoTmoyenne(connection, names, moyennes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static List<String> calculateAverages(List<String> notes) {
	        List<String> moyennes = new ArrayList<>();
	        for (String line : notes) {
	            String[] noteValues = line.split(" ");
	            double sum = 0;
	            for (String note : noteValues) {
	                sum += Double.parseDouble(note);
	            }
	            double average = sum / noteValues.length;
	            String resultat = (average >= 10) ? "Admis" : "Recalé";
	            moyennes.add(String.format("%.2f %s", average, resultat));
	        }
	        return moyennes;
	    }

	    private static void insertDataIntoTmoyenne(Connection connection, List<String> names, List<String> moyennes) throws SQLException {
	        Set<String> existingNames = new HashSet<>();
	        String sqlSelect = "SELECT nom FROM Tmoyenne";
	        PreparedStatement statementSelect = connection.prepareStatement(sqlSelect);
	        ResultSet resultSet = statementSelect.executeQuery();
	        while (resultSet.next()) {
	            existingNames.add(resultSet.getString("nom"));
	        }
	        statementSelect.close();

	        String sqlInsert = "INSERT INTO Tmoyenne (nom, prenom, moyenne, resultat) VALUES (?, ?, ?, ?)";
	        PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);

	        for (int i = 0; i < names.size(); i++) {
	            String nameLine = names.get(i).trim();
	            if (nameLine.isEmpty()) {
	                continue;
	            }
	            String[] nameParts = nameLine.split(" ");
	            String nom, prenom;
	            if (nameParts.length == 2) {
	                nom = nameParts[0];
	                prenom = nameParts[1];
	            } else {
	                nom = nameLine;
	                prenom = "";
	                System.out.println("Attention: Format de nom non conforme - " + nameLine);
	            }

	            if (!existingNames.contains(nom)) {
	                String moyenne = moyennes.get(i).split(" ")[0];
	                String resultat = moyennes.get(i).split(" ")[1];
	                statementInsert.setString(1, nom);
	                statementInsert.setString(2, prenom);
	                statementInsert.setDouble(3, Double.parseDouble(moyenne));
	                statementInsert.setString(4, resultat);
	                statementInsert.executeUpdate();
	            }
	        }
	        statementInsert.close();
	    }

	    private static void displayTmoyenne(Connection connection) throws SQLException {
	        StringBuilder tmoyenneContent = new StringBuilder();
	        String sql = "SELECT * FROM Tmoyenne";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet result = statement.executeQuery();
	        while (result.next()) {
	            String nom = result.getString("nom");
	            String prenom = result.getString("prenom");
	            double moyenne = result.getDouble("moyenne");
	            String resultat = result.getString("resultat");
	            tmoyenneContent.append(nom).append(" ").append(prenom).append(" ").append(moyenne).append(" ").append(resultat).append("\n");
	        }
	        statement.close();
	        JOptionPane.showMessageDialog(null, "Contenu de la table Tmoyenne :\n" + tmoyenneContent.toString());
	    }
}
