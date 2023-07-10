package interfaces.repartidor;

import clases.*;
import interfaces.Inicio;
import sistema.mapa.Mapa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

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
    private JComboBox cboRuta;
    private JSpinner spY;
    private JSpinner spX;
    private Mapa m;
    private ArrayList<Direccion> puntos;

    public InicioRepartidor(Persona p) {
        for (Sucursales s : Sucursales.values()) {
            cboRuta.addItem(s);
        }
        cboRuta.setSelectedIndex(-1);
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
                                    "\n" + envio.getPaquete());
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
                Sucursales s = (Sucursales) cboRuta.getSelectedItem();
                m = Inicio.sistema.buscarMapa(s);

                mapaCanvas.setLayout(new BorderLayout());
                mapaCanvas.add(m, BorderLayout.CENTER);

                Camion c = Inicio.sistema.buscarCamion(p);
                puntos = new ArrayList<>();
                Direccion inicio;
                float x = Float.parseFloat(spX.getValue().toString()), y = Float.parseFloat(spY.getValue().toString());
                if (x >= 0 && y >= 0 && x <= 1500 && y <= 1500) {
                    if (c == null) {
                        JOptionPane.showMessageDialog(null, "No está registrado como conductor de uno de los camiones designados");
                    } else {
                        if (c.getCarga().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No tiene carga asignada en el sistema");
                        } else {
                            inicio = new Direccion(y, x);

                            for (Envio envio : c.getCarga()) {
                                puntos.add(envio.getDireccionEntrega());
                            }
                            m.dibujarMapa();

                            m.imprimirRutaOptima(m.referenciaMasCercana(puntos), inicio);
                            for (Direccion d : m.referenciaMasCercana(puntos)) {
                                m.addDotVertex(d, Color.BLACK);
                            }
                            for (Direccion d : puntos) {
                                m.addDotVertex(d, Color.GREEN);
                            }

                            m.repaint();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese valores de dirección entre 0 y 1500");
                }
            }
        });
        cboRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRuta.setEnabled(true);
            }
        });
    }

    public JPanel getPanel() {
        return InicioRepartidor;
    }
}
