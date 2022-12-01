/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorPublicacion;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import entidades.Publicacion;
import peticiones.PeticionPublicacion;
import principales.ClientManager;

/**
 *
 * @author tonyd
 */
public class EliminarPublicacion implements IEvento{
    private ControladorPublicacion controladorPublicacion;
    private IJsonToObject conversor;

    public EliminarPublicacion() {
        this.conversor = new JsonToObject();
        this.controladorPublicacion = new ControladorPublicacion();
    }

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionPublicacion peticionPublicacion = conversor.convertirPeticionPublicacion(peticion);
        PeticionPublicacion respuesta = controladorPublicacion.eliminarPublicacion(peticionPublicacion.getPublicacion());
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
}
