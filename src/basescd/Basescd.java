/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basescd;

/**
 *
 * @author ped90
 */
public class Basescd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        conecta obc = new conecta();
        obc.Conexion();
        obc.cargaArray();
        obc.insertarJugadores();
    }
    
}
