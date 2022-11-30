/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import controladores.ControladorHashtag;
import conversors.IJsonToObject;
import entidades.Hashtag;
import java.util.List;
import peticiones.PeticionHashtags;
import principales.ClientManager;

/**
 *
 * @author tonyd
 */
public class RegistrarHashtags implements IEvento {

    private ControladorHashtag controladorHashtag;
    private IJsonToObject conversor;
    
    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        PeticionHashtags peticionHashtags = conversor.convertirPeticionHashtags(peticion);
        List<Hashtag> hashtagsRegistrado = controladorHashtag.registrarHashtags(peticionHashtags.getHashtags());
        PeticionHashtags respuesta;
        if(hashtagsRegistrado != null){
            respuesta = new PeticionHashtags(Eventos.registrarHashtags, 200, hashtagsRegistrado);
        } else{
            respuesta = new PeticionHashtags(Eventos.registrarHashtags, 404, "hashtags no encontrados");
        }
        cliente.enviarMensaje(conversor.convertirObjetoString(respuesta));        
    }

}
