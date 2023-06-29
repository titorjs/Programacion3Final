package interfaces.estibaje;

import interfaces.estibaje.interfaces_ListasSucursales.ListaSucursales;
import interfaces.estibaje.interfaces_camestados.EstadoPaquete;
import interfaces.estibaje.interfaces_listaaentregar.ListaEntregar;
import interfaces.estibaje.interfaces_modenvios.ModEnviosEstibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioEstibaje {

    private JPanel principal;
    private JLabel imgAvatar;
    private JButton btnInterfazModificarEnvios;
    private JButton btnEstadoPaquete;
    private JButton btnListarPSucursal;
    private JButton btnListaEnvios;

    public InicioEstibaje() {
        /**
         * Redireccion a al Incio de modificar envios de estibaje
         */
        btnInterfazModificarEnvios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame esto = (JFrame) SwingUtilities.getWindowAncestor(principal);
                esto.setContentPane(new ModEnviosEstibaje().getPanel());
                esto.revalidate();
            }
        });
        /**
         * Redireccion a Cambiar el estado
         */
        btnEstadoPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este=(JFrame) SwingUtilities.getWindowAncestor(principal);
                este.setContentPane(new EstadoPaquete().getJpCambiarEstado());
                este.revalidate();
            }
        });
        /**
         * Redireccionar a pestaña para visualizar la lista de envios entre sucursales
         */
        btnListarPSucursal.addActionListener(new
                                                     ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(principal);
                este.setContentPane(new ListaSucursales().getJpListaSucursales());
                este.revalidate();
            }
        });
        /**
         * Redireccionar a pestaña para visualizar la lista de envios por entregar
         */
        btnListaEnvios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(principal);
                este.setContentPane(new ListaEntregar().getJpListaEntregar());
                este.revalidate();
            }
        });
    }

    public JPanel getPanel(){
        return principal;
    }
}
