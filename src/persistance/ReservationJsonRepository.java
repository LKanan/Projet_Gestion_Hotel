package persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import data.Reservation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationJsonRepository extends MainRepositoryImplement {
    private final String fileName;
    private final Gson gson;

    public ReservationJsonRepository(String fileName) {
        this.fileName = fileName;
        this.gson = new GsonBuilder()
                .setPrettyPrinting().registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter()) // Register the custom adapter
                .create();
    }

    @Override
    public void saveReservation(Reservation reservation) {
        Path path = Paths.get(fileName);
        List<Reservation> nouvellesReservations = new ArrayList<>();
        List<Reservation> reservationsExistantes = loadReservation(); // Charger les réservations existantes

        // Ajouter la nouvelle réservation à la liste existante
        nouvellesReservations.add(reservation);
        reservationsExistantes.addAll(nouvellesReservations);
        try (Writer writer = Files.newBufferedWriter(path)) {
            gson.toJson(reservationsExistantes, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement dans " + fileName, e);
        }
    }

    @Override
    public List<Reservation> loadReservation() {
        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try (Reader reader = Files.newBufferedReader(path)) {
            Reservation[] reservationArray = gson.fromJson(reader, Reservation[].class);

            if (reservationArray == null) {
                return new ArrayList<>();
            }

            return new ArrayList<>(Arrays.asList(reservationArray));
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture de " + fileName, e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Format JSON invalide dans " + fileName, e);
        }
    }
}