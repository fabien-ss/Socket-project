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

public class Jeu implements Serializable{
    
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    ServerSocket serverSocket;

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
        Utile.printTab(this.getTab());
    }
    public void convertisseur(){
        for(Joueur j : this.getJoueurs()){
            for(Pion p : j.getPions()){
                int x = Math.round(2*(p.getPosX()/390));
                int y = Math.round(2*(p.getPosY()/390));
                System.out.println(x+":"+y);
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
                    throw new Exception("Winner j1");
                }
                if(ligne == 6){
                    throw new Exception("Winner j2");
                }
            }
        }
    }
    public void updatePoints(){

    }
}
