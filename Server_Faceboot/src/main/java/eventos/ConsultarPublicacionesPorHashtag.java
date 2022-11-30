/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorPublicacion;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionPublicaciones;
import peticiones.PeticionString;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ConsultarPublicacionesPorHashtag implements IEvento {

    private IJsonToObject conversor;
    
    private ControladorPublicacion controladorPublicacion;

    public ConsultarPublicacionesPorHashtag() {
        this.conversor = new JsonToObject();
        this.controladorPublicacion = new ControladorPublicacion();
    }
    
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionString peticionEnviada = conversor.convertirPeticionString(peticion);
        PeticionPublicaciones respuesta = controladorPublicacion.consultarPublicacionesPorEtiqueta(peticionEnviada.getStr());
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
    
}
