package ui;

import data.Admin;
import data.Personne;
import data.Utilisateur;
import persistance.AdminSQLiteRepository;
import persistance.UserSQLiteRepository;

import java.util.Scanner;

public class MenuPrincipal {
    public static void affichageMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        /*
         * Create a repository for connecting to a database
         */
        UserSQLiteRepository userSQLiteRepository = new UserSQLiteRepository("Hotel.db");
        AdminSQLiteRepository adminSQLiteRepository = new AdminSQLiteRepository("Hotel.db");


        while (true) {

            System.out.println("\n\t -------------------------------------------------------------------------------\n\t\t\t\t\t\t\t\t  Bienvenue  Sur HotelManagement   \n\t ------------------------------------------------------------------------------- \t ");
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t 1. Se connecter    \t");
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t 2. Quitter          \n\t ");
            System.out.println("\n\t ----------------------------------- |   |  ------------------------------------ \t ");
            System.out.print("\nChoisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            /*
                Authentication current user
             */
            switch (choix) {
                case 1:
                    System.out.println("\n\t -------------------------------------------------------------------------------\n\t\t\t\t\t\t\t\t  Authentification   \n\t ------------------------------------------------------------------------------- \t ");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t 1. Entrez votre email :    \t");
                    String email = scanner.nextLine();
                    System.out.println("\n\t\t\t\t\t\t\t\t\t 2. Entrez votre mot de passe :           \t ");
                    String password = scanner.nextLine();
                    System.out.println("\n\t ----------------------------------- |   |  ------------------------------------ \t ");

                    Personne personne = new Personne() {}.seConnecter(Admin.adminList = adminSQLiteRepository.loadAdmin(), Utilisateur.utilisateurList =userSQLiteRepository.loadUser(), email, password);

                    if (personne != null) {
                        if (personne instanceof Admin) {
                            ((Admin) personne).afficherMenuAdmin(Admin.adminList, Utilisateur.utilisateurList);
                        } else if (personne instanceof Utilisateur) {
                            ((Utilisateur) personne).afficherMenuUtilisateur();
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n\t -------------------------------------------------------------------------------\n\t\t\t\t\t\t\t\t  Bienvenue  Sur HotelManagement   \n\t ------------------------------------------------------------------------------- \t ");
                    System.out.println("\n\t\t\t\t\t\t\t\t\t\t  Merci d'avoir utilisé notre système.           \n\t ");
                    System.out.println("\n\t ----------------------------------- |   |  ------------------------------------ \t ");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        }
    }
}
