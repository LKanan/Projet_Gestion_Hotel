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

public class AdminJsonRepository implements MainRepository{
    private final String fileName;
    private final Gson gson;

    public AdminJsonRepository(String fileName) {
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
    public void saveUser(List<Utilisateur> user) {

    }

    @Override
    public List<Utilisateur> loadUser() {
        return List.of();
    }

    @Override
    public void saveChambre(Chambre chambre) {

    }

    @Override
    public void saveAdmin(List<Admin> adminsToSave) {
        Path path = Paths.get(fileName);
        List<Admin> existingAdmins = loadAdmin(); // Charger les données existantes.

        // Trouver l'ID maximum existant
        int maxId = existingAdmins.stream()
                .mapToInt(Admin::getId)
                .max()
                .orElse(0);

        // Ajouter uniquement les admins non-présents
        for (Admin admin : adminsToSave) {
            boolean exists = existingAdmins.stream()
                    .anyMatch(existingAdmin -> existingAdmin.getEmail().equals(admin.getEmail()));

            if (!exists) {
                admin.setId(++maxId); // Incrémenter l'ID
                existingAdmins.add(admin);
            }
        }

        // Écriture dans le fichier JSON
        try (Writer writer = Files.newBufferedWriter(path)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(existingAdmins, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement dans " + fileName, e);
        }
    }

    @Override
    public ArrayList<Admin> loadAdmin() {
        Path path = Paths.get(fileName);

        // Si le fichier n'existe pas, retourner une liste vide
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(path)) {
            // Utilisation de TypeToken pour la désérialisation de List<Animal>
            Admin[] adminArray = gson.fromJson(reader, Admin[].class);

            if (adminArray == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(Arrays.asList(adminArray));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture de " + fileName, e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Format JSON invalide dans " + fileName, e);
        }
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
