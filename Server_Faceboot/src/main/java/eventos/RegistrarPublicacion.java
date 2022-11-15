/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorPublicacion;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import entidades.Publicacion;
import peticiones.Peticion;
import peticiones.PeticionPublicacion;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class RegistrarPublicacion implements IEvento {

    private IJsonToObject conversor;
    
    private ControladorPublicacion controladorPublicacion;

    public RegistrarPublicacion() {
        this.conversor = new JsonToObject();
        this.controladorPublicacion = new ControladorPublicacion();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        
        PeticionPublicacion peticionPublicacion = conversor.convertirPeticionPublicacion(peticion);
        controladorPublicacion.registrarPublicacion(peticionPublicacion.getPublicacion());
        //Peticion peticionRespuesta = new Peticion(Eventos.registrarPublicacion, 400, conversor.convertirObjetoString(PublicacionRegistrado));
        //cliente.enviarMensaje(conversor.convertirObjetoString(peticion));
    }
    
    
    
}
