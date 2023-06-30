package interfaces.usuario;

import clases.*;
import interfaces.Inicio;
import sistema.Validaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CambiarDatosEnvio {
    private JComboBox cboIdEnvio;
    private JComboBox cboDatosEnvio;
    private JPanel cambiarDatosEnvio;
    private JButton btnBuscar;
    private JButton btnRegresar;
    private JTabbedPane tabbedPane1;
    private JPanel receptor;
    private JTextField txtCedula;
    private JButton btnBuscarUsuario;
    private JPanel clienteTemporal;
    private JTextField txtNombreReceptor;
    private JTextField txtTelefono;
    private JButton btnCambiarReceptor;
    private JPanel domicilio;
    private JTextField txtLatitudDomicilio;
    private JTextField txtLongitudDomicilio;
    private JButton btnCambiarDomicilio;
    private JPanel paquete;
    private JTextField txtAltoDimension;
    private JTextField txtAnchoDimension;
    private JTextField txtLargoDimension;
    private JTextField txtPesoDimension;
    private JTextField txtDetallePaquete;
    private JButton btnCambiarPaquete;
    private JPanel sucursal;
    private JComboBox cboCambioSucursalS;
    private JComboBox cboCambioSucursalD;
    private JButton btnCambiarSucursales;
    private JPanel entrega;
    private JTextField txtLatitudEntrega;
    private JTextField txtLongitudEntrega;
    private JButton btnCambiarEntrega;
    private Persona usuario;
    private Envio envio;
    private EnvioRecibidoDomicilio envioDomicilio;

    public CambiarDatosEnvio(Persona p) {
        envioDomicilio=null;
        envio=null;
        usuario=p;
        actualizarCboId();
        deshabilitarPaneles();
        clienteTemporal.setVisible(false);

    /**
     * Redirecciona a la interfaz Usuario Inicio
     */
    btnRegresar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cambiarDatosEnvio);
            este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
            este.revalidate();
        }
    });
        /**
         * Encuentra el envio y habilita el panel con la opcion que elige el usuario
         */
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Envio> lista=Inicio.sistema.enviosUsuario(usuario);
                //Valida si el usuario tiene envios en el sistema
                if (lista.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Todavía no tiene envíos agregados para modificar");
                    JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cambiarDatosEnvio);
                    este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                    este.revalidate();
                }else{
                    int id=Integer.parseInt(cboIdEnvio.getSelectedItem().toString());
                    envio =  Inicio.sistema.buscarEnvio(id);
                    int estado=envio.getEstado();
                    int opcion=cboDatosEnvio.getSelectedIndex();
                    //habilita los paneles en base a los datos que quiera modificar
                    if (estado>=4){
                        JOptionPane.showMessageDialog(null,"No puede realizar cambios porque el estado avanzado del envio");
                    }else{
                        switch (opcion){
                            case 0:
                                if (estado<=2){
                                    int valido0=JOptionPane.showConfirmDialog(null,"Quiere modificar el receptor\n" +
                                            "Nombre: "+envio.getReceptor().getNombre(),"Confirmacion",JOptionPane.YES_NO_OPTION);
                                    //Valida si ese es el panel que quiere cambiar los datos
                                    if (valido0==0){
                                        deshabilitarPaneles();
                                        receptor.setVisible(true);
                                        JOptionPane.showMessageDialog(null,"Dirigase al panel de receptor");
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Escoja una opción");
                                    }
                                }else
                                    JOptionPane.showMessageDialog(null,"No puede modificar el receptor ya que el envio no se encuentra disponible");

                                break;
                            case 1:
                                if (estado==0){
                                    if (esEnvioDomicilio(envio)){
                                        int valido1=JOptionPane.showConfirmDialog(null,"Quiere modificar la dirección de su domicilio\n"
                                                ,"Confirmacion",JOptionPane.YES_NO_OPTION);
                                        //Valida si ese es el panel que quiere cambiar los datos
                                        if (valido1==0){
                                            deshabilitarPaneles();
                                            domicilio.setVisible(true);
                                            JOptionPane.showMessageDialog(null,"Dirigase al panel de domicilio");
                                        }else{
                                            JOptionPane.showMessageDialog(null,"Escoja una opción");
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Este paquete fue entregado directamente a la oficina, no hay cambios disponibles para su domicilio");
                                    }
                                }else
                                    JOptionPane.showMessageDialog(null,"No puede modificar su domicilio ya que el envio no se encuentra disponible");
                                break;
                            case 2:
                                if (estado==0){
                                    int valido2=JOptionPane.showConfirmDialog(null,"Quiere modificar el tamaño y detalle del paquete \n"+
                                            "Detalle: "+envio.getPaquete().getDetalle(),"Confirmacion",JOptionPane.YES_NO_OPTION);
                                    //Valida si ese es el panel que quiere cambiar los datos
                                    if (valido2==0){
                                        deshabilitarPaneles();
                                        paquete.setVisible(true);
                                        JOptionPane.showMessageDialog(null,"Dirigase al panel de Paquete");
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Escoja una opción");
                                    }
                                }else
                                    JOptionPane.showMessageDialog(null,"No puede modificar el paquete ya que el envio no se encuentra disponible");
                                break;
                            case 3:
                                if (estado<=2){
                                    int valido3=JOptionPane.showConfirmDialog(null,"Quiere modificar las sucursales\n"
                                            +"Sucursal salida: "+envio.getSucursalRecibida().getZona()+"\tSucursal destino: "+envio.getSucursalEntrega().getZona(),"Confirmacion",JOptionPane.YES_NO_OPTION);
                                    //Valida si ese es el panel que quiere cambiar los datos
                                    if (valido3==0){
                                        deshabilitarPaneles();
                                        sucursal.setVisible(true);
                                        JOptionPane.showMessageDialog(null,"Dirigase al panel de Sucursal");
                                        JOptionPane.showMessageDialog(null,"Tenga en consideración que se le puede cobrar una multa por el cambio");
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Escoja una opción");
                                    }
                                }else
                                    JOptionPane.showMessageDialog(null,"No puede modificar las sucursales ya que el envio no se encuentra disponible");
                                break;
                            case 4:
                                if (estado<=3){
                                    int valido4=JOptionPane.showConfirmDialog(null,"Quiere modificar el domicilio de entrega\n"
                                            ,"Confirmacion",JOptionPane.YES_NO_OPTION);
                                    //Valida si ese es el panel que quiere cambiar los datos
                                    if (valido4==0){
                                        deshabilitarPaneles();
                                        entrega.setVisible(true);
                                        JOptionPane.showMessageDialog(null,"Dirigase al panel de Domicilio Receptor");
                                        JOptionPane.showMessageDialog(null,"Tenga en consideración que se le puede cobrar una multa por el cambio");
                                    }else{
                                        JOptionPane.showMessageDialog(null,"Escoja una opción");
                                    }
                                }else
                                    JOptionPane.showMessageDialog(null,"No puede modificar el domicilio del receptor ya que el envio no se encuentra disponible");
                                break;
                        }
                    }
                }
            }
        });
        /**
         * Verifica si existe el nuevo receptor y le pregunta si quiere
         * agregar ese usuario o crear uno temporal
         */
        btnBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula=txtCedula.getText();
                if (cedula.equals("")){
                    JOptionPane.showMessageDialog(null,"Llene todos los campos");
                }else{
                    try {
                        boolean validar= Validaciones.cedulaValida(cedula);
                        if (validar){
                            Persona cambiar= Inicio.sistema.buscarUsuarioCedula(cedula);
                            if (cambiar!=null){
                                if (cambiar==p){
                                    JOptionPane.showMessageDialog(null,"No puede ponerse a si mismo como receptor");
                                    txtCedula.setText("");
                                }else{
                                    int opcion=JOptionPane.showConfirmDialog(null,"Está seguro que este es el nuevo usuario receptor:\n" +
                                            "\tCedula: "+usuario.getCedula()+"\n"+usuario.getNombre(), "Confirmación de receptor",JOptionPane.YES_NO_OPTION);
                                    //Valido si quiere crear una Persona receptor con usuarios del sistema
                                    if(opcion==0){
                                        if (esEnvioDomicilio(envio)){
                                            envioDomicilio= (EnvioRecibidoDomicilio) envio;
                                            envioDomicilio.setReceptor(cambiar);
                                            JOptionPane.showMessageDialog(null,"Receptor Modificado");
                                        }else{
                                            envio.setReceptor(cambiar);
                                            JOptionPane.showMessageDialog(null,"Receptor Modificado");
                                        }
                                    }else {
                                        JOptionPane.showMessageDialog(null,"Ingrese de nuevo la cedula del usuario receptor");
                                        txtCedula.setText("");
                                    }
                                }
                            }else{
                                int o2= JOptionPane.showConfirmDialog(null,"El usuario no está en el sistema, seleccione yes si desea agregar" +
                                        "un usario temporal, presione no, si quiere volver a intentar con otra cedula","Confirmacion",JOptionPane.YES_NO_OPTION);
                                /*
                                 * Valida que la persona que busca no está en el sistema y le pregunta si quiere
                                 * crear una Persona temporal o buscar otra Persona (puede ser usuario o no)
                                 * */
                                if(o2==0){
                                    JOptionPane.showMessageDialog(null,"Por favor llenar los siguientes datos");
                                    clienteTemporal.setVisible(true);
                                    btnBuscarUsuario.setEnabled(false);
                                }else {
                                    JOptionPane.showMessageDialog(null,"Ingrese de nuevo la cedula del usuario receptor");
                                    txtCedula.setText("");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Cedula Inválida");
                            txtCedula.setText("");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        /**
         * Cambia el receptor que no tiene usuario en el sistema
         */
        btnCambiarReceptor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre=txtNombreReceptor.getText(),
                        telefono=txtTelefono.getText();
                //Valida que los campos estén llenos
                if (nombre.equals("")){
                    JOptionPane.showMessageDialog(null,"Llene todos los campos");
                }else{
                    try{
                        //Valida el telefono
                        Validaciones.validarTelefono(telefono);
                        String cedula=txtCedula.getText();
                        /*
                         * Crea una Persona temporal receptora, no está en el sistema
                         * la contraseña la mando como "**" y el tipo de cuenta null
                         * no se si sea correcto jajajaj
                         * */
                        Persona temporal = new Persona(nombre,cedula,telefono,"",null);
                        if (esEnvioDomicilio(envio)){
                            envioDomicilio= (EnvioRecibidoDomicilio) envio;
                            envioDomicilio.setReceptor(temporal);
                            JOptionPane.showMessageDialog(null,"Receptor Modificado");
                        }else{
                            envio.setReceptor(temporal);
                            JOptionPane.showMessageDialog(null,"Receptor Modificado");
                        }
                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        /**
         * Modifica la direccion de domicilio del solicitante
         */
        btnCambiarDomicilio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (esEnvioDomicilio(envio)){
                    envioDomicilio= (EnvioRecibidoDomicilio) envio;
                    //Valida el ingreso de solo numeros
                    try{
                        double latitud=Double.parseDouble(txtLatitudDomicilio.getText()),
                                longitud=Double.parseDouble(txtLongitudDomicilio.getText());
                        Direccion cambiar= new Direccion(latitud,longitud);
                        envioDomicilio.setDireccionRecibida(cambiar);
                        JOptionPane.showMessageDialog(null,"Su domicilio fue modificado");
                    }catch (NumberFormatException ex){
                        double latitud=-1,
                                longitud=-1;
                        if (latitud==-1 || longitud==-1){
                            JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                        }
                    }
                }
            }
        });
        /**
         * Modifica el paquete agregado
         */
        btnCambiarPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String detalle=txtDetallePaquete.getText();
                try{
                    double alto=Double.parseDouble(txtAltoDimension.getText()),
                            ancho=Double.parseDouble(txtAnchoDimension.getText()),
                            largo=Double.parseDouble(txtLargoDimension.getText()),
                            peso=Double.parseDouble(txtPesoDimension.getText());
                    //Valida que los campos estén llenos
                    if (detalle.equals("") || alto<0|| ancho<0|| largo<0|| peso<0){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }else{
                        //Crea un paquete para cambiar el anterior
                        Dimension d= new Dimension(alto,ancho,largo,peso);
                        Paquete cambiar =new Paquete(d,detalle);
                        if (esEnvioDomicilio(envio)){
                            envioDomicilio= (EnvioRecibidoDomicilio) envio;
                            envioDomicilio.setPaquete(cambiar);
                            JOptionPane.showMessageDialog(null,"Paquete Modificado");
                        }else{
                            envio.setPaquete(cambiar);
                            JOptionPane.showMessageDialog(null,"Paquete Modificado");
                        }
                    }
                }catch (NumberFormatException ex){
                    double alto=-1,
                            ancho=-1,
                            largo=-1,
                            peso=-1;
                    //Valida que los campos estén llenos
                    if (detalle.equals("") || alto==-1|| ancho==-1|| largo==-1|| peso==-1){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }
                }
            }
        });
        /**
         * Modifica las sucursales
         */
        btnCambiarSucursales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion=cboCambioSucursalS.getSelectedIndex();
                int estado=envio.getEstado();
                /**
                 * Se valida de nuevo el estado para permitir cambiar las 2 sucursales o no
                 */
                if (estado==0){
                    //Modifico las sucursales para el envio en base a la posicion de la lista sucursales en sistema
                    if (esEnvioDomicilio(envio)){
                        envioDomicilio= (EnvioRecibidoDomicilio) envio;
                        switch (opcion){
                            case 0:
                                envioDomicilio.setSucursalRecibida(Inicio.sistema.getSucursales().get(0));
                                break;
                            case 1:
                                envioDomicilio.setSucursalRecibida(Inicio.sistema.getSucursales().get(1));
                                break;
                            case 2:
                                envioDomicilio.setSucursalRecibida(Inicio.sistema.getSucursales().get(2));
                                break;
                            case 3:
                                envioDomicilio.setSucursalRecibida(Inicio.sistema.getSucursales().get(3));
                                break;}
                        int op=cboCambioSucursalD.getSelectedIndex();
                        //Modifico las sucursales para el envio en base a la posicion de la lista sucursales en sistema
                        switch (op){
                            case 0:
                                envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(0));
                                break;
                            case 1:
                                envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(1));
                                break;
                            case 2:
                                envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(2));
                                break;
                            case 3:
                                envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(3));
                                break;}
                        JOptionPane.showMessageDialog(null,"Sucursales Modificadas");
                    }else{
                        switch (opcion){
                            case 0:
                                envio.setSucursalRecibida(Inicio.sistema.getSucursales().get(0));
                                break;
                            case 1:
                                envio.setSucursalRecibida(Inicio.sistema.getSucursales().get(1));
                                break;
                            case 2:
                                envio.setSucursalRecibida(Inicio.sistema.getSucursales().get(2));
                                break;
                            case 3:
                                envio.setSucursalRecibida(Inicio.sistema.getSucursales().get(3));
                                break;}
                        int op=cboCambioSucursalD.getSelectedIndex();
                        //Modifico las sucursales para el envio en base a la posicion de la lista sucursales en sistema
                        switch (op){
                            case 0:
                                envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(0));
                                break;
                            case 1:
                                envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(1));
                                break;
                            case 2:
                                envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(2));
                                break;
                            case 3:
                                envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(3));
                                break;}
                        JOptionPane.showMessageDialog(null,"Sucursales Modificadas");
                    }
                }else{
                    //Se pregunta si está de acuerdo con solo modificar la sucursal de destino
                    int respuesta=JOptionPane.showConfirmDialog(null,"Debido al estado del paquete solo se puede modificar la sucursal de destino" +
                            " está de acuerdo?","Confirmacion",JOptionPane.YES_NO_OPTION);
                    if(respuesta==0){
                        if (esEnvioDomicilio(envio)){
                            envioDomicilio=(EnvioRecibidoDomicilio) envio;
                            int op=cboCambioSucursalD.getSelectedIndex();
                            //Modifico las sucursales para el envio en base a la posicion de la lista sucursales en sistema
                            switch (op){
                                case 0:
                                    envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(0));
                                    break;
                                case 1:
                                    envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(1));
                                    break;
                                case 2:
                                    envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(2));
                                    break;
                                case 3:
                                    envioDomicilio.setSucursalEntrega(Inicio.sistema.getSucursales().get(3));
                                    break;}
                            JOptionPane.showMessageDialog(null,"Sucursal de destino modificada");
                        }else{
                            int op=cboCambioSucursalD.getSelectedIndex();
                            //Modifico las sucursales para el envio en base a la posicion de la lista sucursales en sistema
                            switch (op){
                                case 0:
                                    envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(0));
                                    break;
                                case 1:
                                    envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(1));
                                    break;
                                case 2:
                                    envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(2));
                                    break;
                                case 3:
                                    envio.setSucursalEntrega(Inicio.sistema.getSucursales().get(3));
                                    break;}
                            JOptionPane.showMessageDialog(null,"Sucursal de destino modificada");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Cambios Cancelados");
                    }
                }
            }
        });
        //Modifico la dirección de entrega a domicilio
        btnCambiarEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Valida el ingreso de solo numeros
                try{
                    double latitud=Double.parseDouble(txtLatitudEntrega.getText()),
                            longitud=Double.parseDouble(txtLongitudEntrega.getText());
                    Direccion cambiar = new Direccion(latitud,longitud);
                    if (esEnvioDomicilio(envio)){
                        envioDomicilio= (EnvioRecibidoDomicilio) envio;
                        envioDomicilio.setDireccionEntrega(cambiar);
                        JOptionPane.showMessageDialog(null,"El domicilio del receptor fue modificado");
                    }else{
                        envio.setDireccionEntrega(cambiar);
                        JOptionPane.showMessageDialog(null,"El domicilio del receptor fue modificado");
                    }
                }catch (NumberFormatException ex){
                    double latitud=-1,
                            longitud=-1;
                    if (latitud==-1 || longitud==-1){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }
                }
            }
        });
    }

    /**
     * Método get del panel
     * @return cambiarDatosEnvio
     */
    public JPanel getCambiarDatosEnvio() {
        return cambiarDatosEnvio;
    }

    /**
     * Actualiza el comboBox con los envios que tiene el usuario
     */
    public void actualizarCboId (){
        ArrayList<Envio> envios = Inicio.sistema.enviosUsuario(usuario);
        for (Envio e: envios){
            int id=e.getId();
            cboIdEnvio.addItem(id);
        }
    }

    /**
     * Deshabilita todos los paneles para que no haga cambios
     */
    public void deshabilitarPaneles (){
        receptor.setVisible(false);
        domicilio.setVisible(false);
        paquete.setVisible(false);
        sucursal.setVisible(false);
        entrega.setVisible(false);
    }

    /**
     * Verifica si el envio es de tipo envioRecibidoDomicilio
     * @param en
     * @return boolean
     */
    public boolean esEnvioDomicilio(Envio en){

        if (en instanceof EnvioRecibidoDomicilio){
            return true;
        }
        return false;
    }
}
