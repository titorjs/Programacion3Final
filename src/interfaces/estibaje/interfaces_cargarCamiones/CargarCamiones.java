package interfaces.estibaje.interfaces_cargarCamiones;

import clases.Camion;
import clases.Envio;
import clases.Persona;
import clases.Sucursal;
import interfaces.Inicio;
import interfaces.estibaje.InicioEstibaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class CargarCamiones {
    private JPanel cargarCamiones;
    private JTabbedPane tabbedPane1;
    private JList jlEnviosCD;
    private JButton btnCargarEnvioDomicilio;
    private JButton btnRegresarCD;
    private JList jlEnviosCS;
    private JButton btnCargarEnvioSucursal;
    private JButton btnRegresarCS;
    private JButton btnMostrarEQN;
    private JButton btnMostrarEQS;
    private JButton btnMostrarEG;
    private JButton btnMostrarESTD;
    private JButton btnDQN;
    private JButton btnDQS;
    private JButton btnDGYE;
    private JButton btnDSTD;
    private JList jlDescargaS;
    private JButton btnDescargarS;
    private JButton btnRegresarDescargaS;
    private JButton btnEnviosCargaDomicilioQN;
    private JButton btnEnviosDomicilioQS;
    private JButton btnEnviosDomicilioGYE;
    private JButton btnEnviosSTD;
    private int actualizar;
    TreeSet<Envio> listaEnvios;
    TreeSet<Envio> listaDomicilio;
    DefaultListModel<Envio> listModelD;
    DefaultListModel<Envio> listModel;

    public CargarCamiones(Persona p) {
        mostrarEnviosADomicilio();
        Inicio.sistema.generarListaEnviosEntrega(2);
        Inicio.sistema.generarListaEnviosSucursal(1);
        Camion camionSucursales = Inicio.sistema.buscarCamion(1);
        Camion camionDomicilio = Inicio.sistema.buscarCamion(2);
        listaDomicilio = (TreeSet<Envio>) camionDomicilio.getCarga();
        listaEnvios = (TreeSet<Envio>) camionSucursales.getCarga();
        listModelD = new DefaultListModel<>();
        listModel = new DefaultListModel<>();

        btnCargarEnvioDomicilio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Envio envioSeleccionado= (Envio) jlEnviosCD.getSelectedValue();
                mostrarEnviosADomicilio();
            }
        });
        btnRegresarCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cargarCamiones);
                este.setContentPane(new InicioEstibaje(p).getPanel());
                este.revalidate();
            }
        });
        btnCargarEnvioSucursal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Envio envioSeleccionado= (Envio) jlEnviosCS.getSelectedValue();
                listModel.removeElement(envioSeleccionado);
                Inicio.sistema.agregarEnvioCamion(3,envioSeleccionado);
                if (actualizar==1){
                    mostrarEnviosASucursalQN();
                }else if (actualizar==2){
                    mostrarEnviosASucursalQS();
                } else if (actualizar==3) {
                    mostrarEnviosASucursalGYE();
                }else {
                    mostrarEnviosASucursalGYE();
                }
            }
        });
        btnRegresarCS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cargarCamiones);
                este.setContentPane(new InicioEstibaje(p).getPanel());
                este.revalidate();
            }
        });
        btnMostrarEQN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosASucursalQN();
                actualizar=1;
            }
        });
        btnMostrarEQS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosASucursalQS();
                actualizar=2;
            }
        });
        btnMostrarEG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosASucursalGYE();
                actualizar=3;
            }
        });
        btnMostrarESTD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosASucursalSTD();
                actualizar=4;
            }
        });
        btnDQN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosDescargaQn();
            }
        });
        btnDQS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosDescargaQS();
            }
        });
        btnDGYE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosDescargaGYE();
            }
        });
        btnDSTD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEnviosDescargaSTD();
            }
        });
        btnDescargarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Envio envioSeleccionado= (Envio) jlDescargaS.getSelectedValue();
                envioSeleccionado.setEstado(3);
                Inicio.sistema.eliminarEnvioCamion(3,envioSeleccionado);
                if (actualizar==1){
                    mostrarEnviosDescargaQn();
                }else if (actualizar==2){
                    mostrarEnviosDescargaQS();
                } else if (actualizar==3) {
                    mostrarEnviosDescargaGYE();
                }else {
                    mostrarEnviosDescargaSTD();
                }
            }
        });
        btnRegresarDescargaS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(cargarCamiones);
                este.setContentPane(new InicioEstibaje(p).getPanel());
                este.revalidate();
            }
        });
        btnEnviosCargaDomicilioQN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                sucAux = Inicio.sistema.getSucursales();
                for (Envio envio : listaDomicilio) {
                    if (envio.getSucursalEntrega().equals(sucAux.get(0))) {
                        listModelD.addElement(envio);
                    }
                }
                jlEnviosCD.setModel(listModelD);
            }
        });
        btnEnviosDomicilioQS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                sucAux = Inicio.sistema.getSucursales();
                for (Envio envio : listaDomicilio) {
                    if (envio.getSucursalEntrega().equals(sucAux.get(1))) {
                        listModelD.addElement(envio);
                    }
                }
                jlEnviosCD.setModel(listModelD);
            }
        });
        btnEnviosDomicilioGYE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                sucAux = Inicio.sistema.getSucursales();
                for (Envio envio : listaDomicilio) {
                    if (envio.getSucursalEntrega().equals(sucAux.get(2))) {
                        listModelD.addElement(envio);
                    }
                }
                jlEnviosCD.setModel(listModelD);
            }
        });
        btnEnviosSTD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Sucursal> sucAux = new ArrayList<>();
                sucAux = Inicio.sistema.getSucursales();
                for (Envio envio : listaDomicilio) {
                    if (envio.getSucursalEntrega().equals(sucAux.get(3))) {
                        listModelD.addElement(envio);
                    }
                }
                jlEnviosCD.setModel(listModelD);
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
    public void mostrarEnviosASucursalQN(){
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        for (Envio envio : listaEnvios) {
            if (envio.getSucursalEntrega().equals(sucAux.get(0))) {
                listModel.addElement(envio);
            }
        }
        jlEnviosCS.setModel(listModel);
    }
    public void mostrarEnviosASucursalQS(){
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        for (Envio envio : listaEnvios) {
            if (envio.getSucursalEntrega().equals(sucAux.get(1))) {
                listModel.addElement(envio);
            }
        }
        jlEnviosCS.setModel(listModel);
    }
    public void mostrarEnviosASucursalGYE(){
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        for (Envio envio : listaEnvios) {
            if (envio.getSucursalEntrega().equals(sucAux.get(2))) {
                listModel.addElement(envio);
            }
        }
        jlEnviosCS.setModel(listModel);
    }
    public void mostrarEnviosASucursalSTD(){
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        for (Envio envio : listaEnvios) {
            if (envio.getSucursalEntrega().equals(sucAux.get(3))) {
                listModel.addElement(envio);
            }
        }
        jlEnviosCS.setModel(listModel);
    }
    public void mostrarEnviosDescargaQn(){
        Camion camionAux = Inicio.sistema.buscarCamion(3);
        TreeSet<Envio> cargaCamion= (TreeSet<Envio>) camionAux.getCarga();
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        DefaultListModel<Envio> listaCarga = new DefaultListModel<>();
        for (Envio envio : cargaCamion){
            if (envio.getSucursalEntrega().equals(sucAux.get(0))) {
                listaCarga.addElement(envio);
            }
        }
        jlDescargaS.setModel(listaCarga);
        actualizar=1;
    }
    public void mostrarEnviosDescargaQS(){
        Camion camionAux = Inicio.sistema.buscarCamion(3);
        TreeSet<Envio> cargaCamion= (TreeSet<Envio>) camionAux.getCarga();
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        DefaultListModel<Envio> listaCarga = new DefaultListModel<>();
        for (Envio envio : cargaCamion){
            if (envio.getSucursalEntrega().equals(sucAux.get(1))) {
                listaCarga.addElement(envio);
            }
        }
        jlDescargaS.setModel(listaCarga);
        actualizar=2;
    }
    public void mostrarEnviosDescargaGYE(){
        Camion camionAux = Inicio.sistema.buscarCamion(3);
        TreeSet<Envio> cargaCamion= (TreeSet<Envio>) camionAux.getCarga();
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        DefaultListModel<Envio> listaCarga = new DefaultListModel<>();
        for (Envio envio : cargaCamion){
            if (envio.getSucursalEntrega().equals(sucAux.get(2))) {
                listaCarga.addElement(envio);
            }
        }
        jlDescargaS.setModel(listaCarga);
        actualizar=3;
    }
    public void mostrarEnviosDescargaSTD(){
        Camion camionAux = Inicio.sistema.buscarCamion(3);
        TreeSet<Envio> cargaCamion= (TreeSet<Envio>) camionAux.getCarga();
        ArrayList<Sucursal> sucAux = new ArrayList<>();
        sucAux = Inicio.sistema.getSucursales();
        DefaultListModel<Envio> listaCarga = new DefaultListModel<>();
        for (Envio envio : cargaCamion){
            if (envio.getSucursalEntrega().equals(sucAux.get(3))) {
                listaCarga.addElement(envio);
            }
        }
        jlDescargaS.setModel(listaCarga);
        actualizar=4;
    }
    public void mostrarEnviosDomicilioQN(){

    }
    public void mostrarEnviosDomicilioQS(){

    }
    public void mostrarEnviosDomicilioGYE(){

    }
    public void mostrarEnviosDomicilioSTD(){

    }
}
