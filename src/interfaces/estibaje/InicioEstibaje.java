package interfaces.estibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioEstibaje {

    private JPanel principal;
    private JLabel imgAvatar;
    private JButton btnInterfazModificarEnvios;
    private JButton btnEstadoPaquete;
    private JButton btnListarPSucursal;
    private JButton btnListaEnvios;

    public InicioEstibaje() {
        btnInterfazModificarEnvios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame esto = (JFrame) SwingUtilities.getWindowAncestor(principal);
                esto.setContentPane(new ModEnviosEstibaje().getPanel());
                esto.revalidate();
            }
        });
    }

    public JPanel getPanel(){
        return principal;
    }
}
