package interfaces.usuario;

import clases.*;
import interfaces.Inicio;
import sistema.Validaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class UsuarioEnvio {
    private JTabbedPane tabbedPane1;
    private JPanel persona;
    private JButton btnBuscarUsuario;
    private JTextField txtCedulaVerificar;
    private JTextField txtNombreReceptor;
    private JTextField txtTelReceptor;
    private JTextField txtLatitudDireccionDomicilio;
    private JTextField txtLongitudDireccionDomicilio;
    private JPanel paquete;
    private JTextField txtAlturaDimension;
    private JTextField txtAnchoDimension;
    private JTextField txtLargoDimension;
    private JTextField txtPesoDimension;
    private JButton btnCrearEnvio;
    private JButton btnRegresar;
    private JPanel sucursal;
    private JComboBox cboSucursalSalida;
    private JComboBox cboSucursalDestino;
    private JButton btnSolicitar;
    private JPanel usuarioEnvio;
    private JPanel clienteTemporal;
    private JPanel servicioDomicilio;
    private JButton btnCrearTemporal;
    private JTextField txtDetallePaquete;
    private JButton btnAgregarPaquete;
    private JButton btnAgregarSucursales;
    private JTextField txtLatitudEntrega;
    private JTextField txtLongitudEntrega;
    private JPanel personaDatos;
    private JPanel datosPaquete;
    private JPanel sucursalDatos;
    private Persona receptor;
    private Direccion entrega;
    private Paquete paquetin;
    private Sucursal salida;
    private Sucursal destino;
    private int estado;
    private Direccion domicilio;


    public UsuarioEnvio(Persona p) {
        //inicializo mis atributos
        domicilio=null;
        estado=-1;
        salida=null;
        destino=null;
        paquetin=null;
        receptor=null;
        entrega=null;
    clienteTemporal.setVisible(false);
    servicioDomicilio.setVisible(false);
    /*
    Redirecciona a interfaz usuario inicio
     */
    btnRegresar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioEnvio);
            este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
            este.revalidate();
        }
    });
    /*
    Verifica que la persona receptor sea usuario del sistema o no
     */
        btnBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula=txtCedulaVerificar.getText();
                try{
                    double latitud= Double.parseDouble(txtLatitudDireccionDomicilio.getText()),
                            longitud=Double.parseDouble(txtLongitudDireccionDomicilio.getText());
                    //Valida que los campos estén llenos
                    if (cedula.equals("") ){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }else {
                        try{
                            boolean validar= Validaciones.cedulaValida(cedula);
                            //Valido cedula
                            if (validar){
                                Persona usuario= Inicio.sistema.buscarUsuarioCedula(cedula);
                                //Valido si el usuario está en el sistema
                                if (usuario!=null){
                                    if (usuario==p){
                                        JOptionPane.showMessageDialog(null,"No puede ponerse a si mismo como receptor");
                                        txtCedulaVerificar.setText("");
                                    }else {
                                        int opcion=JOptionPane.showConfirmDialog(null,"Está seguro que este es el usuario receptor:\n" +
                                                "\tCedula: "+usuario.getCedula()+"\n"+usuario.getNombre(), "Confirmación de receptor",JOptionPane.YES_NO_OPTION);
                                        //Valido si quiere crear una Persona receptor con usuarios del sistema
                                        if(opcion==0){
                                            JOptionPane.showMessageDialog(null,"El paquete se enviará a dicho receptor");
                                            receptor=usuario;
                                            domicilio=new Direccion(latitud,longitud);
                                        }else {
                                            JOptionPane.showMessageDialog(null,"Ingrese de nuevo la cedula del usuario receptor");
                                            txtCedulaVerificar.setText("");
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
                                        txtCedulaVerificar.setText("");
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Cedula Inválida");
                                txtCedulaVerificar.setText("");
                            }
                        }catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                }catch(NumberFormatException ec){
                    double latitud=-1,longitud=-1;
                    //Valida que los campos estén llenos
                    if (cedula.equals("") || latitud==-1 || longitud==-1){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }
                }
            }
        });
        /*
            Crea la dirección del servicio a domicilio
         */
        btnSolicitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double latitud= Double.parseDouble(txtLatitudEntrega.getText()),
                            longitud=Double.parseDouble(txtLongitudEntrega.getText());
                    entrega=new Direccion(latitud,longitud);
                    JOptionPane.showMessageDialog(null,"Servicio añadido");

                }catch(NumberFormatException ex){
                    double latitud=-1,
                            longitud=-1;
                    if (latitud==-1 || longitud==-1){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }
                }

            }
        });
        /*
          Crea una persona temporal
          Solo se valida nuevos datos (telefono)
         */
        btnCrearTemporal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre=txtNombreReceptor.getText(),
                        telefono=txtTelReceptor.getText();
                if (nombre.equals("")){
                    JOptionPane.showMessageDialog(null,"Llene todos los campos");
                }else{
                    try{
                        //Valida el telefono
                        Validaciones.validarTelefono(telefono);
                        String cedula=txtCedulaVerificar.getText();
                        double latitud=Double.parseDouble(txtLatitudDireccionDomicilio.getText()),
                                longitud=Double.parseDouble(txtLongitudDireccionDomicilio.getText());
                        /*
                         * Crea una Persona temporal receptora, no está en el sistema
                         * la contraseña la mando como "**" y el tipo de cuenta null
                         * no se si sea correcto jajajaj
                         * */
                        receptor=new Persona(nombre,cedula,telefono,"**",null);
                        domicilio=new Direccion(latitud,longitud);
                        JOptionPane.showMessageDialog(null,"Datos del cliente temporal agregados");
                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        /*
            Crea un paquete
         */
        btnAgregarPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String detalle=txtDetallePaquete.getText();
                try{
                    double alto=Double.parseDouble(txtAlturaDimension.getText()),
                            ancho=Double.parseDouble(txtAnchoDimension.getText()),
                            largo=Double.parseDouble(txtLargoDimension.getText()),
                            peso=Double.parseDouble(txtPesoDimension.getText());
                    //Valida que los campos estén llenos
                    if (detalle.equals("") || alto<0|| ancho<0|| largo<0|| peso<0){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }else{
                        //Crea un paquete para el envio
                        Dimension d= new Dimension(alto,ancho,largo,peso);
                        paquetin=new Paquete(d,detalle);
                        JOptionPane.showMessageDialog(null,"Paquete Creado");
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
        /*
            Creo las sucursales
         */
        btnAgregarSucursales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    int opcion=cboSucursalSalida.getSelectedIndex();
                    //Creo las sucursales para el paquete en base a la posicion de la lista sucursales en sistema
                    switch (opcion){
                        case 0:
                            salida=Inicio.sistema.getSucursales().get(0);
                            break;
                        case 1:
                            salida=Inicio.sistema.getSucursales().get(1);
                            break;
                        case 2:
                            salida=Inicio.sistema.getSucursales().get(2);
                            break;
                        case 3:
                            salida=Inicio.sistema.getSucursales().get(3);
                            break;}
                    int op=cboSucursalDestino.getSelectedIndex();
                    switch (op){
                        case 0:
                            destino=Inicio.sistema.getSucursales().get(0);
                            break;
                        case 1:
                            destino=Inicio.sistema.getSucursales().get(1);
                            break;
                        case 2:
                            destino=Inicio.sistema.getSucursales().get(2);
                            break;
                        case 3:
                            destino=Inicio.sistema.getSucursales().get(3);
                            break;}
                if(salida!=null && destino!=null){

                    int option=JOptionPane.showConfirmDialog(null,"Sucursales agregadas, desea utilizar el servicio a domicilio para la entrega del paquete?"
                            ,"Confirmación",JOptionPane.YES_NO_OPTION);
                    //Verifico si utiliza el servicio a domicilio
                    if (option==0){
                        estado=0;
                        JOptionPane.showMessageDialog(null,"Por favor llenar los datos para retiro a domicilio");
                        servicioDomicilio.setVisible(true);
                        btnAgregarSucursales.setEnabled(false);
                    }else{
                        estado=0;
                        JOptionPane.showMessageDialog(null,"Servicio domicilio cancelado");
                    }
                }
            }
        });
        /*
            Crea un envio y se lo agrega a la lista
         */
        btnCrearEnvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Valida si todos los campos están llenos
                if (receptor==null || paquetin==null || salida==null|| destino==null || estado==-1 || domicilio==null){
                    JOptionPane.showMessageDialog(null,"Debe ingresar todos los datos en los campos requeridos, solo estarán habilitados los paneles que faltan por completar");
                    actualizarPanel();
                }else {
                    /*
                    * Valida si el envio va a ser entregado al receptor por domicilio
                    * o tiene que ir a recoger el paquete
                    * */
                    if (entrega==null){
                        LocalDateTime l1 = LocalDateTime.now();
                        int id=Inicio.sistema.asignarIdEnvio();
                        //Creo un objeto tipo Envio
                        EnvioRecibidoDomicilio nuevo=new EnvioRecibidoDomicilio(id,estado,l1,p,receptor,paquetin,salida,
                                destino,null,domicilio);
                        int opcion=cboSucursalSalida.getSelectedIndex();
                        String nombreS=nombreSucursal(opcion);
                        //Agrego el envio al Sistema
                        Inicio.sistema.agregarEnvio(nuevo,nombreS);
                        JOptionPane.showMessageDialog(null,"Su Envio ha sido agregado, el ID de su envio es: " +
                                ""+nuevo.getId()+" con este ID podrás hacer seguimiento de tu envio");
                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioEnvio);
                        este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                        este.revalidate();
                    }else{
                        LocalDateTime l1 = LocalDateTime.now();
                        int id=Inicio.sistema.asignarIdEnvio();
                        //Creo un objeto tipo EnvioRecibidoDomicilio
                        EnvioRecibidoDomicilio nuevo= new EnvioRecibidoDomicilio(id,estado,l1,p,receptor,paquetin,salida,destino,
                                entrega,domicilio);
                        int opcion =cboSucursalSalida.getSelectedIndex();
                        String nombreS=nombreSucursal(opcion);
                        //Agrego el envio al Sistema
                        Inicio.sistema.agregarEnvio(nuevo,nombreS);
                        JOptionPane.showMessageDialog(null,"Su Envio ha sido agregado, el ID de su envio es: " +
                                ""+nuevo.getId()+" con este ID podrás hacer seguimiento de tu envio");
                        //Regresa al panel de inicio de
                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioEnvio);
                        este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                        este.revalidate();
                    }
                }
            }
        });
    }
    /*
    Metodo get del panel
     */
    public JPanel getUsuarioEnvio() {
        return usuarioEnvio;
    }
    /*
        Este método deshabilitará los paneles que ya estén los datos llenos
        Solo estarán habilitados los paneles que no estén llenos los datos para que pueda completar el proceso
        NO FUNCIONA :(
     */
    public void actualizarPanel(){
        if (receptor!=null || entrega!=null){
            personaDatos.setVisible(false);
        }
        if (paquetin!=null){
            datosPaquete.setVisible(false);
        }
        if ((salida!=null && destino!=null) || estado!=-1){
            sucursalDatos.setVisible(false);
        }
    }
    /*
    * Devuelve el nombre de la sucursal en la que estoy,
        por culpa del Tony que crea métodos raros, debo implementar este método
        * @param opcion
        * @return nombre
        * */
    public String nombreSucursal(int opcion){
        String nombre="";
        switch (opcion){
            case 0:
                 nombre="UIO_N";
                break;
            case 1:
                 nombre="UIO_S";
                break;
            case 2:
                 nombre="GYE";
                break;
            case 3:
                 nombre="STO_DGO";
                break;
        }
        return nombre;
    }

}
