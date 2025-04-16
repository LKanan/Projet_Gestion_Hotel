package persistance;

import data.Admin;
import data.Chambre;
import data.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSQLiteRepository implements MainRepository {

    private final String databaseUrl;

    public UserSQLiteRepository(String databaseUrl) {
        this.databaseUrl = databaseUrl;

        // Initialisation de la base de données (création du fichier et des tables, si nécessaire)
        SQLiteDatabaseInitializer.initializeDatabase(databaseUrl);
    }

    @Override
    public void saveUser(List<Utilisateur> usersToSave) {
        String insertUserQuery = """
            INSERT INTO Utilisateur (nom, email, password, role, adresse)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             PreparedStatement insertStmt = connection.prepareStatement(insertUserQuery)) {

            for (Utilisateur user : usersToSave) {
                try {
                    insertStmt.setString(1, user.getNom());
                    insertStmt.setString(2, user.getEmail());
                    insertStmt.setString(3, user.getPassword());
                    insertStmt.setString(4, user.getRole());
                    insertStmt.setString(5, user.getAdresse());
                    insertStmt.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de l'insertion de l'utilisateur : " + user.getEmail() +
                            ". Message : " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement des utilisateurs dans la base de données", e);
        }
    }

    @Override
    public List<Utilisateur> loadUser() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String query = "SELECT * FROM Utilisateur";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("adresse")
                );
                utilisateurs.add(utilisateur);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement des utilisateurs depuis la base de données", e);
        }

        return utilisateurs;
    }

    @Override
    public void saveChambre(Chambre chambre) {
        // Implémentation facultative
    }

    @Override
    public void saveAdmin(List<Admin> adminsToSave) {
        // Implémentation facultative
    }

    @Override
    public ArrayList<Admin> loadAdmin() {
        return null;
    }

    @Override
    public List<Chambre> loadChambre() {
        return List.of();
    }

    @Override
    public void saveReservation(List<data.Reservations> reservation) {
        // Implémentation facultative
    }

    @Override
    public List<data.Reservations> loadReservation() {
        return List.of();
    }
}