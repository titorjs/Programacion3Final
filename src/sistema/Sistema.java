package sistema;
import clases.*;

import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Clase para el manejo de las funcionalidades del sistema
 */
public class Sistema {
    private ArrayList<Sucursal> sucursales;
    private SortedSet<Persona> usuarios;
    private ArrayList<Persona> empleadosQS;
    private SortedSet<Envio> envios;
    private SortedSet<Camion> camiones;
    private SortedSet<Mensaje> mensajes;
    private ArrayList<ReporteServicios> reporteServicios;
    private ArrayList<ReporteVia> reporteVias;

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
        envios = new TreeSet<>();
        camiones = new TreeSet<>();
        mensajes = new TreeSet<>();
        reporteServicios = new ArrayList<>();
        reporteVias = new ArrayList<>();

        /**
         * !!! Valores de prueba
         */


        //Usuarios
        admin = new Persona("Nombre del administrador","0000000000", "1234567890", "admin", TipoCuenta.ADMINISTRADOR);
        Persona maria =new Persona("Maria","1010101010","123456789","enviador",TipoCuenta.USUARIO);
        Persona pedro = new Persona("Pedro","1010101028","123456789","receptor",TipoCuenta.USUARIO);
        Persona carlos = new Persona("Carlos","1010101036","123456789","conductor",TipoCuenta.CONDUCTOR);
        Persona pepe = new Persona("José","1010101044","123456789","conductor",TipoCuenta.EJECUTIVO);
        Persona tito = new Persona("Tito Jaramillo", "2350999039", "0996693539", "1q2w3e4r",TipoCuenta.ESTIBAJE);
        Persona tony = new Persona("Tony Chen","1724375371","0958665421","tonychen777",TipoCuenta.ESTIBAJE);
        Persona jaime = new Persona("Jaime", "1010101051", "0996693539", "1q2w3e4r",TipoCuenta.REPARTIDOR);

        usuarios.add(tito);
        usuarios.add(maria);
        usuarios.add(pedro);
        usuarios.add(carlos);
        usuarios.add(pepe);
        usuarios.add(jaime);
        usuarios.add(tony);

        empleadosQS.add(tito);
        empleadosQS.add(maria);
        empleadosQS.add(pedro);
        empleadosQS.add(carlos);
        empleadosQS.add(pepe);
        empleadosQS.add(jaime);
        empleadosQS.add(tony);

        //Sucursales
        Sucursal sQuitoS = new Sucursal(new Direccion(5,5),new TreeSet<>(),Sucursales.UIO_S,empleadosQS);
        Sucursal sGuayaquil = new Sucursal(new Direccion(10,10),new TreeSet<>(),Sucursales.GYE,empleadosQS);
        Sucursal sQuitoN= new Sucursal(new Direccion(6,6),new TreeSet<>(),Sucursales.UIO_N,empleadosQS);
        Sucursal sStoDomingo = new Sucursal(new Direccion(15,15),new TreeSet<>(),Sucursales.STO_DGO,empleadosQS);

        sucursales.add(sQuitoN);
        sucursales.add(sQuitoS);
        sucursales.add(sGuayaquil);
        sucursales.add(sStoDomingo);

        //Envios
        Paquete p1 = new Paquete(new Dimension(10,10,10,10),"Paquete Prueba");

        Envio e1 = new Envio(2461,3, LocalDateTime.now(),maria,pedro,p1,sQuitoS,sQuitoS,new Direccion(5,5));
        Envio e2 = new Envio(2462,0, LocalDateTime.of(2004, 12, 1, 12, 35, 34,4),maria,pedro,p1,sQuitoS,sQuitoN,new Direccion(5,5));

        envios.add(e1);
        envios.add(e2);

        //Camiones
        Camion camion = new Camion(1,new TreeSet(), carlos);
        Camion camion2 = new Camion(2,new TreeSet(), jaime);

        camiones.add(camion2);
        camiones.add(camion);

        //Mensajes

        LocalDateTime l1 = LocalDateTime.now();
        LocalDateTime l2 = LocalDateTime.of(2021, 6, 11, 19, 15);
        LocalDateTime l3 = LocalDateTime.of(2022, 6, 11, 19, 15);
        LocalDateTime l4 = LocalDateTime.of(2024, 6, 11, 19, 15);
        LocalDateTime l5 = LocalDateTime.of(2025, 6, 11, 19, 15);

        Mensaje m1 = new Mensaje(maria, pedro, "Mensaje de maria a pedro", l1);
        Mensaje m2 = new Mensaje(jaime, carlos, "Jaime-Carlos", l2);
        Mensaje m3 = new Mensaje(pedro, maria, "pedro-maria", l3);
        Mensaje m4 = new Mensaje(tito, pepe, "tito-pepe", l4);
        Mensaje m5 = new Mensaje(pepe, carlos, "pepe-carlos", l5);

        mensajes.add(m1);
        mensajes.add(m2);
        mensajes.add(m3);
        mensajes.add(m4);
        mensajes.add(m5);

        //Reportes Via
        ReporteVia rv1 = new ReporteVia(jaime,  "Reporte via jaime", l1, new Direccion(5, 5));
        ReporteVia rv2 = new ReporteVia(jaime,  "Reporte via jaime", l1, new Direccion(4, 4));
        ReporteVia rv3 = new ReporteVia(jaime,  "Reporte via jaime", l1, new Direccion(6, 6));

