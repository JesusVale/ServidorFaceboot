/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorNotificacion;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionNotificaciones;
import peticiones.PeticionUsuario;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ConsultarNotificacionesPorRemitente implements IEvento {

    private ControladorNotificacion controladorNotificacion;
    private IJsonToObject conversor;
    
    public ConsultarNotificacionesPorRemitente() {
        this.conversor = new JsonToObject();
        this.controladorNotificacion = new ControladorNotificacion();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionUsuario peticionRecibida = conversor.convertirPeticionUsuario(peticion);
        PeticionNotificaciones respuesta = controladorNotificacion.consultarNotificacionesPorRemitente(peticionRecibida.getUsuario());
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }

}
