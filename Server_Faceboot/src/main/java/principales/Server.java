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
    private static Server server;
    
    private Server(int puerto) {
        this.puerto = puerto;
    }
    
    public static Server getInstance(){
        if(server == null){
            server = new Server(9000);
        }
        return server;
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
    
    public void notificarTodos(String mensaje){
        System.out.println("Notificando a todos en el server");
        System.out.println(clientesConectados.size());
        for(ClientManager cliente: clientesConectados){
                try{
                    cliente.out.write(mensaje);
                    cliente.out.newLine();
                    cliente.out.flush();
                } catch(IOException io){
                    io.printStackTrace();
                    break;
                }
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
        new Thread(Server.getInstance()).start();
    }
}


