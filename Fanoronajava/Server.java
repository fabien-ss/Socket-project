package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import affichage.Start;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected!");
                Active clientHandler = new Active(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void closeServerSocket(){
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException{
        Start start = new Start();
    }
}
