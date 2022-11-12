/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.Peticion;
import eventos.ManejadorEventos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author jegav
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private List<ClientManager> clientesConectados;
    
    public ClientManager(Socket clientSocket, List<ClientManager> clientesConectados) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));;
        this.clientesConectados = clientesConectados;
    }

    @Override
    public void run() {
        IJsonToObject conversor = new JsonToObject();
        while(clientSocket.isConnected()){
            try{
                String mensajeCliente = in.readLine();
                Peticion peticion = conversor.convertirPeticion(mensajeCliente); //El server recibe una petición de un cliente (Vista)
                ManejadorEventos.get(peticion.getEvento()).ejecutar(peticion, this); //Envia la petición para que se maneje según el evento 
            } catch(IOException io){
                cerrarTodo(clientSocket, in, out);
                break;
            }
        }
    }
    
    public void notificarTodos(List<ClientManager> clientes, String mensaje){
        for(ClientManager cliente: clientes){
            if(!cliente.equals(this)){
                try{
                    cliente.out.write(mensaje);
                    cliente.out.newLine();
                    cliente.out.flush();
                } catch(IOException io){
                    cerrarTodo(clientSocket, in, out);
                }
            }
            
        }
    }
    
    public void enviarMensaje(String mensaje){
        try{
            out.write(mensaje);
            out.newLine();
            out.flush();
        } catch(IOException io){
            cerrarTodo(clientSocket, in, out);
        }
    }

    public void cerrarTodo(Socket socket, BufferedReader in, BufferedWriter out){
        try{
            socket.close();
            in.close();
            out.close();
        } catch(IOException io){
            io.printStackTrace();
        }
    }
}
