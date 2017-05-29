/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basescd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ped90
 */
public class conecta {

    public static Connection conexion;
   
    ArrayList<parametros>personas = new ArrayList<>();
    
    private String url;
    

    public conecta() {
        url="default.db";
    }

    public conecta(String url) {
        this.url = url;
    }
    
    public void Conexion(){
        try{
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        }  
        try {
         conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
         System.out.println("Conectado");
        }
        catch (SQLException e) {
         System.out.println("Error");
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void cargaArray(){
        
            personas.add(new parametros(
            JOptionPane.showInputDialog("Inserta el Nombre"),
            JOptionPane.showInputDialog("Inserta el DNI")));
            
    }
    
    public void insertarPersonas(){
        
        try {
            PreparedStatement insert = conexion.prepareStatement("Insert into Jugadores(Nombre, DNI) values(?,?)");
            for(int i=0;i<personas.size();i++){
            insert.setString(1,personas.get(i).getNombre());
            insert.setString(2,personas.get(i).getDni());
            insert.execute();
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos en la tabla:"+ex.getMessage());
        }
        
    }
     
}
