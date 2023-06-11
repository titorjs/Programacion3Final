package interfaces.estibaje.interfaces_camestados;

import clases.Envio;
import interfaces.Inicio;
import interfaces.estibaje.InicioEstibaje;
import interfaces.estibaje.interfaces_modenvios.PestañaModSucursal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstadoPaquete {
    private JPanel jpCambiarEstado;
    private JButton btnEstadoBuscar;
    private JSpinner spEstadoCodEnvio;
    private JPanel jpCambioEstado;
    private JComboBox cboActualizarEstado;
    private JButton btnActualizarEstado;
    private JButton btnCamEstadoaInicio;

    public EstadoPaquete() {
        btnCamEstadoaInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpCambiarEstado);
                este.setContentPane(new InicioEstibaje().getPanel());
                este.revalidate();
            }
        });
        btnEstadoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Inicio.sistema.buscarEnvio((int)spEstadoCodEnvio.getValue())!=null){
                    jpCambioEstado.setVisible(true);
                    cboActualizarEstado.setModel(actualizrComboBox());
                } else {
                    JOptionPane.showMessageDialog(null,"No existe envio con ese codigo");
                }
            }
        });
        /**
         * Boton para cambiar el estado del envio
         */
        btnActualizarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Inicio.sistema.cambiarEstado((Inicio.sistema.buscarEnvio((int)spEstadoCodEnvio.getValue())),cboActualizarEstado.getSelectedItem().toString());
                    cboActualizarEstado.setModel(actualizrComboBox());
                    JOptionPane.showMessageDialog(null, "Se cambio correctamente");
            }
        });
    }

    /**
     * Metodo para actualizar el combo box con las opciones posteriores y no anteriores al proceso
     * @return un DefaultComboBoxModel de String para poder cambiar el contenido del combobox
     */
    public DefaultComboBoxModel<String> actualizrComboBox(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel<>();
        String[] estados ={"Retirar en el domicilio",
                "Se recibió en oficina",
                "Enviandose a la sucursal correcta",
                "En sucursal correcta",
                "En curso a domicilio",
                "Entregado"};
        for (int i =Inicio.sistema.buscarEnvio((int)spEstadoCodEnvio.getValue()).getEstado();i<=estados.length;i++){
            modelo.addElement(estados[i]);
        }
        return modelo;
    }

    public JPanel getJpCambiarEstado() {
        return jpCambiarEstado;
    }
}
