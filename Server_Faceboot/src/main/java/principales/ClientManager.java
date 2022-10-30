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
    private BufferedWriter out;
    private List<ClientManager> clientesConectados;
    private String codigo;
    private double seguimiento;
    
    public ClientManager(Socket clientSocket, List<ClientManager> clientesConectados) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));;
        this.clientesConectados = clientesConectados;
        this.codigo = in.readLine();
    }

    @Override
    public void run() {
        IJsonToObject convertidorJson = new JsonToObject();
        while(clientSocket.isConnected()){
            try{
                String mensajeCliente = in.readLine();   //
                String[] mensajes = convertidorJson.convertirStrings(mensajeCliente);
                ManejadorEventos.eventos.get(mensajes[0]).ejecutar(mensajes, this);
            } catch(IOException io){
                cerrarTodo(clientSocket, in, out);
                break;
            }
        }
    }
    
    public void enviarMensaje(String codigo, String mensaje){
        for(ClientManager cliente: clientesConectados){
            try{
                if(cliente.getCodigo().equals(codigo)){
                    cliente.out.write(mensaje);
                    cliente.out.newLine();
                    cliente.out.flush();
                }
            } catch(IOException io){
                cerrarTodo(clientSocket, in, out);
            }
        }
    }
    
    public void enviarMensaje(double seguimiento, String mensaje){
        for(ClientManager cliente: clientesConectados){
            try{
                if(cliente.getSeguimiento() == seguimiento){
                    cliente.out.write(mensaje);
                    cliente.out.newLine();
                    cliente.out.flush();
                }
            } catch(IOException io){
                cerrarTodo(clientSocket, in, out);
            }
        }
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento() {
        double seguimiento;
        do{
            seguimiento = Math.random();
        } while(existsSeguimiento(seguimiento));
        
        this.seguimiento = seguimiento;
    }
    
    public boolean existsSeguimiento(double seguimiento){
        for(ClientManager cliente: clientesConectados){
            if(cliente.getSeguimiento() == seguimiento){
                return true;
            }
        }
        return false;
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
