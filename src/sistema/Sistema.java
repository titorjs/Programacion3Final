package sistema;
import clases.Sucursal;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import clases.Persona;

/**
 * Clase para el manejo de las funcionalidades del sistema
 */
public class Sistema {
    private ArrayList<Sucursal> sucursales;
    private SortedSet<Persona> usuarios;

    /**
     * En esta clase se manejará todas las funciones necesarias para la interfaz gráfica
     */
    public Sistema(){
        /**
         * Aquí se deben de inicializar los valores de la data, sea desde una base de datos
         * o simplemente en 0
         */
        sucursales = new ArrayList<>();
        usuarios = new TreeSet<>();
    }

    /**
     * METODOS REFERENTES A MANEJO DE USUARIOS
     */
}
