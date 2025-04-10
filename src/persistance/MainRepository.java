package persistance;

import data.*;
import data.Chambre;
import data.Reservations;
import data.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public interface MainRepository {
    void saveUser(List<Utilisateur> user);
    public List<Utilisateur> loadUser();

    void saveAdmin(List<Admin> admin);

    public ArrayList<Admin> loadAdmin();

    void saveChambre(List<Chambre> chambre);

    public List<Chambre> loadChambre();

    void saveReservation(List<Reservations> reservation);

    public List<Reservations> loadReservation();
}
