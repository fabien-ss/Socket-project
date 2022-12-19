package affichage;
import action.ServerMouse;

import java.awt.Dimension;
import java.awt.*;
import java.net.ServerSocket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Start extends JFrame{
    
    public Start(){
        this.setTitle("SERVER"); 
        this.setSize(200,250);  
        JLabel lab = new JLabel("Entrez le port");
        JTextField jTextField = new JTextField();
       
        jTextField.setPreferredSize(new Dimension(170,20));
        JButton jButton = new JButton("START SERVER");
        jButton.addMouseListener(new ServerMouse(jButton, jTextField));      
        JPanel pan = new JPanel();

        pan.add(lab);
        pan.add(jTextField);
        pan.add(jButton);
        try{
            BufferedImage img = ImageIO.read(new File("Loading_icon.gif"));
            JLabel pic = new JLabel(new ImageIcon(img));
            pic.setPreferredSize((new Dimension(170,100)));
            pan.add(pic);
        }
        catch(Exception eee){

        }
       // pan.setLayout(new FlowLayout());
        this.add(pan);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
