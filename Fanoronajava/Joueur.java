package personne;

import objet.*;
import affichage.*;
import server.*;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;

public class Joueur {

    Socket socket;
    String ip = "localhost";
    int port = 1111;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    
    /**
     *
     */

    boolean accepted = false;
    int id;
    Boolean move = true;

    Boolean win = false;
    public void setWin(Boolean win) {
        this.win = win;
    }
    public Boolean getWin() {
        return win;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    public int getPort() {
        return port;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getIp() {
        return ip;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setMove(Boolean move) {
        this.move = move;
    }
    public Boolean getMove() {
        return move;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }    
    String nom;
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    int point = 0;
    public void setPoint(int point) {
        this.point = point;
    }
    public int getPoint() {
        return point;
    }
    ArrayList<Pion> pions;
    public void setPions(ArrayList<Pion> pions) {
        this.pions = pions;
    }
    public ArrayList<Pion> getPions() {
        return pions;
    }
    public Joueur(){

    }
    public Joueur(Boolean move, String nom, int port, String ip, int id) throws Exception{
        this.setNom(nom);
        this.setPort(port);
        this.setIp(ip);
        this.setId(id);

        ArrayList<Pion> pionsA = new ArrayList<>();
        if(id == 3){
            pionsA.add(new Pion(10,10,new Color(16,52,166)));
            pionsA.add(new Pion(200, 10, new Color(16,52,166)));
            pionsA.add(new Pion(390,10, new Color(16,52,166)));
        }
        else if(id == 5){
            pionsA.add(new Pion(390,390, new Color(0,142,142)));
            pionsA.add(new Pion(200, 390, new Color(0,142,142)));
            pionsA.add(new Pion(10,390, new Color(0,142,142)));
        }

        this.setPions(pionsA);
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(this);
        
        this.socket = new Socket(ip, port);

        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream()); 
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void sendObject(Object object){
        System.out.println("Mihasa");
        try{
            System.out.println(object);
            objectOutputStream.writeObject(object);
            System.out.println("succes");
            objectOutputStream.reset();
            objectOutputStream.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void receiveObject(Joueur j1){
        new Thread(new Runnable(){
            @Override
            public void run() {
                
                Joueur j2 = new Joueur();
                if(j1.getId() == 3) j2.setId(5);
                else if(j1.getId() == 5) j2.setId(3);
                j2.setMove(true);
                ArrayList<Pion> pionsB = new ArrayList<>();
                j2.setPions(pionsB);
                ArrayList<Joueur> joueurs = new ArrayList<>();
                joueurs.ensureCapacity(2);                
                joueurs.add(j1);
                joueurs.add(j2);
                
                Jeu jeu = new Jeu();
                Fenetre fenetre = new Fenetre(joueurs, jeu);

                while(socket.isConnected()){
                    try {

                        ArrayList<Pion> pionsAdverse = (ArrayList<Pion>) objectInputStream.readObject();
                        j2.setPions(pionsAdverse);

                        Jeu game = new Jeu(joueurs);                        

                        if(j2.nietsika()){
                            System.out.println("EFA NIETSIKA DAHOLO ANH");
                            if(game.winner(j1, j2)){
                         
                                if(j2.getWin()){
                                    fenetre.getTerrain().setBackground(new Color(34,12,39));
                                    fenetre.getTerrain().setMessage("+1 "+"Vous:" + j1.getPoint() +" VS J2:"+j2.getPoint());
                                    j1.setMove(false);
                                    j1.reset();
                                    j2.reset();
                                }
                            }
                        }
                        j1.setMove(true);
                        System.out.println(pionsAdverse);
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void moove(int x, int y, Terrain terrain) throws Exception{
        for(Pion p : this.getPions()){
            if(p.getRenew() == true){
                for(Way way : p.getWays()){
                    int x1 = (way.getX() - x)*(way.getX() - x);
                    int y1 = (way.getY() - y)*(way.getY() - y);
                    int distance = (int) Math.sqrt(x1+y1);
                    if(distance < 20){
                        terrain.getJeu().getTab()[Math.round(2*(p.getPosX()/390))][Math.round(2*(p.getPosY()/390))] = 0;
                        p.setCount(2);
                        p.setPosX((float) x);
                        p.setPosY((float) y);
                        terrain.getJeu().getTab()[Math.round(2*(p.getPosX()/390))][Math.round(2*(p.getPosY()/390))] = this.getId();
                        p.setRenew(true);
                        p.setWays(null);
                      //  terrain.getJeu().convertisseur();
                        terrain.getJeu().checkWinner();
                    }
                }                
            }
        }
    }

    public Boolean canMove(int x, int y, Terrain terrain){

        for(Pion p : this.getPions()){
            int x1 = (int) ((p.getPosX() - x)*(p.getPosX() - x));
            int y1 = (int) ((p.getPosY() - y)*(p.getPosY() - y));
            int distance = (int) Math.sqrt(x1+y1); 
            if(distance <= 20){ 
                p.setRenew(true);
                p.setPoints(terrain);
                return true;
            }
        }
        return false;
    }

    public void removeWays(){
        for(Pion p : this.getPions()){
            ArrayList<Way> ways = new ArrayList<>();
            p.setWays(ways);
        }
    }
    
    public Boolean initializeSocket(){
        try{
            Socket socket = new Socket(ip, port);
            this.setSocket(socket);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }
    public void soutPlayerPoints(){
        for(Pion p : this.getPions()){
            System.out.println(p.getPosX() +" : "+ p.getPosY());
        }
    }
    
    public Boolean nietsika(){
        for(Pion p : this.getPions()){
            if(p.getCount() == 0){
                return false;
            }
        }
        return true;
    }
    
    public void reset(){
        for(Pion p : this.getPions()){
            p.setCount(0);   
        }
    }
}
