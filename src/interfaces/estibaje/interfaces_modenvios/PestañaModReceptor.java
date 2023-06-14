package interfaces.estibaje.interfaces_modenvios;

import clases.Persona;
import clases.TipoCuenta;
import interfaces.Inicio;
import sistema.Validaciones;
import clases.Envio;

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

    public PestañaModReceptor(Envio envio) {
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
                try {
                    boolean cedulaV = Validaciones.cedulaValida(txtModCedulaNReceptor.getText());
                    if (cedulaV) {
                        Persona aux = Inicio.sistema.buscarUsuarioCedula(txtModCedulaNReceptor.getText());
                        if (aux != null) {
                            boolean cambio = JOptionPane.showOptionDialog(null, "Es este el usuario por el que desea cambiar?\n" +
                                    aux.toString(), "Confirmar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == 0;
                            if (cambio) {
                                envio.setReceptor(aux);
                            } else {
                                JOptionPane.showMessageDialog(null, "Se canceló el cambio de receptor");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe el usuario");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null , "Cédula ingresada inválida");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
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
                try {
                    boolean cedulaV = Validaciones.cedulaValida(txtCedulaTemporal.getText());
                    if (cedulaV) {
                        boolean telefonoV = Validaciones.validarTelefono(txtTelefonoTemporal.getText());
                        if (telefonoV) {
                            Persona personaTemporal = new Persona(txtNombreTemporal.getText(), txtCedulaTemporal.getText(), txtTelefonoTemporal.getText(), "", TipoCuenta.USUARIO);
                            envio.setReceptor(personaTemporal);
                            JOptionPane.showMessageDialog(null,"Se creo correctamente");
                        }
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });


    }

    public JPanel getJpPestañaModReceptor() {
        return jpPestañaModReceptor;
    }
}
