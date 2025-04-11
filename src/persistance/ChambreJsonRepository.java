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
    public final Gson gson;

    public ChambreJsonRepository(String fileName) {
        this.fileName = fileName;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void saveChambre(Chambre chambre) {
        Path path = Paths.get(fileName);
        List<Chambre> nouvellecambre = new ArrayList<>();
        List<Chambre> chambresExistantes = loadChambre(); // Charger les chambres existantes

        // Ajouter les nouvelles chambres à la liste existante
        nouvellecambre.add(chambre);
        chambresExistantes.addAll(nouvellecambre);
        try (Writer writer = Files.newBufferedWriter(path)) {
            gson.toJson(chambresExistantes, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement dans " + fileName, e);
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
