package interfaces.estibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModPaquete {
    private JPanel jpModPaquete;
    private JTextField txtModDimAlto;
    private JTextField txtModDimLargo;
    private JTextField txtModDimAncho;
    private JTextField txtModDimPeso;
    private JTextArea txtAModDetalle;
    private JButton btnModPaquete;
    private JButton btnPaqaEnvios;

    public JPanel getJpModPaquete() {
        return jpModPaquete;
    }

    public PestañaModPaquete() {
    btnPaqaEnvios.addActionListener(new ActionListener() {
        /**
         * BOTON PARA REGRESAR A LA PESTAÑA DE ENVIOS ESTIBAJE
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModPaquete);
            este.setContentPane(new ModEnviosEstibaje().getPanel());
            este.revalidate();
        }
    });

}
}
