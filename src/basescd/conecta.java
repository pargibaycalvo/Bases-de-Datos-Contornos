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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Programa para añadir datos a una base de datos externa.
 * @author ped90
 */
public class conecta {
    public static Connection conexion;
    ResultSet result;
   
    ArrayList<parametros>personas = new ArrayList<>();
    
    private String url;
    
    /**
     * Ruta de acceso al archivo de base de datos.
     */
    public conecta() {
        url="default.db";
    }

    public conecta(String url) {
        this.url = url;
    }
    /**
     * Conexion al fichero que es el que tiene la base de datos creada en la carpeta del proyecto.
     */
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
    /**
     * Mensajes para añadir los datos necesarios.
     */
    public void cargaArray(){
        
            personas.add(new parametros(
            JOptionPane.showInputDialog("Inserta el Nombre"),
            JOptionPane.showInputDialog("Inserta el DNI")));
    }
    /**
     * Insertar los datos que le añadimos anteriormente a la nuestra tabla.
     */
    public void insertarPersonas(){
        
        try {
            PreparedStatement insert = conexion.prepareStatement("Insert into Jugadores(Nombre, DNI) values(?,?)");
            for(int i=0;i<personas.size();i++){
            insert.setString(1,personas.get(i).getNombre());
            insert.setString(2,personas.get(i).getDni());
            insert.execute();
            int count=insert.getUpdateCount();
                System.out.println(count+" fila insertada");
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar los datos en la tabla:"+ex.getMessage());
        }
        
    }
    /**
     * Visualiza los datos de la tabla.
     */
    public void visualizarPersonas(){
        
        try {
           PreparedStatement ver = conexion.prepareStatement("Select * from Jugadores");
           result = ver.executeQuery();
           while(result.next()){
               System.out.println("Nombre  "+":"+ result.getString("Nombre"));
               System.out.println("DNI"+" :"+result.getString("DNI"));   
           }
        } catch (SQLException ex) {
            System.out.println("Error al leer la Base de Datos: "+ex.getMessage());
        }
        
    }
    /**
     * Borrar los datos de la tabla mediante el DNI.
     * Al introducir el DNI se borra esa linea.
     * @param reg 
     */
    public void borrarPersonas(Integer reg){
        
        try{ 
            PreparedStatement borra = conexion.prepareStatement("delete from Jugadores where dni="+reg.toString());
            borra.executeUpdate();
            int count=borra.getUpdateCount();
            System.out.println(count+" fila borrada con éxito"); 
        }catch(SQLException ex){ 
            System.out.println("Error al borrar la fila, compruebe que ha introducido bien el DNI: "+ex.getMessage()); 
        }
        
    }
    /**
     * Actualiza los datos de la fila que queramos mediante el DNI.
     * @param reg
     * @param nom
     * @param dni 
     */
    public void actualizarPersonas(Integer reg, String nom, String dni){ 
        
       try{ 
            PreparedStatement actualiza = conexion.prepareStatement("update jugadores set nombre='"+nom+"',dni='"+dni+"'where dni="+reg.toString());
                actualiza.execute();
                int count=actualiza.executeUpdate();
                System.out.println("Registro actualizado ="+count); 
        }catch(SQLException ex){ 
            System.out.println("Error al actualizar el registro, verifique que ha introducido bien los datos a actualizar: "+ex.getMessage());
        }
       
    }
    /**
     * Elimina todas las filas de la tabla, dejando así la tabla en blanco.
     */
    public void formatearTabla(){
    
        try {
        PreparedStatement borracom = conexion.prepareStatement("delete from jugadores");
        borracom.execute();
        System.out.println("Tabla formateada");

        } catch(SQLException ex){ 
            System.out.println("Error al formatear la tabla: "+ex.getMessage());
        }
    
       }
    /**
     * Cierra la base con seguridad para evitar daños en nuestros datos de la tabla.
     */
    public void cerrarBasePersonas(){
        
        try {
                conexion.close();
                System.out.println("Seguridad: Base de datos cerrada con éxito.");
            } catch (SQLException ex) {
                Logger.getLogger(Basescd.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     
}
