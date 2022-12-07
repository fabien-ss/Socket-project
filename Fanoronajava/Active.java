package server;

import affichage.*;
import objet.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Active implements Runnable{

    Socket socket;
    static ArrayList<Active> actives = new ArrayList<>();
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String id;
    public Active(Socket socket){
        this.socket = socket;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            actives.add(this);
            // bufferedReader = new BufferedReader(new InputStreamReader((socket.getInputStream())));
            // this.id = bufferedReader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
    @Override
    public void run(){
        while(socket.isConnected()){
            try{
                ArrayList<Pion> p = (ArrayList<Pion>) objectInputStream.readObject();
                System.out.println("Ito");
                System.out.println(p);
                retour(p);
            }
            catch(Exception e){

            }
        }
    }
    public void retour(ArrayList<Pion> p){
        for (Active clientHandler : actives) {
            try {
                if (!clientHandler.equals(this)) {
                    clientHandler.objectOutputStream.writeObject(p);
                    clientHandler.objectOutputStream.reset();
                    clientHandler.objectOutputStream.flush();
                }

            } catch (Exception e) {
                //TODO: handle exception
              
            }
        }
    }
}
