package affichage;

import objet.*;
import personne.*;
import action.*;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame{

    ArrayList<Joueur> joueurs;
    Terrain terrain;
  
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    public Terrain getTerrain() {
        return terrain;
    }
    public void setJoueurs(ArrayList<Joueur> joueur){
        this.joueurs = joueur;
    }
    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }
    public Fenetre(ArrayList<Joueur> arrayList){

        System.out.println(arrayList.size());
        
        this.setBackground(new Color(10,250,10));
        this.setTitle(arrayList.get(0).getNom().toUpperCase());
        this.setSize(700,450);

        Terrain terrain = new Terrain(this, arrayList);
        terrain.setBackground(new Color(6,50,20));

        this.setTerrain(terrain);

        this.add(this.getTerrain());
        this.addMouseListener(new MouseAction(this.getTerrain()));        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }    
}
