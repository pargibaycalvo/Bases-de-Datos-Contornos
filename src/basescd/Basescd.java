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
     * Menú para usar la base de datos cómodamente.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        conecta obc = new conecta();
        
        int opcion;
         do{
         opcion= Integer.parseInt(JOptionPane.showInputDialog("1) Conexión de la Base de Datos. \n2) Insertar Jugador nuevo. \n3) Visualizar Jugadores."
                                                                                                 + " \n4) Borrar Jugador.  \n5) Actualizar Jugador.  \n6) Formatear Tabla. \n7) Cerrar Base de datos. \n0) Salir del programa."));
         switch(opcion){
            case 1:
                obc.Conexion();
                break;
            case 2:
               obc.cargaArray();
               obc.insertarPersonas();
                break;
            case 3:
                obc.visualizarPersonas();
                break;
            case 4:
                obc.borrarPersonas(Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para borrar la fila correspondiente:")));
                break;
            case 5:
                obc.actualizarPersonas(Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para actualizar la fila correspondiente:")),
               JOptionPane.showInputDialog("Inserte el nombre a actualizar:"),
               JOptionPane.showInputDialog("Inserte el DNI a actualizar:"));
                break;
            case 6:
                obc.formatearTabla();
                break;
            case 7:
                obc.cerrarBasePersonas();
                break;
            case 0:
                JOptionPane.showMessageDialog(null,"Pulse para salir del programa");
                System.exit(0); 
                break;
            default:
                JOptionPane.showMessageDialog(null,"Error");
        }
    }while(opcion!=0);
    }
        
    
}
