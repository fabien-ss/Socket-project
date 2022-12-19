package objet;

import server.*;
import affichage.*;
import personne.Joueur;
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
    int count = 0;
    
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
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
    public Pion(int x, int y, Color color){
        this.setPosX((float) x);
        this.setPosY((float) y);
        this.setCouleur(color);
    }
    public Pion(){

    }


    public void setPoints(Terrain fenetre) {
        // ArrayList<Pion> arP2 = fenetre.getJoueurs().get(1).getPions();
        // ArrayList<Integer> distances = new ArrayList<>();
        // for(Pion pion : arP2){
        //     Float posx = (pion.getPosX() - this.getPosX()) * (pion.getPosX() - this.getPosX());
        //     Float posy = (pion.getPosY() - this.getPosY()) * (pion.getPosY() - this.getPosY());
        //     int distance = (int) Math.sqrt(posx+posy);
        //     if(distance < 20){
                
        //     }
        // }

        Jeu jeu = new Jeu(fenetre.getJoueurs());
        System.out.println("J1 "+fenetre.getJoueurs().get(0));
        System.out.println("J2 "+fenetre.getJoueurs().get(1));
        jeu.convertisseur();

        ArrayList<Way> chemin = new ArrayList<>();
        int c = 0;
        int x = (int) Math.round(2*(this.getPosX()/390));
        int y = (int) Math.round(2*(this.getPosY()/390));
        if((x-1)>-1 && y>-1 && (x-1)<3 && y<3 && jeu.getTab()[x-1][y] == 0){
            Way way = new Way(x-1, y);
            chemin.add(way);
            c+=1;
        }
        if((x+1)>-1 && y>-1 && (x+1)<3 && y<3 && jeu.getTab()[x+1][y] == 0){
            Way way = new Way(x+1, y);
            chemin.add(way);
            c+=1;
        }
        if(x>-1 && (y-1)>-1 && x<3 && (y-1)<3 && jeu.getTab()[x][y-1] == 0){
            Way way = new Way(x, y-1);
            chemin.add(way);
            c+=1;
        }
        if(x>-1 && (y+1)>-1 && x<3 && (y+1)<3 && jeu.getTab()[x][y+1] == 0 ){
            Way way = new Way(x, y+1);
            chemin.add(way);
            c+=1;
        }
        if((x+1)>-1 && (y+1)>-1 && (x+1)<3 && (y+1)<3 && jeu.getTab()[x+1][y+1] == 0){
            Way way = new Way(x+1, y+1);
            chemin.add(way);
            c+=1;
        }
        if((x-1)>-1 && (y-1)>-1 && (x-1)<3 && (y-1)<3 && jeu.getTab()[x-1][y-1] == 0){
            Way way = new Way(x-1, y-1);
            chemin.add(way);
            c+=1;
        }
        if((x+1)>-1 && (y-1)>-1 && (x+1)<3 && (y-1)<3 && jeu.getTab()[x+1][y-1] == 0){
            Way way = new Way(x+1, y-1);
            chemin.add(way);
            c+=1;
        }
        if((x-1)>-1 && (y+1)>-1 && (x-1)<3 && (y+1)<3 && jeu.getTab()[x-1][y+1] == 0){
            Way way = new Way(x-1, y+1);
            chemin.add(way);
            c+=1;
        }
        this.setWays(chemin);
    }
}