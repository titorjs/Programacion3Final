package interfaces.estibaje;

import interfaces.LoginEmpleados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class inicioEstibaje {

    private JPanel principal;
    private JLabel imgAvatar;
    private JButton btnInterfazModificarEnvios;
    private JButton btnEstadoPaquete;
    private JButton btnListarPSucursal;
    private JButton btnListaEnvios;

    public inicioEstibaje() {
        btnInterfazModificarEnvios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame esto = (JFrame) SwingUtilities.getWindowAncestor(principal);
                esto.setContentPane(new ModEnviosEstibaje().getPanel());
                esto.revalidate();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("inicioEstibaje");
        frame.setContentPane(new inicioEstibaje().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }
    public JPanel getPanel(){
        return principal;
    }
}
