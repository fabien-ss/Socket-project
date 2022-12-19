package affichage;
import action.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import personne.*;
import server.Jeu;


public class FenetreJoueur extends JFrame{
    public FenetreJoueur(){
        this.setTitle("FANORONA");
        
        Vector<JTextField> champs = new Vector<>();
        Formulaire formulaire = new Formulaire();
        formulaire.setBackground(new Color(6,50,20));
        JLabel label1 = new JLabel("Comment voulez-vous etre appele?");
        label1.setPreferredSize(new Dimension(300,20));
        formulaire.add(label1);
        JTextField jTextField1 = new JTextField("PSEUDO");
        champs.add(jTextField1);
        jTextField1.setPreferredSize(new Dimension(300,20));
        formulaire.add(jTextField1);
        JLabel label2 = new JLabel("Entrer l'adresse IP");
        label2.setPreferredSize(new Dimension(300,20));
        formulaire.add(label2);
        JTextField jTextField2 = new JTextField("localhost");
        champs.add(jTextField2);
        jTextField2.setPreferredSize(new Dimension(300,20));
        formulaire.add(jTextField2);
        JLabel label3 = new JLabel("Entrer le port");
        formulaire.add(label3);
        label3.setPreferredSize(new Dimension(300,20));
        JTextField jTextField3 = new JTextField("1111");
        jTextField3.setPreferredSize(new Dimension(300,20));
        champs.add(jTextField3);
        formulaire.add(jTextField3);
        JLabel label4 = new JLabel("Enter votre pion 3-5");
        label4.setPreferredSize(new Dimension(300,20));
        formulaire.add(label4);
        label3.setPreferredSize(new Dimension(300,20));
        JTextField jTextField4 = new JTextField("3-5");
        jTextField3.setPreferredSize(new Dimension(300,20));
        champs.add(jTextField4);
        formulaire.add(jTextField4);
        formulaire.setChamps(champs);
        JButton ok = new JButton("COMMENCER A JOUER");
        String error = "Nothing wrong";
        ok.addMouseListener(new ListnerFenetreJoueur(formulaire.getChamps(), error));
        formulaire.add(ok);
        this.add(formulaire);
        this.setSize(450,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        FenetreJoueur fenetreJoueur = new FenetreJoueur();
    }
}