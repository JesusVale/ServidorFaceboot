/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jegav
 */
public class Server implements Runnable{

    private static List<ClientManager> clientesConectados = new ArrayList();
    private int puerto;
    private ServerSocket servidor;
    private Server(int puerto) {
        this.puerto = puerto;
    }
    
    @Override
    public void run() {
        Socket sc = null;
        try{
            servidor = new ServerSocket(puerto); //Se crea el servidor
            System.out.println("Servidor Iniciado");
            while(true){ //De esta forma el servidor siempre va a estar escuchando peticiones     
                sc = servidor.accept();
                System.out.println("Cliente Conectado");
                ClientManager cliente = new ClientManager(sc, clientesConectados);
                clientesConectados.add(cliente);
                new Thread(cliente).start();
            }
        } catch(IOException io){
            io.printStackTrace();
        }
    }

    public void cerrarServerSocket(){
        try{
            if(servidor != null){
                servidor.close();
            }
        } catch(IOException io){
            io.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Server server = new Server(6000);
        new Thread(server).start();
    }
}


