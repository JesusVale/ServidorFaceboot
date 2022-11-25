/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorUsuario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
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
           PeticionUsuario usuarioLogeado = controladorUsuario.IniciarSesion(peticionUsuario.getUsuario()); //Se envia el usuario al controlador
           if(usuarioLogeado.getStatus() == 200){
               cliente.setId(usuarioLogeado.getUsuario().getId());
               System.out.println(cliente.getId());
           }
           cliente.enviarMensaje(conversor.convertirObjetoString(usuarioLogeado));
    }
    
}
