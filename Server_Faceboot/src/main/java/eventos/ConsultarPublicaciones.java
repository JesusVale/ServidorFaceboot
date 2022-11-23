/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorPublicacion;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionPublicaciones;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ConsultarPublicaciones implements IEvento {

    private IJsonToObject conversor;
    
    private ControladorPublicacion controladorPublicacion;

    public ConsultarPublicaciones() {
        this.conversor = new JsonToObject();
        this.controladorPublicacion = new ControladorPublicacion();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionPublicaciones peticionRespuesta = controladorPublicacion.consultarPublicaciones();
        System.out.println("eaeaeaeaeaeaeaeaeae");
        System.out.println(conversor.convertirObjetoString(peticionRespuesta));
        cliente.enviarMensaje(conversor.convertirObjetoString(peticionRespuesta));
    }
}
