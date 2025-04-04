public class Main {
    public static void main(String [] args){
        Chambre.addChamber("Chambre 1", 100, "Description 1", "Type 1");
        Chambre.addChamber("Chambre 2", 200, "Description 2", "Type 2");
        Chambre.addChamber("Chambre 3", 300, "Description 3", "Type 3");
        for(int i = 0; i< Chambre.chambres.size(); i++){
            System.out.println(Chambre.chambres.get(i).toString());
        }
    }
}
