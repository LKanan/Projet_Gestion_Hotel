import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Reservations {
    private int id;
    private int idClient;
    private int idChambre;
    private LocalDate date;
    private static List<Reservations> reservations = new ArrayList<>();
    private static Scanner clav = new Scanner(System.in);

    private Reservations(int id, int idClient, int idChambre, LocalDate date) {
        this.id = id;
        this.idClient = idClient;
        this.idChambre = idChambre;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public LocalDate getDate() {
        return date;
    }

    static void ajouterReservation(int idClient) {
        System.out.println("Faire une réservation\n--------------------------------------------\n");
        System.out.println("Saisissez le numero de la chambre: ");
        int idChambre = clav.nextInt();
        LocalDate date = LocalDate.now();
        if (reservations.isEmpty()) {
            int id = 1;
            Reservations reservation = new Reservations(id, idClient, idChambre, date);
            reservations.add(reservation);
        }
        else{
            int id = reservations.get(reservations.size() - 1).getId() + 1;
            Reservations reservation = new Reservations(id, idClient, idChambre, date);
            reservations.add(reservation);
        }
        System.out.println("Réservation ajoutée avec succès !");
    }

    static void afficherReservations() {
        System.out.println("Liste des réservations\n--------------------------------------------\n");
        System.out.println("Id\tIdClient\tIdChambre\tDate");
        System.out.println("--\t--------\t---------\t----");
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation trouvée.");
        } else {
            for (Reservations reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    static void afficherUneReservation(int id) {
        System.out.println("Détails de la réservation\n--------------------------------------------\n");
        for (Reservations reservation : reservations) {
            if (reservation.getId() == id) {
                System.out.println(reservation);
                return;
            }
        }
        System.out.println("Aucune réservation trouvée avec cet ID.");
    }
//done
    static void supprimerReservation(int id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId() == id) {
                reservations.remove(i);
                System.out.println("Réservation supprimée avec succès !");
                return;
            }
        }
        System.out.println("Aucune réservation trouvée avec cet ID.");
    }

    @Override
    public String toString() {
        return "" + id + "\t|" + idClient + "\t\t\t|" + idChambre + "\t\t\t|" + date;
    }
}
