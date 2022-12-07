package action;

import affichage.*;
import personne.*;

import java.awt.event.*;

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
   
    // public MouseAction(Fenetre fenetre){
    //     this.setFenetre(fenetre);
    // }
    public MouseAction(Terrain terrain){
        this.setTerrain(terrain);
    }
    public MouseAction(){

    }
    public void	mouseClicked(MouseEvent e){

        Boolean movable = false;
        System.out.println(e.getX()+" and "+e.getY());
        
        Joueur j = this.getTerrain().getJoueurs().get(0);

        if(this.getOuverture() == 0){
            System.out.println("11");
            movable = j.canMove(e.getX(), e.getY(), this.getTerrain());
            if(movable == true){
                System.out.println("Voakitika");
                this.setOuverture(1);
                movable = false;
            }
            return;
        }
        if(this.getOuverture() == 1){
            System.out.println("Miestika");
            System.out.println("11");
            try {
                System.out.println("go");
                j.moove(e.getX(), e.getY(), this.getTerrain());
                System.out.println("nietsika");
                j.removeWays();
                System.out.println("gogo");
                
                
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            
            //tour += 1;
            j.setMove(true);
            if(tour == 1) tour = 0;
            else if(tour ==0 ) tour = 1;
            System.out.println("tsia");
            this.setOuverture(0);
            j.sendObject(j.getPions());
            return;
        } 
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