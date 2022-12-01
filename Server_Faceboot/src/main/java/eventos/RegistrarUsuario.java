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
        PeticionUsuario respuesta = controladorUsuario.registrarUsuario(peticionUsuario.getUsuario());
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
    
}
