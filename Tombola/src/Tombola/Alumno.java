/*
 * Nombre: Alumno.java
 * Descripción: Clase para instancias objetos con nombre, número de lista y participación
 * Versión: 1.0
 */
package Tombola;

public class Alumno {

    String nombre;//Nombre del alumno
    int listNum;//Número de lista
    boolean control;//Participación

    //Constructores//
    /**
     * Constructor con datos
     */
    public Alumno(String nombre, int listNum, boolean control) {
        this.nombre = nombre;
        this.listNum = listNum;
        this.control = control;
    }

    /**
     * Constructor vacío
     */
    public Alumno() {
    }

    //Funciones//

    /**
     * Para retornar el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Para guardar el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Para retornar el número de lista
     */
    public int getListNum() {
        return listNum;
    }

    /**
     * Para guardar el número de lista
     */
    public void setListNum(int listNum) {
        this.listNum = listNum;
    }

    /**
     * Para retornar la participación
     */
    public boolean isControl() {
        return control;
    }

    /**
     * Para guardar la participación
     */
    public void setControl(boolean control) {
        this.control = control;
    }
    
}//Fin clase Alumno
