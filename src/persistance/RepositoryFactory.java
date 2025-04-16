package persistance;

public class RepositoryFactory {
    public static MainRepository createUserRepository(String type) {
        switch (type.toLowerCase()) {
            case "sqlite":
                return new UserSQLiteRepository("jdbc:sqlite:your-database.db"); // Spécifier l'URL de la base de données
            case "json":
                return new UserJsonRepository("users.json"); // Support existant pour JSON
            default:
                throw new IllegalArgumentException("Type de repository inconnu : " + type);
        }
    }
    public static MainRepository createAdminRepository(String type) {
        if ("sqlite".equalsIgnoreCase(type)) {
            return new AdminSQLiteRepository("jdbc:sqlite:your-database.db");
        }
        throw new IllegalArgumentException("Type de repository inconnu : " + type);
    }

    public static ChambreSQLiteRepository createChambreRepository(String type) {
        if ("sqlite".equalsIgnoreCase(type)) {
            return new ChambreSQLiteRepository("jdbc:sqlite:your-database.db");
        }
        throw new IllegalArgumentException("Type de repository inconnu : " + type);
    }
    public static ReservationSQLiteRepository createReservationRepository(String type) {
        if ("sqlite".equalsIgnoreCase(type)) {
            return new ReservationSQLiteRepository("jdbc:sqlite:your-database.db");
        }
        throw new IllegalArgumentException("Type de repository inconnu : " + type);
    }
}