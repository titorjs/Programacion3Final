package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEmpleados {
    private JPanel LoginEmpleados;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JButton volverALaPáginaButton;


    public LoginEmpleados() {
        volverALaPáginaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Redirección a interfaz Inicio
                 */
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(LoginEmpleados);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });
    }

    public JPanel getPanel() {
        return LoginEmpleados;
    }
}
