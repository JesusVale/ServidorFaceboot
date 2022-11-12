/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import peticiones.Peticion;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class SuscribirseRegistrarPublicacion implements IEvento {

    @Override
    public void ejecutar(Peticion peticion, ClientManager cliente) {
        ManejadorListaEventos.getInstance().agregarRegistrarPublicacionListener(cliente);
    }
    
    
    
}
