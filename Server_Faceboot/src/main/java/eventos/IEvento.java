/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventos;

import peticiones.Peticion;
import principales.ClientManager;

/**
 *
 * @author jegav
 */
public interface IEvento {
    public void ejecutar(Peticion peticion, ClientManager cliente);
}
