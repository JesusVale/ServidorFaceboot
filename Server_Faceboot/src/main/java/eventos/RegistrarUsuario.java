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
import peticiones.PeticionUsuario;
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
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionUsuario peticionUsuario = conversor.convertirPeticionUsuario(peticion);
        Usuario usuarioRegistrado = controladorUsuario.registrarUsuario(peticionUsuario.getUsuario());
        PeticionUsuario respuesta;
        if(usuarioRegistrado != null){
            respuesta = new PeticionUsuario(Eventos.registrarUsuario, 200, usuarioRegistrado);
        } else{
            respuesta = new PeticionUsuario(Eventos.registrarUsuario, 404, "Usuario no Encontrado");
        }
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
//        Usuario usuario = conversor.convertirUsuario(peticion.getInfo()); //Se convierte JSON a Objeto
//        Usuario usuarioRegistrado = controladorUsuario.registrarUsuario(usuario); //Se envia el usuario al controlador
//        Peticion peticionRespuesta = new Peticion(Eventos.registrarUsuario, 200, conversor.convertirObjetoString(usuarioRegistrado));
//        cliente.enviarMensaje(conversor.convertirObjetoString(peticion));
    }
    
}
