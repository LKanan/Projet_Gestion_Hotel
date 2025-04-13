package persistance;

import data.Chambre;
import data.Reservation;
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
    public void saveChambre(Chambre chambre) {
    }

    @Override
    public List<Chambre> loadChambre() {
        return new ArrayList<>();
    }

    @Override
    public void saveReservation(Reservation reservation) {

    }

    @Override
    public List<Reservation> loadReservation() {
        return new ArrayList<>();
    }
}
