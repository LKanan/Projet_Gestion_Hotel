package persistance;

import data.Admin;
import data.Chambre;
import data.Reservations;
import data.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class MainRepositoryImplement implements MainRepository{
    @Override
    public void saveUser(List<Utilisateur> user) {

    }

    @Override
    public List<Utilisateur> loadUser() {
        return new ArrayList<>();
    }

    @Override
    public void saveAdmin(List<Admin> admin) {

    }

    @Override
    public ArrayList<Admin> loadAdmin() {
        return null;
    }

    @Override
    public void saveChambre(List<Chambre> chambre) {

    }

    @Override
    public List<Chambre> loadChambre() {
        return new ArrayList<>();
    }

    @Override
    public void saveReservation(List<Reservations> reservation) {

    }

    @Override
    public List<Reservations> loadReservation() {
        return new ArrayList<>();
    }
}
