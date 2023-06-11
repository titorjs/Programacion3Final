package sistema;
import clases.Envio;
import clases.Sucursal;

import java.util.ArrayList;
import java.util.Date;
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

    private SortedSet<Envio> envios;
    /**
     * Siempre debe tener como cédula el valor 00000000
     */
    private Persona admin;

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
        envios = new TreeSet<>();

        /**
         * !!! Valores de prueba
         */

        admin = new Persona("Nombre del administrador","0000000000", "1234567890", "admin", TipoCuenta.ADMINISTRADOR);
        usuarios.add(new Persona("Tito Jaramillo", "2350999039", "+593 99669 3539", "1q2w3e4r",TipoCuenta.ESTIBAJE));
        usuarios.add(new Persona("Maria","1010101010","123456789","enviador",TipoCuenta.USUARIO));
        usuarios.add(new Persona("Pedro","1010101020","123456789","receptor",TipoCuenta.USUARIO));
        //envios.add(new Envio(1,0, "10/20/2023",))
    }

    /**
     * METODOS REFERENTES A MANEJO DE USUARIOS
     */

    /**
     *
     * @param nuevo debe haberse validado previamente la contraseña
     * @return
     */
    public boolean agregarUsuario(Persona nuevo){
        return usuarios.add(nuevo);
    }

    /** Elimina un usuario en base a la cédula */
    public void eliminarUsuario(Persona p){
        usuarios.remove(p);
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

    public SortedSet<Persona> getUsuarios() {
        return usuarios;
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
     * Validar ingreso admin
     * @param clave
     * @return
     */
    public boolean validarAdmin(String clave){
        return admin.validarContrasenia(clave);
    }



    /**
     * MÉTODOS PARA MANEJAR LAS SUCURSALES Y SUS ATRIBUTOS
     */

    /**
     * METODOS REFERENTES A MANEJO DE ENVIOS
     */

    public Envio buscarEnvio(Integer numCodigo){
        int inferior, superior, centro;
        inferior=0;
        superior=envios.size()-1;
        while(inferior<=superior){
            centro=(inferior+superior)/2;
            Envio envio=(Envio) envios.toArray()[centro];
            if(numCodigo==envio.getCodigo())
                return envio;
            if(numCodigo<envio.getCodigo()){
                superior=centro-1;
            }else{
                inferior=centro+1;
            }
        }
        return null;
    }
}
