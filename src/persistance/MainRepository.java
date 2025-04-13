package persistance;

import data.*;
import data.Chambre;
import data.Reservation;
import data.Utilisateur;

import java.util.List;

public interface MainRepository {
    void saveUser(List<Utilisateur> user);

    public List<Utilisateur> loadUser();

    void saveChambre(Chambre chambre);

    public List<Chambre> loadChambre();

    void saveReservation(List<Reservations> reservation);

    public List<Reservations> loadReservation();
}
