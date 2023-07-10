package interfaces.estibaje.interfaces_modenvios;

import clases.Direccion;
import clases.Envio;
import clases.Persona;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModDireccion {
    private JTextField txtCx;
    private JTextField txtCy;
    private JButton btnCambiarDireccion;
    private JButton btnDiraModEnvios;
    private JPanel jpPestañaModDireccion;
    private ModEnviosEstibaje modEnviosEstibaje;

    public PestañaModDireccion(Envio envio, Persona p) {
        btnDiraModEnvios.addActionListener(new ActionListener() {
            /**
             * Redireccion a la pestaña de modificar envios
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpPestañaModDireccion);
                este.setContentPane(new ModEnviosEstibaje(p).getPanel());
                este.revalidate();
            }
        });
        /**
         * Boton para Cambiar la direccion de entrega del envio que queriamos modifficar
         */
        btnCambiarDireccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Direccion d = new Direccion(Double.parseDouble(txtCx.getText()),Double.parseDouble(txtCy.getText()));
                envio.setDireccionEntrega(d);
                JOptionPane.showMessageDialog(null,"Se cambio correctamente");
            }
        });
    }

    /**
     * Metodo get para obtener el panel
     * @return
     */

    public JPanel getJpPestañaModDireccion() {
        return jpPestañaModDireccion;
    }
}
