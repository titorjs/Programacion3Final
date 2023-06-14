package interfaces.estibaje.interfaces_modenvios;

import clases.Envio;
import clases.Sucursal;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PestañaModSucursal {
    private JPanel jpModSucursal;
    private JComboBox cboModSucursal;
    private JButton btnModificarSucursal;
    private JButton btnSucaEnvios;

    public JPanel getJpModSucursal() {
        return jpModSucursal;
    }

    public PestañaModSucursal( Envio envio) {
        /**
         * Redireccionar a modificar envios
         */
        btnSucaEnvios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpModSucursal);
                este.setContentPane(new ModEnviosEstibaje().getPanel());
                este.revalidate();
            }
        });
        /**
         * Modificar la sucursal Dependiendo la sucursal seleccionada del combobox
         */
    btnModificarSucursal.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cboModSucursal.getSelectedItem().toString().compareToIgnoreCase("QUITONORTE")==0){
                Sucursal s = Inicio.sistema.getSucursales().get(0);
                Inicio.sistema.cambiarSucursalEntrega(s,envio);
                JOptionPane.showMessageDialog(null,"Se modifico correctamente");
            } else if (cboModSucursal.getSelectedItem().toString().compareToIgnoreCase("QUITOSUR")==0) {
                Sucursal s = Inicio.sistema.getSucursales().get(1);
                Inicio.sistema.cambiarSucursalEntrega(s,envio);
                JOptionPane.showMessageDialog(null,"Se modifico correctamente");
            } else if (cboModSucursal.getSelectedItem().toString().compareToIgnoreCase("GUAYAQUIL")==0) {
                Sucursal s = Inicio.sistema.getSucursales().get(2);
                Inicio.sistema.cambiarSucursalEntrega(s,envio);
                JOptionPane.showMessageDialog(null,"Se modifico correctamente");
            } else {
                Sucursal s = Inicio.sistema.getSucursales().get(3);
                Inicio.sistema.cambiarSucursalEntrega(s,envio);
                JOptionPane.showMessageDialog(null,"Se modifico correctamente");
            }

        }
    });



    }
}
