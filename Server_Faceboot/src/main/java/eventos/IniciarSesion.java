/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * @author tonyd
 */
public class IniciarSesion implements IEvento{

    private ControladorUsuario controladorUsuario;
    private IJsonToObject conversor;

    public IniciarSesion() {
        this.conversor = new JsonToObject();
        this.controladorUsuario = new ControladorUsuario();
    }
    
    @Override
    public void ejecutar(Peticion peticion, ClientManager cliente) {
            
            Usuario usuario = conversor.convertirUsuario(peticion.getInfo()); //Se convierte JSON a Objeto
            Usuario usuarioRegistrado = controladorUsuario.IniciarSesion(usuario); //Se envia el usuario al controlador
            Peticion peticionRespuesta = new Peticion(Eventos.Login, 200, conversor.convertirObjetoString(usuarioRegistrado));
            cliente.enviarMensaje(conversor.convertirObjetoString(peticion));
            
    }
    
}
