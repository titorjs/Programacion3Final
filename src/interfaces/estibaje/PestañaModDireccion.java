package interfaces.estibaje;

import interfaces.LoginEmpleados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModDireccion {
    private JTextField txtCx;
    private JTextField txtCy;
    private JButton btnCambiarDireccion;
    private JButton btnRPModEnvios;
    private JPanel jpPestañaModDireccion;

    public PestañaModDireccion() {
        btnRPModEnvios.addActionListener(new ActionListener() {
            /*
                Redireccion a la Pestaña de Modificar Envios
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpPestañaModDireccion);
                este.setContentPane(new ModEnviosEstibaje().getPanel());
                este.revalidate();
            }
        });
    }

    public JPanel getJpPestañaModDireccion() {
        return jpPestañaModDireccion;
    }
}
