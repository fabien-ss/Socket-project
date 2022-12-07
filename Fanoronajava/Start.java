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
    ServerSocket serverSocket;
    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public Start(ServerSocket serverSocket){
        this.setServerSocket(serverSocket);
        this.setTitle("SERVER"); 
        this.setSize(200,350);  
        JLabel lab = new JLabel("Entrez le port");
        JTextField jTextField = new JTextField();
       
        jTextField.setPreferredSize(new Dimension(170,20));
        JButton jButton = new JButton("START SERVER");
        JButton stop = new JButton("STOP SERVER");
              
        JPanel pan = new JPanel();
        pan.add(lab);
        pan.add(jTextField);
        pan.add(jButton);
        pan.add(stop);

        jButton.addMouseListener(new ServerMouse(jButton, jTextField, pan));
        stop.addMouseListener(new ServerMouse(stop, jTextField, pan));
       
       // pan.setLayout(new FlowLayout());
        this.add(pan);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
