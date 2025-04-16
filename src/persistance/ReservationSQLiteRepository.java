package persistance;

import data.Reservations;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationSQLiteRepository {

    private final String databaseUrl;

    public ReservationSQLiteRepository(String databaseUrl) {
        this.databaseUrl = databaseUrl;

        // Initialisation de la base de données
        SQLiteDatabaseInitializer.initializeDatabase(databaseUrl);
    }

    // Méthode pour sauvegarder une réservation
    public void saveReservation(List<Reservations> reservationsToSave) {
        String insertReservationQuery = """
            INSERT INTO Reservations (idClient, idChambre, date)
            VALUES (?, ?, ?)
        """;

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             PreparedStatement insertStmt = connection.prepareStatement(insertReservationQuery)) {

            for (Reservations reservation : reservationsToSave) {
                try {
                    insertStmt.setInt(1, reservation.getIdClient());
                    insertStmt.setInt(2, reservation.getIdChambre());
                    insertStmt.setString(3, reservation.getDate().toString());
                    insertStmt.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de l'insertion de la réservation pour le client ID : "
                            + reservation.getIdClient() + ". Message : " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement des réservations dans la base de données", e);
        }
    }

    // Méthode pour charger toutes les réservations
    public List<Reservations> loadReservation() {
        List<Reservations> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Reservations reservation = new Reservations(
                        resultSet.getInt("id"),
                        resultSet.getInt("idClient"),
                        resultSet.getInt("idChambre"),
                        LocalDate.parse(resultSet.getString("date"))
                );
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors du chargement des réservations depuis la base de données", e);
        }

        return reservations;
    }
}