package data;

import persistance.ChambreJsonRepository;
import persistance.ChambreSQLiteRepository;
import persistance.ReservationJsonRepository;
import persistance.ReservationSQLiteRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Reservations implements Serializable {
    private static long serialVersionUID = 1L;
    private int id;
    private int idClient;
    private int idChambre;
    private LocalDate date;
    private static List<Reservations> reservations = new ArrayList<>();
    private static Scanner clav = new Scanner(System.in);
    static private ReservationSQLiteRepository reservationSQLiteRepository = new ReservationSQLiteRepository("Hotel.db");
//    private static ReservationJsonRepository jsonRepository = new ReservationJsonRepository("Reservations.json");

    public Reservations(int id, int idClient, int idChambre, LocalDate date) {
        this.id = id;
        this.idClient = idClient;
        this.idChambre = idChambre;
        this.date = date;
    }

    public Reservations(int idClient, int idChambre, LocalDate date) {
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

        List<Reservations> reservationsExistantes = reservationSQLiteRepository.loadReservation();

        Reservations reservation = new Reservations(idClient, idChambre, date);
        reservationSQLiteRepository.saveReservation(reservation);
//        jsonRepository.saveReservation(reservation);
        System.out.println("Réservation ajoutée avec succès !");
    }

    static void afficherReservations() {
        System.out.println("Liste des réservations\n--------------------------------------------\n");
        System.out.println("N°\tIdClient\tIdChambre\tDate");
        System.out.println("--\t--------\t---------\t----");
        List<Reservations> reservationsExistantes = reservationSQLiteRepository.loadReservation();
        if (reservationsExistantes.isEmpty()) {
            System.out.println("Aucune réservation trouvée.");
        } else {
            for (Reservations reservation : reservationsExistantes) {
                System.out.println(reservation);
            }
        }
    }

    static void afficherUneReservation(int id) {
        System.out.println("Détails de la réservation\n--------------------------------------------\n");
        List<Reservations> reservationsExistantes = reservationSQLiteRepository.loadReservation();
        for (Reservations reservation : reservationsExistantes) {
            if (reservation.getId() == id) {
                System.out.println(reservation);
                return;
            }
        }
        System.out.println("Aucune réservation trouvée avec cet ID.");
    }

    //done
    static void supprimerReservation(int id) {

        boolean isDeleted = reservationSQLiteRepository.deleteReservation(id);
        if (isDeleted){
                System.out.println("Réservation supprimée avec succès !");
            }
        else System.out.println("Aucune réservation trouvée avec cet ID.");
    }

    @Override
    public String toString() {
        ChambreSQLiteRepository chambreSQLiteRepository = new ChambreSQLiteRepository("Hotel.db");
        List<Chambre> chambres = chambreSQLiteRepository.loadChambre();
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
