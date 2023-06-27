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
    private JTextField txtLatitudDireccion;
    private JTextField txtLongitudDireccion;
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
    private JTextField txtLatitudDireccionDomicilio;
    private JTextField txtLongitudDireccionDomicilio;
    private JPanel usuarioEnvio;
    private JPanel clienteTemporal;
    private JPanel servicioDomicilio;
    private JButton btnCrearTemporal;
    private JTextField txtDetallePaquete;
    private JButton btnAgregarPaquete;
    private JButton btnAgregarSucursales;
    private Persona receptor;
    private Direccion direccion;
    private Paquete paquetin;
    private Sucursal salida;
    private Sucursal destino;
    private int estado;
    private Direccion domicilio;


    public UsuarioEnvio(Persona p) {
        domicilio=null;
        estado=-1;
        salida=null;
        destino=null;
        paquetin=null;
        receptor=null;
        direccion=null;
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
                    double latitud= Double.parseDouble(txtLatitudDireccion.getText()),
                            longitud=Double.parseDouble(txtLongitudDireccion.getText());
                    if (cedula.equals("") ){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }else {
                        try{
                            boolean validar= Validaciones.cedulaValida(cedula);
                            if (validar){
                                Persona usuario= Inicio.sistema.buscarUsuarioCedula(cedula);
                                if (usuario!=null){
                                    int opcion=JOptionPane.showConfirmDialog(null,"Está seguro que este es el usuario receptor:\n" +
                                            "\tCedula: "+usuario.getCedula()+"\n"+usuario.getNombre(), "Confirmación de receptor",JOptionPane.YES_NO_OPTION);
                                    if(opcion==0){
                                        JOptionPane.showMessageDialog(null,"El paquete se enviará a dicho receptor");
                                        receptor=usuario;
                                        direccion=new Direccion(latitud,longitud);
                                    }else {
                                        JOptionPane.showMessageDialog(null,"Ingrese de nuevo la cedula del usuario receptor");
                                        txtCedulaVerificar.setText("");
                                    }
                                }else{
                                    int o2= JOptionPane.showConfirmDialog(null,"El usuario no está en el sistema, seleccione yes si desea agregar" +
                                            "un usario temporal, presione no, si quiere volver a intentar con otra cedula","Confirmacion",JOptionPane.YES_NO_OPTION);
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
                    double latitud=Double.parseDouble(txtLatitudDireccionDomicilio.getText()),
                            longitud=Double.parseDouble(txtLongitudDireccionDomicilio.getText());
                    domicilio=new Direccion(latitud,longitud);
                    JOptionPane.showMessageDialog(null,"Servicio a Domicilio aceptado");
                }catch(NumberFormatException ex){
                    double latitud=-1,
                            longitud=-1;
                    if (latitud==-1 || longitud==-1){
                        JOptionPane.showMessageDialog(null,"Llene los campos correctamente");
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
                try{
                    Validaciones.validarTelefono(telefono);
                    String cedula=txtCedulaVerificar.getText();
                    double latitud=Double.parseDouble(txtLatitudDireccion.getText()),
                            longitud=Double.parseDouble(txtLongitudDireccion.getText());
                    receptor=new Persona(nombre,cedula,telefono,"**",null);
                    direccion=new Direccion(latitud,longitud);
                    JOptionPane.showMessageDialog(null,"Datos del cliente temporal agregados");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
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
                    if (detalle.equals("") || alto<0|| ancho<0|| largo<0|| peso<0){
                        JOptionPane.showMessageDialog(null,"Llene todos los campos correctamente");
                    }else{
                        Dimension d= new Dimension(alto,ancho,largo,peso);
                        paquetin=new Paquete(d,detalle);
                        JOptionPane.showMessageDialog(null,"Paquete Creado");
                    }
                }catch (NumberFormatException ex){
                    double alto=-1,
                            ancho=-1,
                            largo=-1,
                            peso=-1;
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
                    //Verifico si utiliza el servicio a domicilio
                    int option=JOptionPane.showConfirmDialog(null,"Sucursales agregadas, desea utilizar el servicio a domicilio para recolectar el paquete?"
                            ,"Confirmación",JOptionPane.YES_NO_OPTION);
                    if (option==0){
                        estado=0;
                        JOptionPane.showMessageDialog(null,"Por favor llenar los datos para retiro a domicilio");
                        servicioDomicilio.setVisible(true);
                        btnAgregarSucursales.setEnabled(false);
                    }else{
                        estado=1;
                        JOptionPane.showMessageDialog(null,"Servicio domicilio cancelado");
                    }
                }
            }
        });
        btnCrearEnvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (receptor==null || paquetin==null || salida==null|| destino==null || estado==-1){
                    JOptionPane.showMessageDialog(null,"Debe ingresar todos los datos en los campos requeridos");
                    actualizarPanel();
                }else {
                    if (domicilio==null){
                        LocalDateTime l1 = LocalDateTime.now();
                        int id=Inicio.sistema.asignarIdEnvio();
                        Envio nuevo=new Envio(id,estado,l1,p,receptor,paquetin,salida,destino,direccion);
                        int opcion=cboSucursalSalida.getSelectedIndex();
                        String nombreS=nombreSucursal(opcion);
                        Inicio.sistema.agregarEnvio(nuevo,nombreS);
                        JOptionPane.showMessageDialog(null,"Su Envio ha sido agregado, el ID de su envio es: " +
                                ""+nuevo.getId()+" con este ID podrás hacer seguimiento de tu envio");
                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioEnvio);
                        este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                        este.revalidate();
                    }else{
                        LocalDateTime l1 = LocalDateTime.now();
                        int id=Inicio.sistema.asignarIdEnvio();
                        EnvioRecibidoDomicilio nuevo= new EnvioRecibidoDomicilio(id,estado,l1,p,receptor,paquetin,salida,destino,
                                direccion,domicilio);
                        int opcion =cboSucursalSalida.getSelectedIndex();
                        String nombreS=nombreSucursal(opcion);
                        Inicio.sistema.agregarEnvio(nuevo,nombreS);
                        JOptionPane.showMessageDialog(null,"Su Envio ha sido agregado, el ID de su envio es: " +
                                ""+nuevo.getId()+" con este ID podrás hacer seguimiento de tu envio");
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
     */
    public void actualizarPanel(){
        if (receptor!=null || direccion!=null){
            persona.setVisible(false);
        }
        if (paquetin!=null){
            paquete.setVisible(false);
        }
        if ((salida!=null && destino!=null) || estado!=-1){
            sucursal.setVisible(false);
        }
    }
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
