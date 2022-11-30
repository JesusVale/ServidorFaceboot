/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorHashtag;
import conversors.IJsonToObject;
import conversors.JsonToObject;
import peticiones.PeticionHashtag;
import peticiones.PeticionString;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ConsultarHashtagPorTema implements IEvento {
    
    private IJsonToObject conversor;
    
    private ControladorHashtag controladorHashtag;

    public ConsultarHashtagPorTema() {
        this.conversor = new JsonToObject();
        this.controladorHashtag = new ControladorHashtag();
    }

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionString peticionEnviada = conversor.convertirPeticionString(peticion);
        PeticionHashtag respuesta = controladorHashtag.consultarPorNombre(peticionEnviada.getStr());
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));
    }
    
}
