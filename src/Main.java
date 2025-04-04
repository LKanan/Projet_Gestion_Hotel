import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Admin> adminList = new ArrayList<>();
        List<Utilisateur> utilisateurList = new ArrayList<>();

        adminList.add(new Admin(1, "rooney", "admin@gmail.com", "P@55word", "ADMIN", "ADM001"));
        utilisateurList.add(new Utilisateur(1, "user", "user@gmail.com", "P@55word", "USER", "paris"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Système d'Authentification ---");
            System.out.println("1. Se connecter");
            System.out.println("2. Créer un compte");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la ligne restante

            switch (choix) {
                case 1:
                    System.out.print("Entrez votre email : ");
                    String email = scanner.nextLine();
                    System.out.print("Entrez votre mot de passe : ");
                    String password = scanner.nextLine();

                    Personne personne = new Personne() {
                    }.seConnecter(adminList, utilisateurList, email, password);

                    if (personne != null) {
                        if (personne instanceof Admin) {
                            ((Admin) personne).afficherMenuAdmin(adminList, utilisateurList);
                        } else if (personne instanceof Utilisateur) {
                            ((Utilisateur) personne).afficherMenuUtilisateur();
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Créer un compte ---");
                    System.out.print("1. Administrateur\n2. Utilisateur\nVotre choix : ");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Email : ");
                    String nouveauEmail = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String nouveauPassword = scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Matricule : ");
                        String matricule = scanner.nextLine();
                        adminList.add(new Admin(adminList.size() + 1, nom, nouveauEmail, nouveauPassword, "ADMIN", matricule));
                        System.out.println("Compte administrateur créé !");
                    } else {
                        System.out.print("Adresse : ");
                        String adresse = scanner.nextLine();
                        utilisateurList.add(new Utilisateur(utilisateurList.size() + 1, nom, nouveauEmail, nouveauPassword, "USER", adresse));
                        System.out.println("Compte utilisateur créé !");
                    }
                    break;

                case 3:
                    System.out.println("Merci d'avoir utilisé notre système.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        }
    }
}