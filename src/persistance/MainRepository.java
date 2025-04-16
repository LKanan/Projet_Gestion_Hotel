package persistance;

import data.Admin;
import data.Chambre;
import data.Reservations;
import data.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public interface MainRepository {
    void saveUser(List<Utilisateur> user);

    public List<Utilisateur> loadUser();

    void saveChambre(Chambre chambre);

    void saveAdmin(List<Admin> adminsToSave);

    ArrayList<Admin> loadAdmin();

    public List<Chambre> loadChambre();

    void saveReservation(List<Reservations> reservation);

    public List<Reservations> loadReservation();
}
