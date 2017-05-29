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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ped90
 */
public class conecta {

    public static Connection conexion;
    ResultSet result;
   
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
    
    public void visualizarPersonas(){
        
        try {
            PreparedStatement ver = conexion.prepareStatement("Select * from Jugadores");
           result = ver.executeQuery();
           while(result.next()){
               System.out.println("Nombre  "+":"+ result.getString("Nombre"));
               System.out.println("dni"+" :"+result.getString("DNI"));
               System.out.println();
           }
        } catch (SQLException ex) {
            System.out.println("Error al leer  la Base de Datos: "+ex.getMessage());
        }
        
    }
    
    public void borrarPersonas(Integer reg){
        
        try{ 
            Statement st = conexion.createStatement(); 
            st.execute("delete from Jugadores where dni="+reg.toString()); 
            System.out.println("Fila borrada con éxito"); 
        }catch(SQLException ex){ 
            System.out.println("Error al borrar la fila, compruebe que ha introducido bien el DNI: "+ex.getMessage()); 
        }
        
    }
    
    public void actualizarPersonas(){ 
        Integer reg=Integer.parseInt(JOptionPane.showInputDialog("Inserte el número del DNI para actualizar la fila correspondiente:")); 
        String nom=JOptionPane.showInputDialog("Inserte el nombre a actualizar:"); 
        String dni=JOptionPane.showInputDialog("Inserte el DNI a actualizar:"); 
       try{ 
            PreparedStatement actualiza = conexion.prepareStatement("update jugadores set nombre='"+nom+"',dni='"+dni+"'where dni="+reg.toString());
                actualiza.execute();
                System.out.println("Registro actualizado"); 
        }catch(SQLException ex){ 
            System.out.println("Error al actualizar el registro, verifique que ha introducido bien los datos a actualizar: "+ex.getMessage());
        }
       
    }
    
    public void cerrarBasePersonas(){
        
        try {
                conexion.close();
                System.out.println("Seguridad: Base de datos cerrada con éxito.");
            } catch (SQLException ex) {
                Logger.getLogger(Basescd.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     
}
