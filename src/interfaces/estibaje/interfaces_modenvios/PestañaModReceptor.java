package interfaces.estibaje.interfaces_modenvios;

import clases.Persona;
import clases.TipoCuenta;
import interfaces.Inicio;
import sistema.Validaciones;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModReceptor {
    private JPanel jpPestañaModReceptor;
    private JTabbedPane tabbedPane1;
    private JTextField txtModCedulaNReceptor;
    private JButton btnCambiarNuevoReceptor;
    private JTextField txtNombreTemporal;
    private JTextField txtCedulaTemporal;
    private JTextField txtTelefonoTemporal;
    private JButton btnCrearUsuarioTemporal;
    private JButton btnRecaEnvios1;
    private JButton btnRecaEnvios2;
    private ModEnviosEstibaje modEnviosEstibaje;

    public PestañaModReceptor() {
        /**
         * Botones para redireccionar a Pestaña de Modifcar envios
         */
        btnRecaEnvios1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpPestañaModReceptor);
                este.setContentPane(new ModEnviosEstibaje().getPanel());
                este.revalidate();
            }
        });
        btnRecaEnvios2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpPestañaModReceptor);
                este.setContentPane(new ModEnviosEstibaje().getPanel());
                este.revalidate();
            }
        });
        /**
         * En este boton se cambiara la persona receptora del envio que queremos modificar
         */
        btnCambiarNuevoReceptor.addActionListener(new ActionListener() {
            /**
             * Se valida que sea una cedula valida
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    boolean cedulaV=Validaciones.cedulaValida(txtModCedulaNReceptor.getText());
                    if (cedulaV){
                        txtModCedulaNReceptor.setText("");
                    }
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }
                //Se modifica el envio con la nueva persona
                if (Inicio.sistema.buscarUsuarioCedula(txtModCedulaNReceptor.getText())!=null){
                    modEnviosEstibaje.getEnvioDeModEnvios().setReceptor(Inicio.sistema.buscarUsuarioCedula(txtModCedulaNReceptor.getText()));
                }else {
                    JOptionPane.showMessageDialog(null,"No existe el usuario");
                }
            }
        });
        /**
         * Boton para crear un usuario temporal validando la cedula y el telefono
         */
        btnCrearUsuarioTemporal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Validamos la cedula
                try{
                    boolean cedulaV=Validaciones.cedulaValida(txtCedulaTemporal.getText());
                    if (cedulaV){
                        txtCedulaTemporal.setText("");
                    }
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,exception);
                }
                //Validamos el telefono
                try {
                    boolean telefonoV=Validaciones.validarTelefono(txtTelefonoTemporal.getText());
                    if (telefonoV){
                        return;
                    }
                }catch (Exception exception1){
                    JOptionPane.showMessageDialog(null,exception1);
                }
                Persona personaTemporal= new Persona(txtNombreTemporal.getText(),txtCedulaTemporal.getText(),txtTelefonoTemporal.getText(),"", TipoCuenta.USUARIO);
                Inicio.sistema.modificarPersona(personaTemporal,modEnviosEstibaje.getEnvioDeModEnvios());
            }
        });


    }

    public JPanel getJpPestañaModReceptor() {
        return jpPestañaModReceptor;
    }
}
