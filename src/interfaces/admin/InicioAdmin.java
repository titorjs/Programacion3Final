package interfaces.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;
import clases.Persona;

public class InicioAdmin {
    private JPanel inicioAdmin;
    private JTabbedPane menuAdmin;
    private JPanel administrarUsuarios;
    private JPanel gestionarReportes;
    private JPanel verMensajes;
    private JComboBox cboFitroUsuarios;
    private JPanel filtroTipoCuenta;
    private JCheckBox ckbFiltroRepartidor;
    private JCheckBox ckbFiltroUsuario;
    private JCheckBox ckbFiltroEstibaje;
    private JCheckBox ckbFiltroConductor;
    private JCheckBox ckbFiltroEjecutivo;
    private JTextField txtFiltroCedula;
    private JPanel filtroCedula;
    private JTextField textField1;
    private JList listaUsuarios;
    private JButton btnBorrarFiltros;
    private JPanel filtroNombre;

    public InicioAdmin() {
    btnBorrarFiltros.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**
             * Reiniciar los parámetros por defecto de las opciones de busqueda
             */
            cboFitroUsuarios.setSelectedIndex(-1);
            filtroCedula.setVisible(false);
            filtroCedula.setEnabled(false);
            filtroTipoCuenta.setVisible(false);
            filtroTipoCuenta.setEnabled(false);
            filtroNombre.setVisible(false);
            filtroNombre.setEnabled(false);

            /**
             * Actualizar la lista
             */

        }
    });
}

    /**
     * Método para actualizar la lista
     * @param lista lista con los
     */
    public void actualizarLista(SortedSet<Persona> lista){
        DefaultListModel<Persona> modeloLista = new DefaultListModel<>();
        modeloLista.addAll(0, lista);
        listaUsuarios.setModel(modeloLista);
    }
}
