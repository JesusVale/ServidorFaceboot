/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principales;

import conversors.IJsonToObject;
import conversors.JsonToObject;
import eventos.ManejadorEventos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author jegav
 */
public class ClientManager implements Runnable{
    private Socket clientSocket;
    private BufferedReader in;
    public BufferedWriter out;
    private Integer id;
    private static List<ClientManager> clientesConectados;
    
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
                ManejadorEventos.ejecutarEvento(mensajeCliente, this);
            } catch(IOException io){
                eliminarCliente();
                cerrarTodo();
                break;
            }
        }
    }
    
    public void notificarTodos(String mensaje){
        for(ClientManager cliente: clientesConectados){
                try{
                    cliente.out.write(mensaje);
                    cliente.out.newLine();
                    cliente.out.flush();
                } catch(IOException io){
                    cerrarTodo();
                    break;
                }
        }
    }
    
    public void enviarMensaje(String mensaje){
        try{
            out.write(mensaje);
            out.newLine();
            out.flush();
        } catch(IOException io){
            cerrarTodo();
        }
    }
    
    public void eliminarCliente(){
        clientesConectados.remove(this);
    }
    
    public void cerrarTodo(){
        try{
            clientSocket.close();
            in.close();
            out.close();
        } catch(IOException io){
            io.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
