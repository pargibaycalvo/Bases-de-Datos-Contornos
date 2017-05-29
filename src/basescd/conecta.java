/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basescd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ped90
 */
public class conecta {
    
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
         Connection conexion = DriverManager.getConnection("jdbc:sqlite:"+url);
         System.out.println("Esto funciona madafakers");
        }
        catch (SQLException e) {
         System.out.println("Buuuu");
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
