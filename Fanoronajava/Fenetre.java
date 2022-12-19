package affichage;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import personne.*;
import server.Jeu;


public class Fenetre extends JFrame{
    ArrayList<Joueur> joueurs;
    Terrain terrain;
    
    public void setTerrain(Terrain terrain){
        this.terrain = terrain;
    }
    public Terrain getTerrain(){
        return this.terrain;
    }
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
   
    public Fenetre(ArrayList<Joueur> arrayList, Jeu jeu){
     
        this.setTitle("Fanorona");
        this.setSize(700,450);

        Terrain terrain = new Terrain(this, arrayList, jeu);
        
        terrain.setBackground(new Color(6,50,20));
        this.setTerrain(terrain);

        this.add(terrain);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }    
}
