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
import peticiones.PeticionUsuario;
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
    public void ejecutar(String peticion, ClientManager cliente) {
            
            PeticionUsuario peticionUsuario = conversor.convertirPeticionUsuario(peticion); //Se convierte JSON a Objeto
            Usuario usuarioRegistrado = controladorUsuario.IniciarSesion(peticionUsuario.getUsuario()); //Se envia el usuario al controlador
            if(usuarioRegistrado != null){
                Peticion peticionRespuesta = new PeticionUsuario(Eventos.Login, 200, usuarioRegistrado);
                cliente.enviarMensaje(conversor.convertirObjetoString(peticionRespuesta));
            } else{
                PeticionUsuario peticionRespuesta = new PeticionUsuario(Eventos.Login, 404, "No se encontró al usuario");
                cliente.enviarMensaje(conversor.convertirObjetoString(peticionRespuesta));
            }
    }
    
}
