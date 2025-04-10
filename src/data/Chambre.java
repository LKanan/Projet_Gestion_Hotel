package data;

import persistance.ChambreJsonRepository;

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
    private static ChambreJsonRepository jsonRepository = new ChambreJsonRepository("Chambres.json");

    private static List<Chambre> chambres = new ArrayList<>();

    private Chambre(int id, String nom, int prix, String description, String type) {
        this.id = id;
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
        List<Chambre> chambresExistantes = jsonRepository.loadChambre();
        if (chambresExistantes.size() == 0) {
            int id = 1;
            Chambre chambre = new Chambre(id, nomChambre, prixChambre, descriptionChambre, typeChambre);
            jsonRepository.saveChambre(chambre);
        } else {
            int id = chambresExistantes.get(chambresExistantes.size() - 1).getId() + 1;
            Chambre chambre = new Chambre(id, nomChambre, prixChambre, descriptionChambre, typeChambre);
            jsonRepository.saveChambre(chambre);
        }
        System.out.println("Chambre ajoutée avec succès !\n--------------------------------------------\n");
    }

    public static void supprimerChamber() {
        System.out.println("Suppression d'une chambre\n--------------------------------------------\n");
        System.out.println("Entrez le numéro de la chambre à supprimer: ");
        int id = clav.nextInt();
        clav.nextLine(); // Consommer le caractère de nouvelle ligne

        // Charger les chambres existantes depuis le fichier JSON
        List<Chambre> chambresExistantes = jsonRepository.loadChambre();

        // Rechercher et supprimer la chambre correspondante
        boolean chambreSupprimee = chambresExistantes.removeIf(chambre -> chambre.getId() == id);

        if (chambreSupprimee) {
            // Sauvegarder la liste mise à jour dans le fichier JSON
            try (Writer writer = Files.newBufferedWriter(Paths.get("Chambres.json"))) {
                jsonRepository.gson.toJson(chambresExistantes, writer);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la mise à jour du fichier JSON", e);
            }
            System.out.println("Chambre supprimée avec succès !");
        } else {
            System.out.println("Aucune chambre trouvée avec cet ID.");
        }
    }

    public static void afficherToutesChambres() {
        System.out.println("Liste des chambres\n--------------------------------------------\n");
        List<Chambre> chambresExistantes = jsonRepository.loadChambre();
        if (chambresExistantes.size() == 0) {
            System.out.println("Aucune chambre disponible.");
        } else {
            for (Chambre chambre : chambresExistantes) {
                System.out.println(chambre.nom + " : " + chambre.prix + "$");
            }
        }
    }

    public static void afficherUneChambre(int id) {
        List<Chambre> chambresExistantes = jsonRepository.loadChambre();
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
}
