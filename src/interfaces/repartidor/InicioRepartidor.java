package interfaces.repartidor;

import clases.*;
import interfaces.Inicio;
import interfaces.chat.BuscarChats;
import sistema.mapa.Mapa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class InicioRepartidor {
    private JPanel InicioRepartidor;
    private JTabbedPane tabbedPane1;
    private JButton btnCerrarSesion;
    private JButton btnActualizarReportes;
    private JTextArea txaReportes;
    private JButton btnRuta;
    private JPanel mapaCanvas;
    private JButton btnAcutalizarCarga;
    private JTextArea txaCarga;
    private JButton btnChat;
    private JButton btnTerminarRuta;
    private Mapa m;
    private ArrayList<Direccion> puntos;
    private ArrayList<Envio> enDom;

    public InicioRepartidor(Persona p) {
        btnAcutalizarCarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txaCarga.setText("");
                Camion c = Inicio.sistema.buscarCamion(p);
                if (c == null) {
                    JOptionPane.showMessageDialog(null, "No está registrado como conductor de uno de los camiones designados");
                } else {
                    if (c.getCarga().isEmpty()) {
                        txaCarga.setText("No tiene carga asignada en el sistema");
                    } else {
                        for (Envio envio : c.getCarga()) {
                            txaCarga.append("Paquete " + envio.getId() +
                                    "\n" + envio.getPaquete() + "\n\n");
                        }
                    }
                }
            }
        });
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(InicioRepartidor);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });
        btnActualizarReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txaReportes.setText("");
                Stack<ReporteVia> reportes = new Stack<>();
                for (ReporteVia m : Inicio.sistema.getReporteVias()) {
                    reportes.push(m);
                }
                if (reportes.isEmpty())
                    txaReportes.setText("No hay nada que reportar");
                while (!reportes.isEmpty()) {
                    ReporteVia aux = reportes.pop();
                    txaReportes.append("Fecha: " + aux.getEnviado().toString() + "\n" +
                            "Dirección: x:" + (int) aux.getDireccion().getLongitud() + ", y:" + (int) aux.getDireccion().getLongitud() + "\n" +
                            "Descripción: " + aux.getMensaje() + "\n\n");
                }
            }
        });
        btnRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Camion c = Inicio.sistema.buscarCamion(p);
                if (c.getCarga().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay paquetes cargados");
                } else {
                    Sucursal su = (c.getCarga().first()).getSucursalEntrega();
                    Sucursales s = su.getZona();
                    m = Inicio.sistema.buscarMapa(s);

                    mapaCanvas.setLayout(new BorderLayout());
                    mapaCanvas.add(m, BorderLayout.CENTER);

                    puntos = new ArrayList<>();
                    Direccion inicio;
                    if (c == null) {
                        JOptionPane.showMessageDialog(null, "No está registrado como conductor de uno de los camiones designados");
                    } else {
                        if (c.getCarga().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No tiene carga asignada en el sistema");
                        } else {
                            inicio = su.getDireccion();

                            for (Envio envio : c.getCarga()) {
                                puntos.add(envio.getDireccionEntrega());
                            }

                            enDom = new ArrayList<>();

                            for (Envio en : Inicio.sistema.getEnvios()) {
                                if (en instanceof EnvioRecibidoDomicilio && en.getEstado() == 0) {
                                    puntos.add(((EnvioRecibidoDomicilio) en).getDireccionRecibida());
                                    enDom.add(en);
                                }
                            }

                            m.dibujarMapa();

                            m.imprimirRutaOptima(m.referenciaMasCercana(puntos), m.referenciaMasCercana(inicio));
                            for (Direccion d : m.referenciaMasCercana(puntos)) {
                                m.addDotVertex(d, Color.BLACK);
                            }
                            for (Direccion d : puntos) {
                                boolean recibir = false;
                                for (Envio en : enDom) {
                                    if (((EnvioRecibidoDomicilio) en).getDireccionRecibida().equals(d)) {
                                        recibir = true;
                                        break;
                                    }
                                }
                                if (recibir)
                                    m.addDotVertex(d, Color.BLUE);
                                else
                                    m.addDotVertex(d, Color.RED);
                            }

                            m.repaint();
                        }
                    }
                }
            }
        });
        btnChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(InicioRepartidor);
                este.setContentPane(new BuscarChats(p).getBuscarChat());
                este.revalidate();
            }
        });
        btnTerminarRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Camion c = Inicio.sistema.buscarCamion(p);
                if (c.getCarga().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay una ruta en curso");
                } else {
                    Sucursal su = (c.getCarga().first()).getSucursalEntrega();
                    Sucursales s = su.getZona();

                    for (Envio en : c.getCarga()) {
                        en.setEstado(5);
                    }
                    c.setCarga(new TreeSet<Envio>());

                    if (!enDom.isEmpty()) {
                        for (Envio en : enDom) {
                            en.setEstado(2);
                        }
                    }
                    enDom = new ArrayList<>();
                    m.limpiarCanvas();
                    JOptionPane.showMessageDialog(null, "Paquetes recogidos y entregados!");
                }
            }
        });
    }

    public JPanel getPanel() {
        return InicioRepartidor;
    }
}
