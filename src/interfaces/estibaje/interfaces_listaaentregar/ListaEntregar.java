package interfaces.estibaje.interfaces_listaaentregar;

import clases.Camion;
import clases.Envio;
import clases.Sucursal;
import interfaces.Inicio;
import interfaces.estibaje.InicioEstibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaEntregar {
    private JPanel jpListaEntregar;
    private JButton btnEntregasQuitoN;
    private JButton btnEntregsaQuitoS;
    private JButton btnEntregasGuayaquil;
    private JButton btnEntregasDomingo;
    private JTextArea txtEntregasInfo;
    private JButton btnListaEntregaraEstibaje;

    public ListaEntregar() {
        /**
         * Redireccionar a incio de estibaje
         */
        btnListaEntregaraEstibaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(jpListaEntregar);
                este.setContentPane(new InicioEstibaje().getPanel());
                este.revalidate();
            }
        });
        btnEntregasQuitoN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosEntrega(2);
                Camion camionSucursales = Inicio.sistema.buscarCamion(2);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(2).getCarga();
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
                txtEntregasInfo.setText(text);
            }
        });
        btnEntregsaQuitoS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosEntrega(2);
                Camion camionSucursales = Inicio.sistema.buscarCamion(2);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(2).getCarga();
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
                txtEntregasInfo.setText(text);
            }
        });
        btnEntregasGuayaquil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosEntrega(2);
                Camion camionSucursales = Inicio.sistema.buscarCamion(2);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(2).getCarga();
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
                txtEntregasInfo.setText(text);
            }
        });
        btnEntregasDomingo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio.sistema.generarListaEnviosEntrega(2);
                Camion camionSucursales = Inicio.sistema.buscarCamion(2);
                ArrayList<Envio> listaEnvios = (ArrayList) Inicio.sistema.buscarCamion(2).getCarga();
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
                txtEntregasInfo.setText(text);
            }
        });
    }

    public JPanel getJpListaEntregar() {
        return jpListaEntregar;
    }
}
