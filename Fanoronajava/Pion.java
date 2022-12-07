package objet;

import server.*;
import affichage.*;
import util.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Pion implements Serializable{

    Float posX;
    Float posY;

    Color couleur;
    Boolean flux;
    ArrayList<Way> ways;
    
    Boolean renew = false;
    
    public void setRenew(Boolean renew) {
        this.renew = renew;
    }
    public Boolean getRenew() {
        return renew;
    }
    public void setWays(ArrayList<Way> ways) {
        this.ways = ways;
    }
    public ArrayList<Way> getWays() {
        return ways;
    }
    public void setFlux(Boolean flux) {
        this.flux = flux;
    }
    public Boolean getFlux() {
        return flux;
    }
    public void setCouleur(Color color){ 
        this.couleur = color;
    }
    public Color getCouleur() {
        return couleur;
    }
    public Float getPosX() {
        return posX;
    }
    public void setPosX(Float posX) {
        this.posX = posX;
    }
    public Float getPosY() {
        return posY;
    }
    public void setPosY(Float posY) {
        this.posY = posY;
    }
    public Pion(int x, int y, Color color){
        this.setPosX((float) x);
        this.setPosY((float) y);
        this.setCouleur(color);
    }
    public Pion(){

    }
    public void setPoints(Terrain fenetre) {
        
        Jeu jeu = fenetre.getJeu();

        ArrayList<Way> chemin = new ArrayList<>();
        int c = 0;
        int x = (int) Math.round(2*(this.getPosX()/390));
        int y = (int) Math.round(2*(this.getPosY()/390));
        if((x-1)>-1 && y>-1 && (x-1)<3 && y<3 && jeu.getTab()[x-1][y] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x-1, y);
            chemin.add(way);
            c+=1;
        }
        if((x+1)>-1 && y>-1 && (x+1)<3 && y<3 && jeu.getTab()[x+1][y] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x+1, y);
            chemin.add(way);
            c+=1;
        }
        if(x>-1 && (y-1)>-1 && x<3 && (y-1)<3 && jeu.getTab()[x][y-1] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x, y-1);
            chemin.add(way);
            c+=1;
        }
        if(x>-1 && (y+1)>-1 && x<3 && (y+1)<3 && jeu.getTab()[x][y+1] == 0 ){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x, y+1);
            chemin.add(way);
            c+=1;
        }
        if((x+1)>-1 && (y+1)>-1 && (x+1)<3 && (y+1)<3 && jeu.getTab()[x+1][y+1] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x+1, y+1);
            chemin.add(way);
            c+=1;
        }
        if((x-1)>-1 && (y-1)>-1 && (x-1)<3 && (y-1)<3 && jeu.getTab()[x-1][y-1] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x-1, y-1);
            chemin.add(way);
            c+=1;
        }
        if((x+1)>-1 && (y-1)>-1 && (x+1)<3 && (y-1)<3 && jeu.getTab()[x+1][y-1] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x+1, y-1);
            chemin.add(way);
            c+=1;
        }
        if((x-1)>-1 && (y+1)>-1 && (x-1)<3 && (y+1)<3 && jeu.getTab()[x-1][y+1] == 0){
            Utile.printTab(jeu.getTab());
            Way way = new Way(x-1, y+1);
            chemin.add(way);
            c+=1;
        }
        this.setWays(chemin);
    }
}