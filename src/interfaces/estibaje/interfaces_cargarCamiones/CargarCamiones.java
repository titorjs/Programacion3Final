package interfaces.estibaje.interfaces_cargarCamiones;

import clases.Envio;
import interfaces.Inicio;
import interfaces.estibaje.InicioEstibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SortedSet;

public class CargarCamiones {
    private JPanel cargarCamiones;
    private JTabbedPane tabbedPane1;
    private JList jlEnviosCD;
    private JButton btnCargarEnvioDomicilio;
    private JButton btnRegresarCD;
    private JList jlEnviosCS;
    private JButton btnCargarEnvioSucursal;
    private JButton btnRegresarCS;

    public CargarCamiones() {
        mostrarEnviosASucursal();
        mostrarEnviosADomicilio();
        btnCargarEnvioDomicilio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Envio envioSeleccionado= (Envio) jlEnviosCD.getSelectedValue();
                envioSeleccionado.setEstado(4);
                mostrarEnviosADomicilio();
            }
        });
        btnRegresarCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cargarCamiones);
                este.setContentPane(new InicioEstibaje().getPanel());
                este.revalidate();
            }
        });
        btnCargarEnvioSucursal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Envio envioSeleccionado= (Envio) jlEnviosCS.getSelectedValue();
                envioSeleccionado.setEstado(3);
                mostrarEnviosASucursal();
            }
        });
        btnRegresarCS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cargarCamiones);
                este.setContentPane(new InicioEstibaje().getPanel());
                este.revalidate();
            }
        });
    }

    public JPanel getCargarCamiones(){
        return cargarCamiones;
    }
    public void mostrarEnviosADomicilio(){
        DefaultListModel<Envio> listModel = new DefaultListModel<>();
        SortedSet<Envio> envios =Inicio.sistema.getEnvios();
        for (Envio envio:envios){
            if (envio.getEstado()==3){
                listModel.addElement(envio);
            }
        }
        jlEnviosCD.setModel(listModel);
    }
    public void mostrarEnviosASucursal(){
        DefaultListModel<Envio> listModel = new DefaultListModel<>();
        SortedSet<Envio> envios =Inicio.sistema.getEnvios();
        for (Envio envio:envios){
            if (envio.getEstado()<=2){
                listModel.addElement(envio);
            }
        }
        jlEnviosCS.setModel(listModel);
    }
}
