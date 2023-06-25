package interfaces;

import clases.Persona;
import clases.TipoCuenta;
import sistema.Sistema;
import sistema.Validaciones;
import interfaces.usuario.UsuarioInicio;
import interfaces.usuario.UsuarioLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JPanel inicio;
    private JTextField txtIngresoUsuario;
    private JPasswordField pswIngresoUsuario;
    private JButton btnIngresar;
    private JButton btnVerPaquete;
    private JButton btnLoginEmpleado;
    private JButton btnSolicitarCuenta;
    public static Sistema sistema;


    public Inicio() {
        btnVerPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /**
         * Aquí se maneja el ingreso para usuarios
         */
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula=txtIngresoUsuario.getText(),
                        contrasenia=pswIngresoUsuario.getText();
                if (cedula.equals("") || contrasenia.equals("")){
                    JOptionPane.showMessageDialog(null,"Ingrese todos los campos requeridos");
                }else {
                    try {
                        boolean validar = Validaciones.cedulaValida(cedula);
                        //Validar la cédula
                        if (validar){
                            Persona ingreso= Inicio.sistema.buscarUsuarioCedula(cedula);
                            //Validar que exista el interfaces.usuario de cédula "interfaces.usuario"
                            if (ingreso!=null){
                                //Validar que el que el tipoCuenta de interfaces.usuario sea "USUARIO", no empleado
                                if (ingreso.getTipo().equals(TipoCuenta.USUARIO)){
                                    validar=ingreso.validarContrasenia(pswIngresoUsuario.getText());
                                    //Validar la contraseña de interfaces.usuario
                                    if (validar){
                                        //Redireccionar a interfaz UsuarioInicio
                                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(inicio);
                                        este.setContentPane(new UsuarioInicio(ingreso).getUsuarioInicio());
                                        este.revalidate();
                                    }else {
                                        JOptionPane.showMessageDialog(null,"Contraseña incorrecta");
                                        pswIngresoUsuario.setText("");
                                    }
                                }else {
                                    JOptionPane.showMessageDialog(null,"Esta sección es solo para usuarios, no empleados");
                                }
                            }else {
                                JOptionPane.showMessageDialog(null,"No existe el interfaces.usuario en el sistema");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Cedula inválida");
                            txtIngresoUsuario.setText("");
                        }
                    } catch (Exception ex) {
                        pswIngresoUsuario.setText("");
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
        btnLoginEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Redirección a interfaz LoginEmpleados
                 */
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(inicio);
                este.setContentPane(new LoginEmpleados().getPanel());
                este.revalidate();
            }
        });
        /*
            Redireccionar a la interfaz de registro nuevo interfaces.usuario
         */
        btnSolicitarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(inicio);
                este.setContentPane(new UsuarioLogin().getUsuarioLogin());
                este.revalidate();
            }
        });
    }

    public JPanel getPanel() {

        return inicio;
    }

    public static void main(String[] args) {
        /**
         * A través de esta clase se manejarán todos los métodos y necesidades de la interfaz
         * gráfica
         */

        sistema = new Sistema();

        JFrame frame = new JFrame("Transcormogal");
        frame.setContentPane(new Inicio().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
