package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import data.Chambre;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChambreJsonRepository extends MainRepositoryImplement {
    private final String fileName;
    private final Gson gson;

    public ChambreJsonRepository(String fileName) {
        this.fileName = fileName;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void saveChambre(List<Chambre> chambresToSave) {
        Path path = Paths.get(fileName);
        List<Chambre> existingChambres = loadChambre();

        // Trouver l'ID maximal :
        int maxId = existingChambres.stream()
                .mapToInt(Chambre::getId)
                .max()
                .orElse(0);

        // Ajouter uniquement les nouvelles chambres (sans doublon)
        for (Chambre chambre : chambresToSave) {
            boolean exists = existingChambres.stream()
                    .anyMatch(existingChambre -> existingChambre.getNom().equals(chambre.getNom()));

            if (!exists) {
                chambre.setId(++maxId); // Incrémenter l'ID.
                existingChambres.add(chambre);
            }
        }

        // Sauvegarder dans le fichier JSON
        try (Writer writer = Files.newBufferedWriter(path)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(existingChambres, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement des chambres dans " + fileName, e);
        }
    }

    @Override
    public List<Chambre> loadChambre() {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try (Reader reader = Files.newBufferedReader(path)) {
            Chambre[] chambreArray = gson.fromJson(reader, Chambre[].class);

            if (chambreArray == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(Arrays.asList(chambreArray));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture de " + fileName, e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Format JSON invalide dans " + fileName, e);
        }
    }
}
