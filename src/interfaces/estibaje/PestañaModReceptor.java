package interfaces.estibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModReceptor {
    private JPanel jpPestañaModReceptor;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btnRegresarRecaModEnvios;

    public PestañaModReceptor() {
        btnRegresarRecaModEnvios.addActionListener(new ActionListener() {
            /**
             * BOTON PARA REGRESAR A LA PESTAÑA DE ENVIOS ESTIBAJE
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpPestañaModReceptor);
                este.setContentPane(new ModEnviosEstibaje().getPanel());
                este.revalidate();
            }
        });
    }

    public JPanel getJpPestañaModReceptor() {
        return jpPestañaModReceptor;
    }
}
