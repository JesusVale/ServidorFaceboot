/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorUsuario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionId;
import peticiones.PeticionUsuario;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ConsultarUsuario implements IEvento {

    private ControladorUsuario controladorUsuario;
    private IJsonToObject conversor;

    public ConsultarUsuario() {
        this.conversor = new JsonToObject();
        this.controladorUsuario = new ControladorUsuario();
    }
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionId peticionId = conversor.convertirPeticionId(peticion);
        PeticionUsuario peticionRespuesta = controladorUsuario.consultarUsuarioPorId(peticionId.getId());
        cliente.enviarMensaje(conversor.convertirObjetoString(peticionRespuesta));
    }
    
}
