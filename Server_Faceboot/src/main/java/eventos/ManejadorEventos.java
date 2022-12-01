/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class ManejadorEventos {
    
    private static Map<String, IEvento> eventos = Map.ofEntries(
            Map.entry(Eventos.Login, new IniciarSesion()),
            Map.entry(Eventos.registrarUsuario, new RegistrarUsuario()),
            Map.entry(Eventos.registrarPublicacion, new RegistrarPublicacion()),
            Map.entry(Eventos.registrarComentario, new RegistrarComentario()),
            Map.entry(Eventos.consultarPublicaciones, new ConsultarPublicaciones()),            
            Map.entry(Eventos.consultarComentarios, new ConsultarComentarios()),
            Map.entry(Eventos.consultarUsuarioPorId, new ConsultarUsuario()),
            Map.entry(Eventos.eliminarUsuario, new EliminarUsuario()),
            Map.entry(Eventos.editarPerfil, new EditarPerfil()),
            Map.entry(Eventos.eliminarPublicacion, new EliminarPublicacion()),
            Map.entry(Eventos.iniciarSesionFacebook, new InicioSesionFacebook()),
            Map.entry(Eventos.registrarHashtags, new RegistrarHashtags()),
            Map.entry(Eventos.registrarNotificacion, new RegistrarNotificacion()),
            Map.entry(Eventos.consultarUsuarioPorNombre, new ConsultarUsuarioPorNombre()),
            Map.entry(Eventos.consultarHashtagPorTema, new ConsultarHashtagPorTema()),
            Map.entry(Eventos.consultarPublicacionesPorHashtag, new ConsultarPublicacionesPorHashtag()),
            Map.entry(Eventos.consultarNotificacionesPorRemitente, new ConsultarNotificacionesPorRemitente()),
            Map.entry(Eventos.eliminarComentario, new EliminarComentario())
    );
    
    public static IEvento get(String evento){
        return eventos.get(evento);
    }
    
    public static void ejecutarEvento(String peticion, ClientManager cliente){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode peticionJson = mapper.readTree(peticion);
            String evento = peticionJson.get("evento").asText();
            eventos.get(evento).ejecutar(peticion, cliente);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ManejadorEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
