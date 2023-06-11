package interfaces.estibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModDireccion {
    private JTextField txtCx;
    private JTextField txtCy;
    private JButton btnCambiarDireccion;
    private JButton btnDiraModEnvios;
    private JPanel jpPestañaModDireccion;

    public PestañaModDireccion() {
        btnDiraModEnvios.addActionListener(new ActionListener() {
            /**
             * BOTON PARA REGRESAR A LA PESTAÑA DE ENVIOS ESTIBAJE
             * @param e the event to be processed
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
