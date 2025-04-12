package data;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Admin extends Personne {

    private static  long serialVersionUID = 1L;
    private String type = "admin";

    private String matricule;

    public Admin(int id, String nom, String email, String password, String role, String matricule) {
        super(id, nom, email, password, role);
        this.matricule = matricule;
        this.type = "admin";

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
            System.out.println("\n\t Menu Administrateur \n\t --------------------------- \n\t");
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
                    System.out.println("\n\tTous les clients \n\t ---------------------- \n\t ");
                    for (Utilisateur utilisateur : utilisateurList) {
                        System.out.println("ID: " + utilisateur.getId() +
                                ", Nom: " + utilisateur.getNom() +
                                ", Email: " + utilisateur.getEmail() +
                                ", Adresse: " + utilisateur.getAdresse());
                    }
                    break;

                case 2:
                    System.out.println("\n--- Afficher Un Seul Client ---");
                    int idUtilisateur = scanner.nextInt();
                    Utilisateur.afficherUnSeulClient(idUtilisateur);
                    break;
                case 3:
                    System.out.println("Afficher les reservations");

                    break;
                case 4:
                    System.out.println("Afficher une seule reservations");
                case 5:
                    System.out.println("Afficher les chambres");
                    Chambre.afficherToutesChambres();
                    break;
                case 6:
                    System.out.println("Afficher une seule chambre par l'id");
                    System.out.println("Entrez l'id de la chambre : ");
                    int idChambre = scanner.nextInt();
                    Chambre.afficherUneChambre(idChambre);
                    break;
                case 7:
                    System.out.println("Creer une chambre");
                    Chambre.ajouterChamber();
                    break;
                case 8:
                    System.out.println("Supprimer Une Chambre par l'id :");
                    int idChambreSupprimer = scanner.nextInt();
                    System.out.println("Entrer l'id de la chambre a supprimer : ");
                    Chambre.supprimerChamber();
                case 9:
                    System.out.println("Déconnexion réussie. Retour au menu principal.");
                    return; // Quitter le menu admin

                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    public String getType() { // Ajouter un getter pour le champ "type"
        return type;
    }

    public void setType(String type) { // Ajouter un setter pour le champ "type"
        this.type = type;
    }


}