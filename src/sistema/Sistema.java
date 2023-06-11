package sistema;
import clases.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Clase para el manejo de las funcionalidades del sistema
 */
public class Sistema {
    private ArrayList<Sucursal> sucursales;
    private SortedSet<Persona> usuarios;
    private ArrayList<Persona> empleadosQS;
    private SortedSet<Envio> envios;
    private SortedSet<Camion> camiones;
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
        empleadosQS=new ArrayList<>();

        /**
         * !!! Valores de prueba
         */
        Date fechaActual=new Date();

        admin = new Persona("Nombre del administrador","0000000000", "1234567890", "admin", TipoCuenta.ADMINISTRADOR);
        Persona maria =new Persona("Maria","1010101010","123456789","enviador",TipoCuenta.USUARIO);
        Persona pedro = new Persona("Pedro","1010101020","123456789","receptor",TipoCuenta.USUARIO);
        Persona carlos = new Persona("Carlos","1010101030","123456789","conductor",TipoCuenta.CONDUCTOR);

        Paquete p1 = new Paquete(new Dimension(10,10,10,10),"Paquete Prueba");
        usuarios.add(new Persona("Tito Jaramillo", "2350999039", "0996693539", "1q2w3e4r",TipoCuenta.ESTIBAJE));
        usuarios.add(maria);
        usuarios.add(pedro);
        usuarios.add(carlos);
        empleadosQS.add(buscarUsuarioCedula("2350999039"));
        Sucursal sQuitoS = new Sucursal(new Direccion(5,5),envios,Sucursales.UIO_S,empleadosQS);
        Sucursal sGuayaquil = new Sucursal(new Direccion(10,10),envios,Sucursales.GYE,empleadosQS);
        Sucursal sQuitoN= new Sucursal(new Direccion(6,6),envios,Sucursales.UIO_N,empleadosQS);
        Sucursal sStoDomingo = new Sucursal(new Direccion(15,15),envios,Sucursales.STO_DGO,empleadosQS);
        envios.add(new Envio(1,0, fechaActual,maria,pedro,p1,sQuitoS,sQuitoS,new Direccion(5,5)));
        Camion camion = new Camion(1,envios,carlos);
        sucursales.add(sQuitoN);
        sucursales.add(sQuitoS);
        sucursales.add(sGuayaquil);
        sucursales.add(sStoDomingo);

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
     * METODOS REFERENTES A MANEJO DE ENVIOS Y SUCURSALES
     */

    /**
     * Metodo busqueda binaria para encontrar un envio mediante el codigo
     * @param numCodigo
     * @return
     */
    public Envio buscarEnvio(Integer numCodigo){
        int inferior, superior, centro;
        inferior=0;
        superior=envios.size()-1;
        while(inferior<=superior){
            centro=(inferior+superior)/2;
            Envio envio=(Envio) envios.toArray()[centro];
            if(numCodigo==envio.getId())
                return envio;
            if(numCodigo<envio.getId()){
                superior=centro-1;
            }else{
                inferior=centro+1;
            }
        }
        return null;
    }

    /**
     * Metodo para saber en que sucursal esta un envio mediante el codigo
     * @param numCodigo Codigo del envio que se quiere encontrar
     * @return retorna la sucursal en la que se encuentra si es que encuentra y null
     * si el envio no esta en ningua sucursal
     */
    public Sucursal buscarSucursalePaquete(int numCodigo){
        for (Sucursal s:sucursales){
            if (buscarEnvio(numCodigo)!=null){
                return s;
            }
        }
        return null;
    }

    /**
     * Metodo para obtener la lista de sucursales
     * @return sucursales
     */
    public ArrayList<Sucursal> getSucursales(){
        return sucursales;
    }

    /**
     * Metodo para agregar envio y agregar a la sucursal comparando la zona
     * @param e
     * @param nombreS
     */
    public void agregarEnvio(Envio e,String nombreS){
        envios.add(e);
        for (Sucursal s: sucursales){
            if (s.getZona().equals(Sucursales.valueOf(nombreS))){
                s.getInventario().add(e);
                return;
            }
        }
    }

