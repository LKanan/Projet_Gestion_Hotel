package persistance;

import data.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminSQLiteRepository implements MainRepository {

    private final String databaseUrl;

    public AdminSQLiteRepository(String databaseUrl) {
        this.databaseUrl = databaseUrl;

        // Initialisation de la base de données
        SQLiteDatabaseInitializer.initializeDatabase(databaseUrl);
    }

    @Override
    public void saveAdmin(List<Admin> adminsToSave) {
        String insertAdminQuery = """
            INSERT INTO Admin (nom, email, password, role, matricule)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             PreparedStatement insertStmt = connection.prepareStatement(insertAdminQuery)) {

            for (Admin admin : adminsToSave) {
                try {
                    insertStmt.setString(1, admin.getNom());
                    insertStmt.setString(2, admin.getEmail());
                    insertStmt.setString(3, admin.getPassword());
                    insertStmt.setString(4, admin.getRole());
                    insertStmt.setString(5, admin.getMatricule());
                    insertStmt.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de l'insertion de l'administrateur : " + admin.getNom() +
                            ". Message : " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement des administrateurs dans la base de données", e);
        }
    }

    @Override
    public ArrayList<Admin> loadAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("matricule")
                );
                admins.add(admin);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement des administrateurs depuis la base de données", e);
        }

        return admins;
    }

    // Autres méthodes de MainRepository
    @Override
    public void saveUser(List<data.Utilisateur> user) {}
    @Override
    public List<data.Utilisateur> loadUser() { return List.of(); }
    @Override
    public void saveChambre(data.Chambre chambre) {}
    @Override
    public List<data.Chambre> loadChambre() { return List.of(); }
    @Override
    public void saveReservation(List<data.Reservations> reservation) {}
    @Override
    public List<data.Reservations> loadReservation() { return List.of(); }
}