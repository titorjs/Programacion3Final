package interfaces.estibaje.interfaces_modenvios;

import clases.Dimension;
import clases.Paquete;
import interfaces.Inicio;

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
    private ModEnviosEstibaje modEnviosEstibaje;

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


        btnModPaquete.addActionListener(new ActionListener() {
            /**
             * Boton para cambiar el paquete del envio que queremos modificar
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Se crea la dimension del paquete, luego el paquete y luego se llama al
                metodo set*/
                Double alto = Double.parseDouble(txtModDimAlto.getText());
                Double ancho = Double.parseDouble(txtModDimAncho.getText());
                Double largo = Double.parseDouble(txtModDimLargo.getText());
                Double peso = Double.parseDouble(txtModDimPeso.getText());
                Dimension d = new Dimension(alto,ancho,largo,peso);
                Paquete p = new Paquete(d,txtAModDetalle.getText());
                Inicio.sistema.modificarPaquete(p,modEnviosEstibaje.getEnvioDeModEnvios());
            }
        });
    }
}
