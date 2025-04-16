package persistance;

import data.Chambre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreSQLiteRepository {

    private final String databaseUrl;

    public ChambreSQLiteRepository(String databaseUrl) {
        this.databaseUrl = databaseUrl;

        // Initialisation de la base de données
        SQLiteDatabaseInitializer.initializeDatabase(databaseUrl);
    }

    public void saveChambre(Chambre chambre) {
        String insertChambreQuery = """
            INSERT INTO Chambre (nom,prix,description, type)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             PreparedStatement insertStmt = connection.prepareStatement(insertChambreQuery)) {

            insertStmt.setString(1, chambre.getNom());
            insertStmt.setInt(2, chambre.getPrix());
            insertStmt.setString(3, chambre.getDescription());
            insertStmt.setString(2, chambre.getType());
            insertStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement de la chambre dans la base de données", e);
        }
    }

    public List<Chambre> loadChambre() {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT * FROM Chambre";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Chambre chambre = new Chambre(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getInt("prix"),
                        resultSet.getString("description"),
                        resultSet.getString("type")
                );
                chambres.add(chambre);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement des chambres depuis la base de données", e);
        }

        return chambres;
    }
}