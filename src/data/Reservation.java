package data;

import persistance.ChambreJsonRepository;
import persistance.ReservationJsonRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private static long serialVersionUID = 1L;
    private int id;
    private int idClient;
    private int idChambre;
    private LocalDate date;
    private static List<Reservation> reservations = new ArrayList<>();
    private static Scanner clav = new Scanner(System.in);
    private static ReservationJsonRepository jsonRepository = new ReservationJsonRepository("Reservations.json");

    private Reservation(int id, int idClient, int idChambre, LocalDate date) {
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
        clav.nextLine(); // Consommer le caractère de nouvelle ligne
        LocalDate date = LocalDate.now();

        List<Reservation> reservationsExistantes = jsonRepository.loadReservation();
        int id = reservationsExistantes.isEmpty() ? 1 : reservationsExistantes.get(reservationsExistantes.size() - 1).getId() + 1;

        Reservation reservation = new Reservation(id, idClient, idChambre, date);
        jsonRepository.saveReservation(reservation);
        System.out.println("Réservation ajoutée avec succès !");
    }

    static void afficherReservations() {
        System.out.println("Liste des réservations\n--------------------------------------------\n");
        System.out.println("N°\tIdClient\tIdChambre\tDate");
        System.out.println("--\t--------\t---------\t----");
        List<Reservation> reservationsExistantes = jsonRepository.loadReservation();
        if (reservationsExistantes.isEmpty()) {
            System.out.println("Aucune réservation trouvée.");
        } else {
            for (Reservation reservation : reservationsExistantes) {
                System.out.println(reservation);
            }
        }
    }

    static void afficherUneReservation(int id) {
        System.out.println("Détails de la réservation\n--------------------------------------------\n");
        List<Reservation> reservationsExistantes = jsonRepository.loadReservation();
        for (Reservation reservation : reservationsExistantes) {
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
        ChambreJsonRepository chambreRepo = new ChambreJsonRepository("Chambres.json");
        List<Chambre> chambres = chambreRepo.loadChambre();
        Chambre chambreConcernee = chambres.stream()
                .filter(chambre -> chambre.getId() == idChambre)
                .findFirst()
                .orElse(null);

        String detailsChambre = (chambreConcernee != null) ? chambreConcernee.toString() : "Chambre non trouvée";

        return "Numéro de réservation: " + id + "\n" +
                "Détails de la chambre: " + detailsChambre + "\n" +
                "Date de réservation: " + date;
    }
}