    /**
     * Metodo para remover el envio de la lista y removerlo de la lista de la
     * sucursal correspondiente
     * @param e significa el apuntador de envio a modificar bobo quien lo lea
     */
    public void removerEnvio(Envio e){
        envios.remove(e);
        for (Sucursal s: sucursales){
            if (s.getZona().equals(buscarSucursalePaquete(e.getId()))){
                s.getInventario().remove(e);
                return;
            }
        }
    }

    /**
     * Metodo para cambiar el estado del envio que comprueba el String del combobox para poder
     * asignar un numero entero asignado anteriormente
     *      *  0. Se debe retirar en el domicilio del cliente
     *      *  1. Se recibió en oficina
     *      *  2. Enviandose a la sucursal correcta
     *      *  3. En sucursal correcta
     *      *  4. En curso a domicilio(En marcha)
     *      *  5. Entregado
     * @param e El envio que se va a cambiar el estado
     * @param estado El String del comboBox para poder asignar
     */
    public void cambiarEstado(Envio e,String estado){
        int cambio;
        if (estado.compareToIgnoreCase("Enviandose a la sucursal correcta")==0){
            cambio=2;
        } else if (estado.compareToIgnoreCase("En sucursal correcta")==0) {
            cambio=3;
        } else if (estado.compareToIgnoreCase("En curso a domicilio")==0) {
            cambio=4;
        } else  {
            cambio=5;
        }
        e.setEstado(cambio);
    }

    /**
     * Metodo para Modificar la persona receptora del envio
     * @param p Persona ya existente que se busca con el metodo de buscar usuario por cedula
     *          en caso de no existir se crea una nueva persona, pero esto se realiza en
     *          interfaz.
     * @param e El envio al cual se va a modificar
     */
    public void modificarPersona(Persona p, Envio e){
        e.setReceptor(p);
    }

    /**
     * Metodo para modificar la direccion del envio
     * @param d Direccion nueva que se ingresa
     * @param e Envio al cual se a modificar
     */
    public void modificarDireccion(Direccion d,Envio e){
        e.setDireccionEntrega(d);
    }

    /**
     * Metodo para modificar el paquete del envio
     * @param p Paquete nuevo que se ingresa
     * @param e Envio al cual se va a modificar
     */
    public void modificarPaquete(Paquete p,Envio e){
        e.setPaquete(p);
    }

    /**
     * Metodo para cambiar la sucursal de entrega, ademas cambia el estado dependiendo de lo siguiente:
     * Primero se utiliza el metodo buscarSucursal que devuelve la sucursal donde se encuentra el envio
     * luego se compara:
     * Si el envio esta en la sucursal de entrega entonces el estado cambia a 3(En sucursal correcta) y
     * si no esta cambia el estado a 2(Enviandose a la sucursal correcta)
     * @param s La sucursal que se quiere cambiar
     * @param e El envio al cual se va a modificar la sucursal
     */
    public void cambiarSucursalEntrega(Sucursal s,Envio e){
        e.setSucursalEntrega(s);
        if (buscarSucursalePaquete(e.getId())==e.getSucursalEntrega()){
            e.setEstado(3);
        } else  {
            e.setEstado(2);
        }
    }


    public void generarListaEnviosSucursal(int idCamion){

    }
    public void generarListaEnviosEntrega(int idCamion){

    }
    /**
     * !!!TEMPORAL
     * METODOS PARA MANEJAR CAMIONES
     */

    /**
     * Metodo para buscar camion en especifico
     * @param numCodigo Codigo para encontrar el camion deseado
     * @return Un camion y null si no lo encuentra
     */
    public Camion buscarCamion(int numCodigo){
        int inferior, superior, centro;
        inferior=0;
        superior=camiones.size()-1;
        while(inferior<=superior){
            centro=(inferior+superior)/2;
            Camion camion=(Camion) camiones.toArray()[centro];
            if(numCodigo==camion.getId())
                return camion;
            if(numCodigo<camion.getId()){
                superior=centro-1;
            }else{
                inferior=centro+1;
            }
        }
        return null;
    }
    public void agregarEnvioCamion(){

    }

}
