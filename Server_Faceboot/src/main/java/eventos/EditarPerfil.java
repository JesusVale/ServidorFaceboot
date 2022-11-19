/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorUsuario;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import entidades.Usuario;
import peticiones.PeticionUsuario;
import principales.ClientManager;

/**
 *
 * @author tonyd
 */
public class EditarPerfil implements IEvento {

    private ControladorUsuario controladorUsuario;
    private IJsonToObject conversor;

    public EditarPerfil() {
        this.conversor = new JsonToObject();
        this.controladorUsuario = new ControladorUsuario();
    }

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionUsuario peticionUsuario = conversor.convertirPeticionUsuario(peticion);
        Usuario usuarioActualizado = controladorUsuario.editarPerfil(peticionUsuario.getUsuario());
        PeticionUsuario respuesta;
        if (usuarioActualizado != null) {
            respuesta = new PeticionUsuario(Eventos.editarPerfil, 200, usuarioActualizado);
        } else {
            respuesta = new PeticionUsuario(Eventos.editarPerfil, 404, "Usuario no Encontrado");
        }
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
}
