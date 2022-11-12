/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import controladores.ControladorUsuario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import entidades.Usuario;
import peticiones.Peticion;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class RegistrarUsuario implements IEvento {
    
    private ControladorUsuario controladorUsuario;
    private IJsonToObject conversor;

    public RegistrarUsuario() {
        this.conversor = new JsonToObject();
        this.controladorUsuario = new ControladorUsuario();
    }
    
    @Override
    public void ejecutar(Peticion peticion, ClientManager cliente) {
            
            Usuario usuario = conversor.convertirUsuario(peticion.getInfo()); //Se convierte JSON a Objeto
            Usuario usuarioRegistrado = controladorUsuario.registrarUsuario(usuario); //Se envia el usuario al controlador
            Peticion peticionRespuesta = new Peticion(Eventos.registrarUsuario, 200, conversor.convertirObjetoString(usuarioRegistrado));
            cliente.enviarMensaje(conversor.convertirObjetoString(peticion));
            
    }
    
}
