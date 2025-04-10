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
        RuntimeTypeAdapterFactory<Admin> typeFactory = RuntimeTypeAdapterFactory
                .of(Admin.class, "type")
                .registerSubtype(Admin.class, "admin");

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapterFactory(typeFactory)
                .create();
    }
    @Override
    public void saveUser(List<Utilisateur> usersToSave) {
        Path path = Paths.get(fileName);
        List<Utilisateur> existingUsers = loadUser(); // Charger les utilisateurs existants

        // Trouver l'ID max existant pour assurer l'unicité des nouveaux IDs
        int maxId = existingUsers.stream()
                .mapToInt(Utilisateur::getId)
                .max()
                .orElse(0);

        // Filtrer les doublons en fonction de l'email ou d'autres critères
        for (Utilisateur userToSave : usersToSave) {
            boolean exists = existingUsers.stream()
                    .anyMatch(existingUser -> existingUser.getEmail().equals(userToSave.getEmail()));
            if (!exists) {
                // Incrémenter l'ID avant d'ajouter un nouvel utilisateur
                userToSave.setId(++maxId);
                existingUsers.add(userToSave);
            }
        }

        // Écrire la liste mise à jour dans le fichier JSON
        try (Writer writer = Files.newBufferedWriter(path)) {
            gson.toJson(existingUsers, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement dans " + fileName, e);
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
            // Utilisation de TypeToken pour la désérialisation de List<Animal>
            Utilisateur[] userArray = gson.fromJson(reader, Utilisateur[].class);

            if (userArray == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(Arrays.asList(userArray));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture de " + fileName, e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Format JSON invalide dans " + fileName, e);
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
