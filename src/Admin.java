import java.util.List;
import java.util.Scanner;

public class Admin extends Personne {
    private String matricule;

    public Admin(int id, String nom, String email, String password, String role, String matricule) {
        super(id, nom, email, password, role);
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    // Menu pour l'administrateur
    public void afficherMenuAdmin(List<Admin> adminList, List<Utilisateur> utilisateurList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Menu Administrateur ---");
            System.out.println("1. Afficher tous les  Clients");
            System.out.println("2. Afficher Un Seul Clients");
            System.out.println("3. Afficher les reservations");
            System.out.println("4. Afficher une seule reservations");
            System.out.println("5. Afficher les chambres");
            System.out.println("6. Afficher une seule chambre par l'id");
            System.out.println("7. Creer une chambre");
            System.out.println("8. Supprimer une chambre par l'id");
            System.out.println("9. Déconnexion");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\n--- Tous les clients ---");
                    for (Utilisateur utilisateur : utilisateurList) {
                        System.out.println("ID: " + utilisateur.getId() +
                                ", Nom: " + utilisateur.getNom() +
                                ", Email: " + utilisateur.getEmail() +
                                ", Adresse: " + utilisateur.getAdresse());
                    }
                    break;

                case 2:
                    System.out.println("\n--- Utilisateur ---");
                    for (Admin admin : adminList) {
                        System.out.println("ID: " + admin.getId() +
                                ", Nom: " + admin.getNom() +
                                ", Email: " + admin.getEmail() +
                                ", Matricule: " + admin.getMatricule());
                    }
                    break;
                case 3:
                    System.out.println("Afficher les reservations");

                    break;
                case 4:
                    System.out.println("Afficher une seule reservations");
                case 5:
                    System.out.println("Afficher les chambres");
                    for (Chambre chambre:)
                    break;
                case 6:
                    System.out.println("Afficher une seule chambre par l'id");
                case 7:
                    System.out.println("Creer une chambre");
                case 8:
                    System.out.println("Supprimer Une Chambre par l'id :");
                case 9:
                    System.out.println("Déconnexion réussie. Retour au menu principal.");
                    return; // Quitter le menu admin

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

}