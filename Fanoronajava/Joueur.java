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
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    public Boolean getAccepted(){
        return this.accepted;
    }
    Boolean move;

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
    int point;
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
    public Joueur(Boolean move) throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name");
        this.setNom(scanner.nextLine());
        this.setMove(move);

        System.out.println("Enter port");
        port = scanner.nextInt();

        System.out.println("Enter point");
        int id = scanner.nextInt();
        while(id > 2){
            System.out.println("Invalide");
            id = scanner.nextInt();        
        }

        this.setId(id);

        ArrayList<Pion> pionsA = new ArrayList<>();
        if(id == 1){
            pionsA.add(new Pion(10,10,new Color(16,52,166)));
            pionsA.add(new Pion(200, 10, new Color(16,52,166)));
            pionsA.add(new Pion(390,10, new Color(16,52,166)));
        }
        else if(id != 1){
            pionsA.add(new Pion(390,390, new Color(0,142,142)));
            pionsA.add(new Pion(200, 390, new Color(0,142,142)));
            pionsA.add(new Pion(10,390, new Color(0,142,142)));
        }

        this.setPions(pionsA);
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.add(this);
        
        this.socket = new Socket(ip, port);
        System.out.println("Vita");

        //Jeu jeu = (Jeu) getGameFromServer();

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
            int i = 0;
            @Override
            public void run() {
                j1.sendObject(j1.getPions());
                Joueur j2 = new Joueur();
               
                j2.setMove(true);
                ArrayList<Pion> pionsB = new ArrayList<>();
                j2.setPions(pionsB);
                ArrayList<Joueur> joueurs = new ArrayList<>();
                joueurs.ensureCapacity(2);                
                joueurs.add(j1);
                joueurs.add(j2);
                
                Fenetre fenetre = new Fenetre(joueurs);

                while(socket.isConnected()){
                    try {
                        ArrayList<Pion> pionsAdverse = (ArrayList<Pion>) objectInputStream.readObject();
                        if(pionsAdverse != null){
                           if(i%2 != 0) accepted = false;
                           else accepted = true;
                           i += 1;
                        }
                        if(j1.getId() == 1) j2.setId(2);
                        if(j1.getId() == 2) j2.setId(1);
                        j2.setPions(pionsAdverse);
                        j2.setMove(true);
                        Jeu vao = new Jeu(joueurs);
                        vao.convertisseur();
                        fenetre.getTerrain().setJeu(vao);
                        System.out.println(pionsAdverse);
                    } catch (ClassNotFoundException | IOException e) {
                        accepted = false;
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
                    if(distance < 40){
                        terrain.getJeu().getTab()[Math.round(2*(p.getPosX()/390))][Math.round(2*(p.getPosY()/390))] = 0;
                        p.setPosX((float) x);
                        p.setPosY((float) y);
                        terrain.getJeu().getTab()[Math.round(2*(p.getPosX()/390))][Math.round(2*(p.getPosY()/390))] = this.getId();
                        p.setRenew(true);
                        p.setWays(null);
                        this.setMove(false);
                        terrain.getJeu().convertisseur();
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
            if(distance <= 40){ 
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
}
