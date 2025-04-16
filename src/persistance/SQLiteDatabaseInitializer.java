package persistance;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseInitializer {

    /**
     * Initialise la base de données SQLite.
     * - Vérifie si le fichier de base de données existe, sinon le crée.
     * - Crée les tables si elles n'existent pas.
     */
    public static void initializeDatabase(String databaseUrl) {
        try {
            // 1. Vérifie et force la création du fichier de base de données
            createDatabaseFileIfNotExists(databaseUrl);

            // 2. Crée les tables nécessaires
            createTables(databaseUrl);

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'initialisation de la base de données", e);
        }
    }

    private static void createDatabaseFileIfNotExists(String databaseUrl) {
        File databaseFile = new File(databaseUrl);

        // Si le fichier de la base de données n'existe pas, le crée via une connexion JDBC
        if (!databaseFile.exists()) {
            try {
                // Force la création du dossier parent
                File parentDir = databaseFile.getParentFile();
                if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                    throw new RuntimeException("Impossible de créer le dossier parent pour la base de données : " + parentDir.getPath());
                }

                // Tente de créer le fichier de base de données via JDBC
                try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl)) {
                    System.out.println("Base de données créée dans : " + databaseUrl);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Impossible de créer le fichier SQLite", e);
            }
        }
    }

    private static void createTables(String databaseUrl) {
        String createTableUtilisateur = """
        CREATE TABLE IF NOT EXISTS Utilisateur (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nom TEXT NOT NULL,
            email TEXT NOT NULL UNIQUE,
            password TEXT NOT NULL,
            role TEXT NOT NULL,
            adresse TEXT
        );
    """;

        String createTableAdmin = """
        CREATE TABLE IF NOT EXISTS Admin (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nom TEXT NOT NULL,
            email TEXT NOT NULL,
            password TEXT NOT NULL,
            role TEXT NOT NULL,
            matricule TEXT NOT NULL UNIQUE
        );
    """;

        String createTableChambre = """
        CREATE TABLE IF NOT EXISTS Chambre (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            numero INTEGER NOT NULL UNIQUE,
            type TEXT NOT NULL,
            etat TEXT NOT NULL
        );
    """;
        String createTableReservations = """
        CREATE TABLE IF NOT EXISTS Reservations (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            idClient INTEGER NOT NULL,
            idChambre INTEGER NOT NULL,
            date TEXT NOT NULL,
            FOREIGN KEY (idClient) REFERENCES Utilisateur(id),
            FOREIGN KEY (idChambre) REFERENCES Chambre(id)
        );
    """;


        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
             Statement statement = connection.createStatement()) {
            statement.execute(createTableUtilisateur);
            statement.execute(createTableAdmin);
            statement.execute(createTableChambre);
            statement.execute(createTableReservations);


            System.out.println("Tables nécessaires ont été vérifiées ou créées avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la création des tables dans la base de données", e);
        }
    }
}