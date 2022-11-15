/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.Peticion;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class RegistrarComentario implements IEvento {
    private IJsonToObject conversor = new JsonToObject();
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
//       String peticionStr = conversor.convertirObjetoString(new Peticion(Eventos.registrarComentario, 200, peticion.getInfo()));
//       cliente.enviarMensaje(peticionStr);
    }
    
}
