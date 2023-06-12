package interfaces.estibaje.interfaces_ListasSucursales;

import clases.Camion;
import clases.Envio;
import clases.Sucursal;
import clases.Sucursales;
import interfaces.Inicio;
import interfaces.estibaje.InicioEstibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        btnVisualizarQNorte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosSucursal(1);
                Camion camionSucursales = Inicio.sistema.buscarCamion(1);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(1).getCarga();
                ArrayList<Envio> aux = new ArrayList<>();
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                String text ="";
                sucAux=Inicio.sistema.getSucursales();
                for (int i=0;i<listaEnvios.size();i++){
                    if (listaEnvios.get(i).getSucursalEntrega().equals(sucAux.get(0))){
                        aux.add(listaEnvios.get(i));
                    }
                }
                for (Envio lista:aux){
                    text = text+"Envio" +
                    "\nID: " + lista.getId() +
                            "\nestado: " + lista.getEstado() +
                            "\nfechaRecibido: " + lista.getFechaRecibido() +
                            "\nsolicitante: " + lista.getSolicitante() +
                            "\nreceptor: " + lista.getReceptor() +
                            "\npaquete: " + lista.getPaquete() +
                            "\nsucursalRecibida: " + lista.getSucursalRecibida() +
                            "\nsucursalEntrega: " + lista.getSucursalEntrega() +
                            "\ndireccionEntrega: " + lista.getDireccionEntrega();
                }
                txtAInfoEnviosSucursales.setText(text);
            }
        });

        btnVisualizarQSur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosSucursal(1);
                Camion camionSucursales = Inicio.sistema.buscarCamion(1);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(1).getCarga();
                ArrayList<Envio> aux = new ArrayList<>();
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                String text ="";
                sucAux=Inicio.sistema.getSucursales();
                for (int i=0;i<listaEnvios.size();i++){
                    if (listaEnvios.get(i).getSucursalEntrega().equals(sucAux.get(1))){
                        aux.add(listaEnvios.get(i));
                    }
                }
                for (Envio lista:aux){
                    text = text+"Envio" +
                            "\nID: " + lista.getId() +
                            "\nestado: " + lista.getEstado() +
                            "\nfechaRecibido: " + lista.getFechaRecibido() +
                            "\nsolicitante: " + lista.getSolicitante() +
                            "\nreceptor: " + lista.getReceptor() +
                            "\npaquete: " + lista.getPaquete() +
                            "\nsucursalRecibida: " + lista.getSucursalRecibida() +
                            "\nsucursalEntrega: " + lista.getSucursalEntrega() +
                            "\ndireccionEntrega: " + lista.getDireccionEntrega();
                }
                txtAInfoEnviosSucursales.setText(text);
            }
        });
        btnVisualizarGuayaquil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosSucursal(1);
                Camion camionSucursales = Inicio.sistema.buscarCamion(1);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(1).getCarga();
                ArrayList<Envio> aux = new ArrayList<>();
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                String text ="";
                sucAux=Inicio.sistema.getSucursales();
                for (int i=0;i<listaEnvios.size();i++){
                    if (listaEnvios.get(i).getSucursalEntrega().equals(sucAux.get(2))){
                        aux.add(listaEnvios.get(i));
                    }
                }
                for (Envio lista:aux){
                    text = text+"Envio" +
                            "\nID: " + lista.getId() +
                            "\nestado: " + lista.getEstado() +
                            "\nfechaRecibido: " + lista.getFechaRecibido() +
                            "\nsolicitante: " + lista.getSolicitante() +
                            "\nreceptor: " + lista.getReceptor() +
                            "\npaquete: " + lista.getPaquete() +
                            "\nsucursalRecibida: " + lista.getSucursalRecibida() +
                            "\nsucursalEntrega: " + lista.getSucursalEntrega() +
                            "\ndireccionEntrega: " + lista.getDireccionEntrega();
                }
                txtAInfoEnviosSucursales.setText(text);
            }
        });
        btnVisualizarDomingo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosSucursal(1);
                Camion camionSucursales = Inicio.sistema.buscarCamion(1);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(1).getCarga();
                ArrayList<Envio> aux = new ArrayList<>();
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                String text ="";
                sucAux=Inicio.sistema.getSucursales();
                for (int i=0;i<listaEnvios.size();i++){
                    if (listaEnvios.get(i).getSucursalEntrega().equals(sucAux.get(3))){
                        aux.add(listaEnvios.get(i));
                    }
                }
                for (Envio lista:aux){
                    text = text+"Envio" +
                            "\nID: " + lista.getId() +
                            "\nestado: " + lista.getEstado() +
                            "\nfechaRecibido: " + lista.getFechaRecibido() +
                            "\nsolicitante: " + lista.getSolicitante() +
                            "\nreceptor: " + lista.getReceptor() +
                            "\npaquete: " + lista.getPaquete() +
                            "\nsucursalRecibida: " + lista.getSucursalRecibida() +
                            "\nsucursalEntrega: " + lista.getSucursalEntrega() +
                            "\ndireccionEntrega: " + lista.getDireccionEntrega();
                }
                txtAInfoEnviosSucursales.setText(text);
            }
        });
    }
}
