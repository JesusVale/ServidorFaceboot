/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorComentario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionComentario;
import principales.ClientManager;

/**
 *
 * @author tonyd
 */
public class EliminarComentario implements IEvento {

    private IJsonToObject conversor;
    
    private ControladorComentario controladorComentario;

    public EliminarComentario() {
        this.conversor = new JsonToObject();
        this.controladorComentario = new ControladorComentario();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionComentario peticionComentarioRecibido = conversor.convertirPeticionComentario(peticion);
        PeticionComentario peticionComentarioRespuesta = controladorComentario.eliminarComentario(peticionComentarioRecibido.getComentario());
        cliente.enviarMensaje(conversor.convertirObjetoString(peticionComentarioRespuesta));
    }
    
}
