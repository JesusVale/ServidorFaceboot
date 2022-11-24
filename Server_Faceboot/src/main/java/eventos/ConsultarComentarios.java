/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorComentario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionComentarios;
import principales.ClientManager;

/**
 *
 * @author tonyd
 */
public class ConsultarComentarios implements IEvento{
    private IJsonToObject conversor;
    
    private ControladorComentario controladorComentario;

    public ConsultarComentarios() {
        this.conversor = new JsonToObject();
        this.controladorComentario = new ControladorComentario();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionComentarios peticionRespuesta = controladorComentario.consultarComentarios();
        cliente.enviarMensaje(conversor.convertirObjetoString(peticionRespuesta));
    }
}
