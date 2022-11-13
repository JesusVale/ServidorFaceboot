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
    public void ejecutar(Peticion peticion, ClientManager cliente) {
        Publicacion Publicacion = conversor.convertirPublicacion(peticion.getInfo()); //Se convierte JSON a Objeto
        Publicacion PublicacionRegistrado = controladorPublicacion.registrarPublicacion(Publicacion); //Se envia el Publicacion al controlador
        Peticion peticionRespuesta = new Peticion(Eventos.registrarPublicacion, 400, conversor.convertirObjetoString(PublicacionRegistrado));
        cliente.enviarMensaje(conversor.convertirObjetoString(peticion));
    }
    
    
    
}
