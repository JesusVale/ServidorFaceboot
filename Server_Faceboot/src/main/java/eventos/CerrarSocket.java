/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventos;

import principales.ClientManager;

/**
 *
 * @author jegav
 */
public class CerrarSocket implements IEvento {

    @Override
    public void ejecutar(String peticion, ClientManager cliente) {
        
        cliente.eliminarCliente();
        cliente.cerrarTodo();
    }
    
}
