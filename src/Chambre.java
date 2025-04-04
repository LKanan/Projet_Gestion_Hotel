import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chambre {
    private int id;
    private String nom;
    private int prix;
    private String description;
    private String type;
    private static Scanner clav = new Scanner(System.in);

    static List<Chambre> chambres = new ArrayList<>();

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
    public static void addChamber() {
        String nomChambre;
        int prixChambre;
        String descriptionChambre;
        String typeChambre;
        System.out.println("Ajout d'une chambre\n--------------------------------------------\n");
        System.out.println("Nom de la chambre: ");
        nomChambre = clav.nextLine();
        System.out.println("Prix de la chambre: ");
        prixChambre = clav.nextInt();
        System.out.println("Type de la chambre: ");
        typeChambre = clav.next();
        System.out.println("Description de la chambre: ");
        descriptionChambre = clav.next();

        if (chambres.size() == 0) {
            int id = 1;
            Chambre chambre = new Chambre(id, nomChambre, prixChambre, descriptionChambre, typeChambre);
            chambres.add(chambre);
        } else {
            int id = chambres.get(chambres.size() - 1).getId() + 1;
            Chambre chambre = new Chambre(id, nomChambre, prixChambre, descriptionChambre, typeChambre);
            chambres.add(chambre);
        }
        System.out.println("Chambre ajoutée avec succès !\n--------------------------------------------\n");
    }

    public static void removeChamber(int id) {
        for (int i = 0; i < chambres.size(); i++) {
            if (chambres.get(i).getId() == id) {
                chambres.remove(i);
                break;
            }
        }
        System.out.println("Chambre supprimée avec succès !");
    }

    @Override
    public String toString() {
        return "" + id + "." +
                "\n\tNom: " + nom +
                "\n\tPrix: " + prix +
                "\n\tDescription: " + description +
                "\n\tType: " + type+
                "\n===============================================\n";
    }
}
