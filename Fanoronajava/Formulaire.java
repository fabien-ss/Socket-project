package affichage;

import server.*;
import objet.*;
import action.*;
import personne.*;

import javax.swing.*;
import javax.swing.JTextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class Formulaire extends JPanel{
    Vector<JTextField> champs = new Vector<>();
    String error = "Nothing wrong"; 
    public void setChamps(Vector<JTextField> champs) {
        this.champs = champs;
    }
    public Vector<JTextField> getChamps() {
        return champs;
    }
    public Formulaire(Vector<JTextField> jTextFields){
        this.setChamps(jTextFields);
    }
    public Formulaire(){
        this.setSize(200,400);
    }
    public void paint(Graphics g1){
        super.paint(g1);
        Graphics2D g =(Graphics2D)g1;
        g.setColor(Color.WHITE);
        g.drawString(error, 200,300);
    }
}