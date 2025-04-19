package data;

import persistance.ChambreJsonRepository;
import persistance.ChambreSQLiteRepository;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chambre implements Serializable {
    private static long serialVersionUID = 1L;
    private int id;
    private String nom;
    private int prix;
    private String description;
    private String type;
    static Scanner clav = new Scanner(System.in);
    static private ChambreSQLiteRepository chambreSQLiteRepository = new ChambreSQLiteRepository("Hotel.db");


    public Chambre(int id, String nom, int prix, String description, String type) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.type = type;
    }
    public Chambre(String nom, int prix, String description, String type) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    //    public static void addChamber(String nom, int prix, String description, String type) {
    public static void ajouterChamber() {
        String nomChambre;
        int prixChambre;
        String descriptionChambre;
        String typeChambre;
        System.out.println("Ajout d'une chambre\n--------------------------------------------\n");
        System.out.println("Nom de la chambre: ");
        nomChambre = clav.nextLine();
        System.out.println("Prix de la chambre: ");
        prixChambre = clav.nextInt();
        clav.nextLine();
        System.out.println("Type de la chambre: ");
        typeChambre = clav.nextLine();
        System.out.println("Description de la chambre: ");
        descriptionChambre = clav.nextLine();
        List<Chambre> chambresExistantes = chambreSQLiteRepository.loadChambre();
        if (chambresExistantes.isEmpty()) {
            int id = 1;
            Chambre chambre = new Chambre(nomChambre, prixChambre, descriptionChambre, typeChambre);
            chambreSQLiteRepository.saveChambre(chambre);
        } else {
            int id = chambresExistantes.get(chambresExistantes.size() - 1).getId() + 1;
            Chambre chambre = new Chambre(id, nomChambre, prixChambre, descriptionChambre, typeChambre);
            chambreSQLiteRepository.saveChambre(chambre);
        }
        System.out.println("Chambre ajoutée avec succès !\n--------------------------------------------\n");
    }

    public static void supprimerChamber() {
        System.out.println("Suppression d'une chambre\n--------------------------------------------\n");
        System.out.println("Entrez le numéro de la chambre à supprimer: ");
        int id = clav.nextInt();
        clav.nextLine(); // Consommer le caractère de nouvelle ligne
        ChambreSQLiteRepository chambreSQLiteRepository = new ChambreSQLiteRepository("Hotel.db");
        chambreSQLiteRepository.deleteChambre(id);
            System.out.println("Chambre supprimée avec succès !");
    }

    public static void afficherToutesChambres() {
        System.out.println("Liste des chambres\n--------------------------------------------\n");
        List<Chambre> chambresExistantes = chambreSQLiteRepository.loadChambre();
        if (chambresExistantes.size() == 0) {
            System.out.println("Aucune chambre disponible.");
        } else {
            for (Chambre chambre : chambresExistantes) {
                System.out.println(chambre.nom + " : " + chambre.prix + "$");
            }
        }
    }

    public static void afficherUneChambre(int id) {
        List<Chambre> chambresExistantes = chambreSQLiteRepository.loadChambre();
        for (Chambre chambre : chambresExistantes) {
            if (chambre.getId() == id) {
                System.out.println(chambre);
            }
        }
    }

    @Override
    public String toString() {
        return "Chambre numero: " + id + "." +
                "\n\tNom: " + nom +
                "\n\tPrix: " + prix + "$" +
                "\n\tDescription: " + description +
                "\n\tType: " + type +
                "\n===============================================\n";
    }

    public int setId(int id){
        this.id = id;
        return id;
    }
}
