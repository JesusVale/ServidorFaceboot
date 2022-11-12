/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import java.util.ArrayList;
import java.util.List;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ManejadorListaEventos {
    public List<ClientManager> publicacionListeners;
    public List<ClientManager> comentariosListeners;
    private static ManejadorListaEventos manejadorListaEventos;
    
    private ManejadorListaEventos(){
        this.publicacionListeners = new ArrayList();
        this.comentariosListeners = new ArrayList();
    }
    
    public static ManejadorListaEventos getInstance(){
        if(manejadorListaEventos == null){
            manejadorListaEventos = new ManejadorListaEventos();
        }
        return manejadorListaEventos;
    }
    
    public void agregarRegistrarPublicacionListener(ClientManager cliente){
        publicacionListeners.add(cliente);
    }
    
    public void agregarRegistrarComentarioListener(ClientManager cliente){
        comentariosListeners.add(cliente);
    }

    public List<ClientManager> getPublicacionListeners() {
        return publicacionListeners;
    }

    public List<ClientManager> getComentariosListeners() {
        return comentariosListeners;
    }

}
