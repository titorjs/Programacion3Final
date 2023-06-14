package interfaces.estibaje.interfaces_modenvios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clases.Envio;
import interfaces.Inicio;
import interfaces.estibaje.InicioEstibaje;

public class ModEnviosEstibaje {
    private JPanel jpModEnviosEstibaje;
    private JButton btnBuscarEnvio;
    private JTextField txtBuscarEnvio;
    private JButton btnRegresarInicioEstibaje;
    private JTextArea txtAInfoEnvio;
    private JButton btnModReceptor;
    private JButton btnModPaquete;
    private JButton btnModDireccion;
    private JButton btnCambiarSucursalEntrega;
    private Envio envio;

    public ModEnviosEstibaje() {
        /** !!! Hacer que si el paquete a modificar es null, no sea visible los botones */

        envio = null;

        /**
         * Redireccion a la interfaz Inicio Estibaje
         */
        btnRegresarInicioEstibaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModEnviosEstibaje);
                este.setContentPane(new InicioEstibaje().getPanel());
                este.revalidate();
            }
        });
        /**
         * Boton que sirve para buscar el envio en base al codigo
         * Si el Estado del obtenido es un estado invalido osea estados 4 o mayores
         * aparece un mensaje en pantalla con la opcion de crear un nuevo envio
         */
        btnBuscarEnvio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                envio = Inicio.sistema.buscarEnvio(Integer.parseInt(txtBuscarEnvio.getText()));

                /**
                 * Se valida el estado en el que se encuentra el envio
                 */
                if (envio!=null){
                    if (envio.getEstado() >= 4) {
                        int opcion = JOptionPane.showOptionDialog(
                                null, "No es posible realizar cambios,¿desea crear un nuevo envio?",
                                "Confirmacion", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (opcion == JOptionPane.YES_OPTION) {
                            /**Redirigir a una nueva pestaña para crear envios y
                             * tomar como base el pedido anterior que se cancela */
                        }
                        /**
                         * En caso de que sea posible modificar se muestra la informacion del paquete
                         * en el textArea.
                         */
                    } else {
                        btnModReceptor.setEnabled(true);
                        btnModPaquete.setEnabled(true);
                        btnModDireccion.setEnabled(true);
                        btnCambiarSucursalEntrega.setEnabled(true);
                        txtAInfoEnvio.setText(envio.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"No existe ese envio");
                }
                }

        });
        /**
         * Redireccion a la Pestaña para modificar la Persona receptora
         */
        btnModReceptor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModEnviosEstibaje);
                este.setContentPane(new PestañaModReceptor(envio).getJpPestañaModReceptor());
                este.revalidate();
            }
        });
        /**
         * Redireccion a la Pestaña para modificar la direccion
         */
        btnModDireccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModEnviosEstibaje);
                este.setContentPane(new PestañaModDireccion(envio).getJpPestañaModDireccion());
                este.revalidate();
            }
        });
        /**
         * Redireccion a la Pestaña de modificar sucursal
         */
        btnCambiarSucursalEntrega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModEnviosEstibaje);
                este.setContentPane(new PestañaModSucursal(envio).getJpModSucursal());
                este.revalidate();

            }
        });
        btnModPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModEnviosEstibaje);
                este.setContentPane(new PestañaModPaquete(envio).getJpModPaquete());
                este.revalidate();
            }
        });
    }

    /**
     * Metodo get del panel
     *
     * @return El panel en el que estemos
     */
    public JPanel getPanel() {
        return jpModEnviosEstibaje;
    }

    public Envio getEnvioDeModEnvios() {
        return envio;
    }

}
