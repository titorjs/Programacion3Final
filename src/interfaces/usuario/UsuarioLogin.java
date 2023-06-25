package interfaces.usuario;

import clases.Persona;
import clases.TipoCuenta;
import interfaces.Inicio;
import sistema.Validaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioLogin {
    private JPanel usuarioLogin;
    private JTextField txtNombreUsuarioAgregar;
    private JTextField txtCedulaUsuarioAgregar;
    private JTextField txtTelefonoUsuarioAgregar;
    private JTextField txtContraseniaUsuarioAgregar;
    private JTextField txtValidarContraseniaUsuarioAgregar;
    private JButton btnAgregarUsuario;
    private JButton btnSalir;
    private JPasswordField pswUsuarioAgregar;
    private JPasswordField pswValidarContraseniaUsuarioAgregar;

    public UsuarioLogin() {
        /*
            Registra un nuevo interfaces.usuario
         */
    btnAgregarUsuario.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cedula = txtCedulaUsuarioAgregar.getText(),
                    nombre = txtNombreUsuarioAgregar.getText(),
                    telefono = txtTelefonoUsuarioAgregar.getText(),
                    contrasenia = pswUsuarioAgregar.getText(),
                    contraseniaV = pswValidarContraseniaUsuarioAgregar.getText();
            if (cedula.equals("") || nombre.equals("") || telefono.equals("")
                    || contrasenia.equals("") || contraseniaV.equals("")) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos correctamente.");
            } else {
                try {
                    /** Validación cédula */
                    boolean validacion = Validaciones.cedulaValida(cedula);
                    if (validacion) {
                        /** !!! La validación de si la cédula ya existe  */
                        Validaciones.validarTelefono(telefono);
                        if (contrasenia.equals(contraseniaV)) {
                            Persona p = new Persona(nombre, cedula, telefono, contrasenia, TipoCuenta.USUARIO);
                            if (Inicio.sistema.agregarUsuario(p)) {
                                JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
                                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioLogin);
                                este.setContentPane(new Inicio().getPanel());
                                este.revalidate();
                            } else {
                                JOptionPane.showMessageDialog(null, "Un interfaces.usuario con esa cédula ya existe");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La cédula ingresada es inválida.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    });
    /*
        Redirecciona a interfaz Inicio
     */
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioLogin);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });
    }

    public JPanel getUsuarioLogin() {
        return usuarioLogin;
    }
}
