/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorUsuario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionString;
import peticiones.PeticionUsuario;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ConsultarUsuarioPorNombre implements IEvento {
    private ControladorUsuario controladorUsuario;
    private IJsonToObject conversor;

    public ConsultarUsuarioPorNombre() {
        this.conversor = new JsonToObject();
        this.controladorUsuario = new ControladorUsuario();
    }

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionString peticionNombre = conversor.convertirPeticionString(peticion);
        PeticionUsuario peticionUsuario = controladorUsuario.consultarUsuarioPorNombre(peticionNombre.getStr());
        cliente.enviarMensaje(conversor.convertirObjetoString(peticionUsuario));
    }
}
