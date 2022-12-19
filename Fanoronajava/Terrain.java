package affichage;

import server.*;
import objet.*;
import action.*;
import personne.*;

import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;

public class Terrain extends JPanel{
    
    ArrayList<Joueur> joueurs;
    Jeu jeu;
    
    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
    public Jeu getJeu() {
        return jeu;
    }
    public void setJoueurs(ArrayList<Joueur> joueur){
        this.joueurs = joueur;
    }
    public ArrayList<Joueur> getJoueurs(){
        return this.joueurs;
    }

    Fenetre fenetre;
    

    String message = "FANORONA LOCAL";
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
    }
    public Fenetre getFenetre() {
        return fenetre;
    }

    public Terrain(Fenetre fenetre, ArrayList<Joueur> joueurs, Jeu jeu){
        this.setFocusable(true);
        this.requestFocus();
        this.addMouseListener(new MouseAction(this));
        
        this.setFenetre(fenetre);
        this.setJeu(jeu);
        this.setJoueurs(joueurs);
    }
    public void paint(Graphics g1){
        super.paint(g1);
        Graphics2D g =(Graphics2D)g1;
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2.0f));
        g.drawString(this.getMessage(), 450, 200);

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(6.0f));
        g.drawLine(10, 10, 390, 10);
        g.drawLine(10,200,200,10);
        g.drawLine(200,10,10,200);
        g.drawLine(10, 10, 10, 390);
        g.drawLine(390, 10, 390, 390);
        g.drawLine(10, 10, 390, 390);
        g.drawLine(10, 390, 390, 10);
        g.drawLine(200, 10, 200, 390);
        g.drawLine(10, 200, 390, 200);
        g.drawLine(10, 390,390, 390);
        g.drawLine(200, 390,390, 200);
        g.drawLine(390, 200,200, 390);
        g.drawLine(200, 10,390, 200);
        g.drawLine(10, 200,200, 390);

        for(Joueur j : this.getJoueurs()){
            // if(j.getWin()){
            //     g.setColor(Color.RED);
            //     g.drawString("WE HAVE A WINNER", 350, 225);
            //     j.setMove(false);
            // }
            for(Pion p : j.getPions()){
                g.setColor(p.getCouleur());
                Image p1 = getToolkit().getImage("feature-6.jpg");
                
                g.fillOval((int) (p.getPosX()-10), (int) (p.getPosY()-10), 20, 20);
                //g.drawImage(p1, (int) (p.getPosX()-10), (int) (p.getPosY()-10), this);
                if(p.getWays() != null){
                    for(Way way : p.getWays()){
                        g.setColor(Color.BLACK);
                        if(j.getMove() == true){
                            g.setColor(Color.BLACK);
                        }
                        g.setStroke(new BasicStroke(2.0f));
                        g.drawOval(way.getX(), way.getY(), 15, 15);
                    }
                }
            }
        }       
        repaint();
    }
}