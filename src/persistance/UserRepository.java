package persistance;

import data.Utilisateur;

import java.util.List;

public interface UserRepository {
    public void save(List<Utilisateur> user);
    public List<Utilisateur> load();
}
