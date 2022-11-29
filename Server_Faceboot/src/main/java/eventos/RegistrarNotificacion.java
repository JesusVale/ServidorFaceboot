/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorNotificacion;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import entidades.Notificacion;
import peticiones.PeticionNotificacion;
import principales.ClientManager;

/**
 *
 * @author tonyd
 */
public class RegistrarNotificacion implements IEvento{

    private ControladorNotificacion controladorNotificacion;
    private IJsonToObject conversor;
    
    public RegistrarNotificacion() {
        this.conversor = new JsonToObject();
        this.controladorNotificacion = new ControladorNotificacion();
    }

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionNotificacion peticionNotificacion = conversor.convertirNotificacion(peticion);
        Notificacion notificacionRegistrada = controladorNotificacion.registrarNotificacion(peticionNotificacion.getNotificacion());
        PeticionNotificacion respuesta;
        if(notificacionRegistrada != null){
            respuesta = new PeticionNotificacion(Eventos.registrarNotificacion, 200, notificacionRegistrada);
        } else{
            respuesta = new PeticionNotificacion(Eventos.registrarNotificacion, 404, "Notificacion no encontrada");
        }
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
    
}
