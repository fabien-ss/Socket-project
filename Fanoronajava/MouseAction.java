package action;

import affichage.*;
import personne.*;
import util.Utile;
import server.*;

import java.awt.event.*;
import java.awt.*;

public class MouseAction implements MouseListener{

    Terrain terrain;

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    Boolean remove = false;
    int ouverture = 0;
    int tour = 0;

    public void setTour(int tour) {
        this.tour = tour;
    }
    public int getTour() {
        return tour;
    }
    public void setOuverture(int ouverture) {
        this.ouverture = ouverture;
    }
    public int getOuverture() {
        return ouverture;
    }
    public void setRemove(Boolean remove) {
        this.remove = remove;
    }
    public Boolean getRemove() {
        return remove;
    }
    public MouseAction(Terrain terrain){
        this.setTerrain(terrain);
    }
    public MouseAction(){

    }
    public void	mouseClicked(MouseEvent e){

        Boolean movable = false;
        System.out.println(e.getX()+" and "+e.getY());
        
        Joueur j = this.getTerrain().getJoueurs().get(0);
        Joueur j2 = new Joueur();
     //   if(j.getMove()){

            if(this.getTerrain().getJoueurs().get(1) != null){
                j2 = this.getTerrain().getJoueurs().get(1);
            }
            if(this.getOuverture() == 0){
                System.out.println("11");
                if(j.getMove()) movable = j.canMove(e.getX(), e.getY(), this.getTerrain());
                
                System.out.println("ETO "+this.getTerrain().getJoueurs().size());
                j.soutPlayerPoints();
                this.getTerrain().getJoueurs().get(1).soutPlayerPoints();
                System.out.println("---------");
                Jeu temp = new Jeu(this.getTerrain().getJoueurs());
                Utile.printTab(temp.getTab());
                if(movable == true){
                    this.setOuverture(1);
                    movable = false;
                }
                return;
            }
            if(this.getOuverture() == 1){
                try {
                    j.moove(e.getX(), e.getY(), this.getTerrain());
                    if(j.nietsika()){
                        System.out.println("EFA NIETSIKA DAHOLO ANH");
                        if(this.getTerrain().getJeu().winner(j, j2)){
                            if(j.getWin()){
                                this.getTerrain().setBackground(new Color(10,23,12));
                                this.getTerrain().setMessage("VOUS AVEZ GAGNEZ");
                                this.getTerrain().setMessage("WIN !! +1 "+"Vous: " + j.getPoint() +" VS J2: "+j2.getPoint());
                                
                                j.setMove(false);
                                j.sendObject(j.getPions());

                                j.reset();
                                j2.reset();
                            }
                        }
                        // if(j2.getWin()){
                        //     this.getTerrain().setBackground(Color.RED);
                        //     j.setMove(false);
                        //     j2.setMove(false);
                        // }
                    }
                    j2.soutPlayerPoints();
                    j.removeWays();
                    j.sendObject(j.getPions());
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
                //tour += 1;
                //j.sendObject(j.getPions());
                if(tour == 1) tour = 0;
                else if(tour ==0 ) tour = 1;
                this.setOuverture(0);
                j.setMove(false);
                return;
            } 
       // }
    }
    public void	mouseEntered(MouseEvent e){
    }
    public void	mouseExited(MouseEvent e){
    }
    public void	mousePressed(MouseEvent e){
    }
    public void	mouseReleased(MouseEvent e){
    }
}