package data;

import java.io.Serializable;
import java.util.List;

public abstract class Personne implements Serializable {
    private static  long serialVersionUID = 1L;
    private int id;
    private String nom;
    private String email;
    private String password;
    private String role;
    boolean estConnecter = false;

    public Personne() {
    }

    public Personne(int id, String nom, String email, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Méthode de connexion
    public Personne seConnecter(List<Admin> adminList, List<Utilisateur> utilisateurList, String email, String password) {
        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                System.out.println("Bienvenue " + admin.getNom() + "! Vous êtes connecté en tant qu'administrateur.");
                estConnecter = true;
                return admin;
            }
        }

        for (Utilisateur utilisateur : utilisateurList) {
            if (utilisateur.getEmail().equals(email) && utilisateur.getPassword().equals(password)) {
                System.out.println("Bienvenue " + utilisateur.getNom() + "! Vous êtes connecté en tant qu'utilisateur.");
                estConnecter = true;
                return utilisateur;
            }
        }

        System.out.println("Échec de l'authentification. Email ou mot de passe incorrect.");
        return null;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}