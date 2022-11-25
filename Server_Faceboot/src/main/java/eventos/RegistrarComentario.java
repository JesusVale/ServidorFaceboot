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
 * @author jegav
 */
public class RegistrarComentario implements IEvento {
    private IJsonToObject conversor;
    
    private ControladorComentario controladorComentario;

    public RegistrarComentario() {
        this.conversor = new JsonToObject();
        this.controladorComentario = new ControladorComentario();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionComentario peticionComentario = conversor.convertirPeticionComentario(peticion);
        PeticionComentario peticionComentarioRegistrado = controladorComentario.registrarComentario(peticionComentario.getComentario());
        cliente.enviarMensaje(conversor.convertirObjetoString(peticionComentarioRegistrado));
    }
}
