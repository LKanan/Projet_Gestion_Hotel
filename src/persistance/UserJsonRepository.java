package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import data.Admin;
import data.Chambre;
import data.Reservations;
import data.Utilisateur;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserJsonRepository implements MainRepository{

    private final String fileName;

    private Gson gson;

    public UserJsonRepository(String fileName) {
        this.fileName = fileName;
        RuntimeTypeAdapterFactory<Utilisateur> typeFactory = RuntimeTypeAdapterFactory
                .of(Utilisateur.class, "type")
                .registerSubtype(Utilisateur.class, "user");

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(typeFactory)
                .create();
    }
    @Override
    public void saveUser(List<Utilisateur> usersToSave) {
        // Étape 1 : Charger les utilisateurs existants
        List<Utilisateur> existingUsers = loadUser();

        // Étape 2 : Trouver l'ID maximum existant
        int maxId = existingUsers.stream()
                .mapToInt(Utilisateur::getId)
                .max()
                .orElse(0);

        // Étape 3 : Ajouter les nouveaux utilisateurs uniquement s'ils ne sont pas déjà présents
        for (Utilisateur user : usersToSave) {
            boolean exists = existingUsers.stream()
                    .anyMatch(exUser -> exUser.getEmail().equals(user.getEmail()));
            if (!exists) {
                // Incrémenter l'ID pour chaque nouveau utilisateur
                user.setId(++maxId);
                existingUsers.add(user);
            }
        }

        // Étape 4 : Écrire dans le fichier JSON
        try (Writer writer = Files.newBufferedWriter(Paths.get(fileName))) {
            new GsonBuilder().setPrettyPrinting().create().toJson(existingUsers, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement des utilisateurs dans " + fileName, e);
        }
    }
    @Override
    public List<Utilisateur> loadUser() {
        Path path = Paths.get(fileName);

        // Si le fichier n'existe pas, retourner une liste vide
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            Utilisateur[] usersArray = new Gson().fromJson(reader, Utilisateur[].class);

            return (usersArray == null) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(usersArray));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement des utilisateurs à partir de " + fileName, e);
        }
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
        return List.of();
    }

    @Override
    public void saveReservation(List<Reservations> reservation) {

    }

    @Override
    public List<Reservations> loadReservation() {
        return List.of();
    }
}
