package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilisateur extends Personne {
    private String adresse;
    static List<Utilisateur>  utilisateurList = new ArrayList<>();

    public Utilisateur(int id, String nom, String email, String password, String role, String adresse) {
        super(id, nom, email, password, role);
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Menu pour l'utilisateur
    public void afficherMenuUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\t Menu Utilisateur \n\t ---------------------------\n\t");
            System.out.println("\n\t1. Voir mon profil\n\t");
            System.out.println("\n\t2. Faire une reservation\n\t");
            System.out.println("\n\t3. Voir Mes reservations\n\t");
            System.out.println("\n\t4. Afficher Une Reservation\n\t");
            System.out.println("\n\t5. Supprimer Une Reservation\n\t");
            System.out.println("\n\t6. Déconnexion\n\t");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\n--- Mon Profil ---");
                    System.out.println("ID: " + this.getId());
                    System.out.println("Nom: " + this.getNom());
                    System.out.println("Email: " + this.getEmail());
                    System.out.println("Adresse: " + this.getAdresse());
                    break;
                case 2:
                    System.out.println("\n\t Faire Une Reservation \n\t ---------------------- \n\t ");
                    Reservations.ajouterReservation(this.getId());
                    break;
                case 3:
                    System.out.println("\n\t Mes Reservations \n\t ---------------------- \n\t ");
                    Reservations.afficherReservations();
                    break;
                case 4:
                    System.out.println("\n\t Afficher Une Reservation \n\t ---------------------- \n\t ");
                    int idReservation = scanner.nextInt();
                    Reservations.afficherUneReservation(idReservation);
                    break;

                case 5:
                    System.out.println("\n\t Supprimer Une Reservation \n\t ---------------------- \n\t ");
                    int idReservationSupprimer = scanner.nextInt();
                    Reservations.supprimerReservation(idReservationSupprimer);
                    break;

                case 6:
                    System.out.println("Déconnexion réussie. Retour au menu principal.");
                    return; // Quitter le menu utilisateur

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    public static void afficherUnSeulClient(int id){
        for (Utilisateur utilisateur : utilisateurList) {
            if (utilisateur.getId() == id) {
                System.out.println(utilisateur);
            }
        }
    }


}