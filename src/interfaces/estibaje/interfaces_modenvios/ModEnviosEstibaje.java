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
                /**
                 * Se valida el estado en el que se encuentra el envio
                 */
                if (Inicio.sistema.buscarEnvio(Integer.parseInt(txtBuscarEnvio.getText())).getEstado()>=4){
                    int opcion = JOptionPane.showOptionDialog(
                            null, "No es posible realizar cambios,¿desea crear un nuevo envio?",
                            "Confirmacion",JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (opcion == JOptionPane.YES_OPTION){
                        //Redirigir a una nueva pestaña para crear envios
                    } else {
                        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModEnviosEstibaje);
                        este.setContentPane(new ModEnviosEstibaje().getPanel());
                        este.revalidate();
                    }
                    /**
                     * En caso de que sea posible modificar se muestra la informacion del paquete
                     * en el textArea.
                      */
                } else {
                    envio = Inicio.sistema.buscarEnvio(Integer.parseInt(txtBuscarEnvio.getText()));
                    txtAInfoEnvio.setText(envio.toString());
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
                este.setContentPane(new PestañaModReceptor().getJpPestañaModReceptor());
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
                este.setContentPane(new PestañaModDireccion().getJpPestañaModDireccion());
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
                este.setContentPane(new PestañaModSucursal().getJpModSucursal());
                este.revalidate();

            }
        });
    }

    /**
     * Metodo get del panel
     * @return El panel en el que estemos
     */
    public JPanel getPanel(){
        return jpModEnviosEstibaje;
    }
    public Envio getEnvioDeModEnvios(){
        return envio;
    }

}
