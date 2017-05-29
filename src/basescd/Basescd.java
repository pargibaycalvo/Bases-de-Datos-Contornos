/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basescd;

import javax.swing.JOptionPane;

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
        obc.insertarPersonas();
        obc.visualizarPersonas();
        obc.borrarPersonas(Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para borrar la fila correspondiente:")));
        obc.actualizarPersonas(Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para actualizar la fila correspondiente:")),
               JOptionPane.showInputDialog("Inserte el nombre a actualizar:"), JOptionPane.showInputDialog("Inserte el DNI a actualizar:"));
        obc.cerrarBasePersonas();
    }
    
}
