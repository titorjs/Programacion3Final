package interfaces;

import clases.Persona;
import clases.TipoCuenta;
import interfaces.admin.InicioAdmin;
import interfaces.estibaje.InicioEstibaje;
import sistema.Validaciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEmpleados {
    private JPanel LoginEmpleados;
    private JTextField txfUsuario;
    private JPasswordField pswContrasenia;
    private JButton btnIngresar;
    private JButton btnVolverInicio;


    public LoginEmpleados() {
        /** Redirección a interfaz Inicio */
        btnVolverInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(LoginEmpleados);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });

        /** Inicio de sesión*/
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txfUsuario.getText();
                /**
                 * Inicio sesión admin
                 * !!! Talvez se puede deshacer y unir con el de abajo o hacer que el admin solo sea un interfaces.usuario más
                 */
                if (usuario.equals("admin")) {
                    boolean validar = Inicio.sistema.validarAdmin(pswContrasenia.getText());
                    if (validar) {
                        /**
                         * Redirección a interfaz InicioAdmin
                         */
                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(LoginEmpleados);
                        este.setContentPane(new InicioAdmin().getPanel());
                        este.revalidate();
                    } else {
                        pswContrasenia.setText("");
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                } else {
                    /**
                     * Manejo de inicio de sesión de interfaces.usuario empleado
                     */
                    try {
                        //Validar la cédula
                        boolean validar = Validaciones.cedulaValida(usuario);
                        if (validar) {
                            //Validar que exista el interfaces.usuario de cédula "interfaces.usuario"
                            Persona p = Inicio.sistema.buscarUsuarioCedula(usuario);
                            if (p != null) {
                                //Validar que no sea del tipo interfaces.usuario pues es login para empleados
                                if (!p.getTipo().equals(TipoCuenta.USUARIO)) {
                                    //Validar que la contraseña sea correcta
                                    validar = p.validarContrasenia(pswContrasenia.getText());
                                    if (validar) {
                                        TipoCuenta tipo = p.getTipo();
                                        JPanel panel = null;
                                        //Redirigir según el tipo de cuenta
                                        switch (tipo){
                                            case EJECUTIVO:
                                                //!!!
                                                break;
                                            case REPARTIDOR:
                                                //!!!
                                                break;
                                            case ESTIBAJE:
                                                panel = new InicioEstibaje(p).getPanel();
                                                break;
                                            case CONDUCTOR:

                                                break;
                                        }
                                        /**
                                         * Redirección a nueva página según tipo
                                         */
                                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(LoginEmpleados);
                                        este.setContentPane(panel);
                                        este.revalidate();
                                    } else {
                                        pswContrasenia.setText("");
                                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Este no es el login de usuarios, " +
                                                                                            "\nregrese al apágina principal");
                                }
                            }
                        } else {
                            pswContrasenia.setText("");
                            JOptionPane.showMessageDialog(null, "Usuario(cédula) ingresada inválida.");
                        }
                    } catch (Exception ex) {
                        pswContrasenia.setText("");
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
    }

    public JPanel getPanel() {
        return LoginEmpleados;
    }
}
