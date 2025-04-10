import data.Admin;
import data.Personne;
import data.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int idConected;

    public static void main(String[] args) {

        affichageMenuPrincipal();

    }

    public static void affichageMenuPrincipal() {
        List<Admin> adminList = new ArrayList<>();



        adminList.add(new Admin(1, "rooney", "admin@gmail.com", "P@55word", "ADMIN", "ADM001"));
        Utilisateur.utilisateurList.add(new Utilisateur(1, "user", "user@gmail.com", "P@55word", "USER", "paris"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t Système d'Authentification \n\t ------------------------- \t ");
            System.out.println("\n\t 1. Se connecter    \t");
            System.out.println("\n\t 2. Créer un compte ");
            System.out.println("\n\t 3. Quitter          \n\t -------------------------\t");
            System.out.print("\nChoisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Entrez votre email : ");
                    String email = scanner.nextLine();
                    System.out.print("Entrez votre mot de passe : ");
                    String password = scanner.nextLine();

                    Personne personne = new Personne() {
                    }.seConnecter(adminList,Utilisateur.utilisateurList, email, password);

                    if (personne != null) {
                        if (personne instanceof Admin) {
                            ((Admin) personne).afficherMenuAdmin(adminList, Utilisateur.utilisateurList);
                        } else if (personne instanceof Utilisateur) {
                            ((Utilisateur) personne).afficherMenuUtilisateur();
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n\t Créer un compte \n\t ---------------------- \n\t");
                    System.out.print("\n\t1. Administrateur\n\t2. Utilisateur\n\tVotre choix : ");
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
                        Utilisateur.utilisateurList.add(new Utilisateur(Utilisateur.utilisateurList.size() + 1, nom, nouveauEmail, nouveauPassword, "USER", adresse));
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


