package action;
import server.*;

import java.awt.event.*;
import java.net.ServerSocket;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ServerMouse implements MouseListener{
    ServerSocket serverSocket;
    JTextField jTextField;
    JPanel jpanel;
    public void setJpanel(JPanel p){
        this.jpanel = p;
    }
    public JPanel getJpanel(){
        return this.jpanel;
    }
    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }
    public JTextField getjTextField() {
        return jTextField;
    }
    JButton jButton;
    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }
    public JButton getjButton() {
        return jButton;
    }
    public ServerMouse(){

    }
    public ServerMouse(JButton jButton, JTextField jTextField, JPanel panel){
        this.setjButton(jButton);
        this.setjTextField(jTextField);
        this.setJpanel(panel);
    }
    public void	mouseClicked(MouseEvent e){
        if(jButton.getText().equals("START SERVER")){
            try{ 
                String port = this.getjTextField().getText();
                System.out.println(port);
                
                this.getJpanel().setBackground(new Color(123,1,220));

                BufferedImage img = ImageIO.read(new File("Loading_icon.gif"));
                JLabel pic = new JLabel(new ImageIcon(img));
                pic.setPreferredSize((new Dimension(170,100)));
                this.getJpanel().add(pic);

                this.serverSocket = new ServerSocket(Integer.parseInt(port));
                Server server = new Server(serverSocket);
                server.startServer();
            }
            catch(Exception exception){
                exception.printStackTrace();
            }
        }
        if(jButton.getText().equals("STOP SERVER")){
            try{
                if(serverSocket != null ){
                    this.getjTextField().setText("SERVER STOPED");
                    serverSocket.close();
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
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
