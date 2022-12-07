package affichage;
import personne.*;
import server.*;

public class Main{
    public static void main(String[] args)throws Exception {
        Joueur player = new Joueur(true);
        player.sendObject(player.getPions());
        player.receiveObject(player);
    }
}