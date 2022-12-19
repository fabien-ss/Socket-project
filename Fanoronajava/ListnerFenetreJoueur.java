package action;
import server.*;

import java.awt.event.*;
import java.net.ServerSocket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;

import personne.Joueur;

public class ListnerFenetreJoueur implements MouseListener{
    Vector<JTextField> jTextFields = new Vector<>();
    String error;
    public void setjTextFields(Vector<JTextField> jTextFields) {
        this.jTextFields = jTextFields;
    }
    public Vector<JTextField> getjTextFields() {
        return jTextFields;
    }
    public ListnerFenetreJoueur(Vector<JTextField> jTextFields, String error){
        this.setjTextFields(jTextFields);   
        this.error = error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }
    public void	mouseClicked(MouseEvent e){
        String nom = jTextFields.get(0).getText();
        String ip = jTextFields.get(1).getText();
        int port = Integer.parseInt(jTextFields.get(2).getText());
        int id = Integer.parseInt(jTextFields.get(3).getText());
        try{
            Joueur joueur = new Joueur(true, nom, port, ip, id);
            joueur.sendObject(joueur.getPions());
            joueur.receiveObject(joueur);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
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
