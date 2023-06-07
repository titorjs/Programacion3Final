package sistema;
import clases.Sucursal;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import clases.Persona;
import clases.TipoCuenta;

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

    public boolean agregarUsuario(Persona nuevo){
        return usuarios.add(nuevo);
    }

    /**
     * Recorre los usuarios en el sistema y devuelve aquellos que son de cierto tipo específico,
     * puede ser un solo tipo o varios
     * @param tipo los tipos de usuarios que están buscando, puede ser uno o más tipos
     * @return SortedSet de los usuarios que están dentro de la lista de tipos pasada por parámtro
     */
    public SortedSet<Persona> buscarUsuarioTipo(ArrayList<TipoCuenta> tipo){
        SortedSet<Persona> resultado = new TreeSet<>();

        for (Persona p: usuarios){
            if (tipo.contains(p.getTipo())){
                resultado.add(p);
            }
        }

        return resultado;
    }

    /**
     * Busqueda binaria de unusuario por su cédula
     * @param cedula cédula a buscar
     * @return Un objeto de tipo Persona de encontrarse o null si no existe
     */
    public Persona buscarUsuarioCedula(String cedula){
        int inicio = 0, fin = usuarios.size() - 1;
        int medio = (inicio + fin)/2;
        Persona aux;
        int comparacion = 0;

        /**
         * Busqueda binaria usando el método estático de comparación de cédula de la clase Persona
         */
        while (inicio <= fin){

            medio = (inicio + fin)/2;
            aux = (Persona)(usuarios.stream().toArray()[medio]);
            comparacion = Persona.compararCedula(cedula, aux.getCedula());

            if (comparacion == 0) {
                return aux;
            }
            if(comparacion > 0){
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }

        return null;
    }

    /**
     * Método para buscar por nombre, si contiene un nombre ignorando minúsculas y mayusculas,
     * por ejemplo si se busca "to", la busque podría mostrar como resultado nombres como:
     * "Tito" y "Tony"
     * @param nombre nombre a buscar
     * @return SortedSet de Persona con los matchs encontrados, o uno vació de no haber
     */
    public SortedSet<Persona> buscarUsuarioNombre(String nombre){
        SortedSet<Persona> resultado = new TreeSet<>();

        /**
         * Verifica que el nombre contenga la palabra buscada, ignorando minúculas y mayúsculas
         */
        for (Persona p: usuarios){
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                resultado.add(p);
            }
        }

        return resultado;
    }

    /**
     * MÉTODOS PARA MANEJAR LAS SUCURSALES Y SUS ATRIBUTOS
     */
}
