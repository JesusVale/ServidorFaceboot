/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import java.util.HashMap;
import java.util.Map;

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
            Map.entry(Eventos.suscribirseRegistrarPublicacion, new SuscribirseRegistrarPublicacion()),
            Map.entry(Eventos.suscribirseRegistrarComentario, new SuscribirseRegistrarComentario())
    );
    
    public static IEvento get(String evento){
        return eventos.get(evento);
    }
    
}