        reporteVias.add(rv1);
        reporteVias.add(rv2);
        reporteVias.add(rv3);

        //Reportes Servicios
        ReporteServicios rs1 = new ReporteServicios(maria, jaime, "El paquete llegó en mal estado", l2, e1);
        ReporteServicios rs2 = new ReporteServicios(maria, jaime, "El señor es un poco grosero", l3, e2);

        reporteServicios.add(rs1);
        reporteServicios.add(rs2);

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

    /** Elimina un interfaces.usuario en base a la cédula */
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
     * METODOS REFERENTES A MANEJO DE MENSAJES Y REPORTES
     */


    public SortedSet<Mensaje> getMensajes() {
        return mensajes;
    }

    public ArrayList<ReporteServicios> getReporteServicios() {
        return reporteServicios;
    }

    public ArrayList<ReporteVia> getReporteVias() {
        return reporteVias;
    }

    public SortedSet<Mensaje> buscarMensajesEmisor(String cedulaEmisor){
        SortedSet<Mensaje> busqueda = new TreeSet<>();
        Persona emisor = buscarUsuarioCedula(cedulaEmisor);
        for (Mensaje m: mensajes){
            if (m.getEmisor().equals(emisor)){
                busqueda.add(m);
            }
        }
        return busqueda;
    }

    public SortedSet<Mensaje> buscarMensajesContenido(String buscado){
        SortedSet<Mensaje> busqueda = new TreeSet<>();
        for (Mensaje m: mensajes){
            if (m.getMensaje().contains(buscado)){
                busqueda.add(m);
            }
        }
        return busqueda;
    }

    public ArrayList<ReporteVia> buscarReporteVia(String cedula){
        ArrayList<ReporteVia> buscado = new ArrayList<>();
        Persona p = buscarUsuarioCedula(cedula);
        for (ReporteVia r: reporteVias){
            if (r.getEmisor().equals(p)){
                buscado.add(r);
            }
        }
        return buscado;
    }

    public ArrayList<ReporteServicios> buscarReporteServicioEmisor(String cedula){
        ArrayList<ReporteServicios> buscado = new ArrayList<>();
        Persona p = buscarUsuarioCedula(cedula);
        for (ReporteServicios r: reporteServicios){
            if (r.getEmisor().equals(p)){
                buscado.add(r);
            }
        }
        return buscado;
    }

    public ArrayList<ReporteServicios> buscarReporteServicioReceptor(String cedula){
        ArrayList<ReporteServicios> buscado = new ArrayList<>();
        Persona p = buscarUsuarioCedula(cedula);
        for (ReporteServicios r: reporteServicios){
            if (r.getReceptor().equals(p)){
                buscado.add(r);
            }
        }
        return buscado;
    }

    public SortedSet<Mensaje> buscarMensajesReceptor(String cedulaReceptor){
        SortedSet<Mensaje> busqueda = new TreeSet<>();
        Persona receptor = buscarUsuarioCedula(cedulaReceptor);
        for (Mensaje m: mensajes){
            if (m.getReceptor().equals(receptor)){
                busqueda.add(m);
            }
        }
        return busqueda;
    }

    /**
     * !!! Podría añadirse uno de busqueda por intervalo de fecha
     */


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
     * Metodo para asignar id's únicas a los envios, se le suma 1 al último envio que hay en la lista
     * @return idUnico
     */
    public int asignarIdEnvio(){
        int idUnico=0;
        ArrayList<Envio> lista =new ArrayList<>(envios);
        Envio ultimo= lista.get(lista.size()-1);
        idUnico= ultimo.getId()+1;
        return idUnico;
    }
    /**
     * Método para crear una lista de los envios que tiene un usuario en el sistema
     * @param p
     *  @return lista
     */
    public ArrayList enviosUsuario(Persona p){
        ArrayList<Envio> lista= new ArrayList<>(envios);
        ArrayList<Envio> nuevaLista=new ArrayList<>();
        for (Envio e: lista){
            if (e.getSolicitante().equals(p)){
                nuevaLista.add(e);
            }
        }
        return nuevaLista;
    }
    /**
     * Método para mostrar el estado del paquete/Envio
     * @param estado
     *  @return resultado
     */
    public String mostrarEstadoPaquete(int estado){
        String resultado="";
        switch (estado){
            case 0:
                resultado="El paquete aún no ha sido retirado de su domicilio";
                break;
            case 1:
                resultado="El paquete se encuentra en la oficina previo al envio";
                break;
            case 2:
                resultado="El paquete está saliendo de oficina a la sucursal de destino";
                break;
            case 3:
                resultado="El paquete llegó a la sucursal de destino";
                break;
            case 4:
                resultado="El paquete está en camino al domicilio del receptor";
                break;
            case 5:
                resultado="El paquete ha sido entregado";
                break;
        }
        return resultado;
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
     * @param p Persona ya existente que se busca con el metodo de buscar interfaces.usuario por cedula
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
        for (Envio e:envios){
            if (e.getEstado()<3){
                buscarCamion(idCamion).getCarga().add(e);
            }
        }
    }
    public void generarListaEnviosEntrega(int idCamion){
        Sucursales s;
        for (Envio e:envios){
            if (e.getEstado()==3 || e.getEstado()==4){
                buscarCamion(idCamion).getCarga().add(e);
            }
        }
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
