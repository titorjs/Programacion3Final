package interfaces.estibaje.interfaces_ListasSucursales;

import interfaces.estibaje.InicioEstibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaSucursales {
    private JPanel jpListaSucursales;
    private JButton btnVisualizarQNorte;
    private JButton btnVisualizarQSur;
    private JButton btnVisualizarDomingo;
    private JButton btnVisualizarGuayaquil;
    private JTextArea txtAInfoEnviosSucursales;
    private JButton btnLSucursalesaEstibaje;

    /**
     * metodo get para obtener el panel
     * @return
     */

    public JPanel getJpListaSucursales() {
        return jpListaSucursales;
    }

    public ListaSucursales() {
        /**
         * Redireccionar al Inicio de estibaje
         */
    btnLSucursalesaEstibaje.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpListaSucursales);
            este.setContentPane(new InicioEstibaje().getPanel());
            este.revalidate();
        }
    });

}
}
