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
            System.out.println("1. Afficher tous les comptes Clients");
            System.out.println("2. Afficher Un Seul Clients");
            System.out.println("3. ");
            System.out.println("3. Déconnexion");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\n--- Liste des Utilisateurs ---");
                    for (Utilisateur utilisateur : utilisateurList) {
                        System.out.println("ID: " + utilisateur.getId() +
                                ", Nom: " + utilisateur.getNom() +
                                ", Email: " + utilisateur.getEmail() +
                                ", Adresse: " + utilisateur.getAdresse());
                    }
                    break;

                case 2:
                    System.out.println("\n--- Liste des Administrateurs ---");
                    for (Admin admin : adminList) {
                        System.out.println("ID: " + admin.getId() +
                                ", Nom: " + admin.getNom() +
                                ", Email: " + admin.getEmail() +
                                ", Matricule: " + admin.getMatricule());
                    }
                    break;

                case 3:
                    System.out.println("Déconnexion réussie. Retour au menu principal.");
                    return; // Quitter le menu admin

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

}