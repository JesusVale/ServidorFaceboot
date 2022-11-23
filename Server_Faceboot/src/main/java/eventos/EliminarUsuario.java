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
public class EliminarUsuario implements IEvento{
    private ControladorUsuario controladorUsuario;
    private IJsonToObject conversor;

    public EliminarUsuario() {
        this.conversor = new JsonToObject();
        this.controladorUsuario = new ControladorUsuario();
    }

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionUsuario peticionUsuario = conversor.convertirPeticionUsuario(peticion);
        Usuario usuarioEliminado = controladorUsuario.eliminarUsuario(peticionUsuario.getUsuario());
        PeticionUsuario respuesta;
        if (usuarioEliminado != null) {
            respuesta = new PeticionUsuario(Eventos.eliminarUsuario, 200, usuarioEliminado);
        } else {
            respuesta = new PeticionUsuario(Eventos.eliminarUsuario, 404, "Usuario no Encontrado");
        }
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
}
