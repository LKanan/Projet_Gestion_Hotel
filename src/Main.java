import data.Admin;
import data.Personne;
import data.Utilisateur;
import persistance.AdminJsonRepository;
import persistance.AdminSQLiteRepository;
import persistance.UserJsonRepository;
import persistance.UserSQLiteRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ui.MenuPrincipal.affichageMenuPrincipal;

public class Main {
    public static int idConected;

    public static void main(String[] args) {

        affichageMenuPrincipal();

    }
}


