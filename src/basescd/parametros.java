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
public class parametros {
    
    String nombre;
    String dni;

    public parametros() {
    }
    
    public parametros(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "parametros{" + "nombre=" + nombre + ", dni=" + dni + '}';
    }
    
    
    
}
