package server;

import personne.*;
import util.*;
import objet.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Jeu {

    ArrayList<Joueur> joueurs;
    int[][] tab = new int[3][3];
    
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
    public void setTab(int[][] tab) {
        this.tab = tab;
    }
    public int[][] getTab() {
        return tab;
    }
    public Jeu(int[][] tab){
        this.setTab(tab);
    }
    public Jeu(){

    }
    public Jeu(ArrayList<Joueur> joueurs){
        this.setJoueurs(joueurs);
        this.convertisseur();
        //Utile.printTab(this.getTab());
    }
    public void convertisseur(){
        for(Joueur j : this.getJoueurs()){
            for(Pion p : j.getPions()){
                int x = Math.round(2*(p.getPosX()/390));
                int y = Math.round(2*(p.getPosY()/390));
                this.getTab()[x][y] = j.getId();
            }
        }
    }
    public void checkWinner() throws Exception{
        for(int i=0;i<tab.length;i++){
            int ligne = 0;
            for(int j=0;j<tab[i].length;j++){
                ligne += tab[i][j];
                if(ligne == 3){
                }
                if(ligne == 6){
                }
            }
        }
    }
    public void updatePoints(){

    }
    /*
        000
        000
        000 
    */
    public Boolean winner(Joueur j1, Joueur j2){
        Boolean retour = false;
        ArrayList<Integer> points = new ArrayList<>();
        int count = 0;
        for(int i = 0; i < tab.length; i++){
            count += tab[i][0]+tab[i][1]+tab[i][2];
            points.add(count);
            count = 0;
        }
        for (int i = 0; i < tab.length; i++) {
            count += tab[0][i]+tab[1][i]+tab[2][i];
            points.add(count);
            count = 0;
        }
        points.add(tab[2][2] + tab[1][1] + tab[0][0]);
        points.add(tab[0][2] + tab[1][1] + tab[2][0]);

        for(int i : points){
            if(i == j1.getId()*3 && j1.nietsika()){
                j1.setWin(true);
                j1.setPoint(j1.getPoint()+1);
                retour = true;
                break;
            }
            if(i == j2.getId()*3 && j2.nietsika()){
                j2.setWin(true);
                retour = true;
                j2.setPoint(j2.getPoint()+1);
                break;
            }
        }
        return retour;
    }
}