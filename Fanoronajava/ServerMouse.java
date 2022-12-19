package action;
import server.*;

import java.awt.event.*;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ServerMouse implements MouseListener{
    ServerSocket serverSocket;
    JTextField jTextField;
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
    public ServerMouse(JButton jButton, JTextField jTextField){
        this.setjButton(jButton);
        this.setjTextField(jTextField);
    }
    public void	mouseClicked(MouseEvent e){
        if(jButton.getText().equals("START SERVER")){
            try{
                String port = this.getjTextField().getText();
                System.out.println(port);
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
                Server server = new Server(serverSocket);
                server.startServer();
            }
            catch(Exception exception){
                exception.printStackTrace();
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
