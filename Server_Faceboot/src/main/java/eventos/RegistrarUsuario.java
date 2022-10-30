/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import conversors.IJsonToObject;
import conversors.JsonToObject;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class RegistrarUsuario implements IEvento {
    
    IJsonToObject convertidorJson = new JsonToObject();
    
    @Override
    public void ejecutar(String[] mensaje, ClientManager cliente) {
        if(cliente.getCodigo().startsWith("11")){ //Si lo envia una vista
            // Evento seguimiento-referencia a vista
            cliente.setSeguimiento(); 
            
            String[] mensajes = {mensaje[0], cliente.getSeguimiento()+"", mensaje[1]};
            cliente.enviarMensaje(ConstantesCodigosClientes.controladorUsuario, convertidorJson.convertirObjetoString(mensajes)); //Le envia al controlador el usuario para que lo agregue al modelo
        } else{ //Si lo envia el controlador
            //Envia evento seguimiento Resultado
            double seguimiento = Double.parseDouble(mensaje[1]);
            String[] mensajes = {Eventos.registrarUsuario, mensaje[2]};
            cliente.enviarMensaje(seguimiento, convertidorJson.convertirObjetoString(mensajes));
        }
    }
    
}
