package interfaces.conductor;

import clases.*;
import interfaces.Inicio;
import sistema.mapa.Mapa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.Stack;

public class InicioConductor {
    private JPanel InicioConducto;
    private JTabbedPane tabbedPane1;
    private JButton btnCerrarSesion;
    private JButton btnAlerta;
    private JButton btnActualizarReportes;
    private JTextArea txaReportes;
    private JButton btnActualizarCarga;
    private JTextArea txtCarga;

    public InicioConductor(Persona p) {


        /** Cerrar sesión */
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(InicioConducto);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });
        /** Generar reportePanico */
        btnAlerta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reporte r = new Reporte(p, LocalDateTime.now(), "botonPanico",true);
                Inicio.sistema.agregarMensaje(r);
            }
        });
        /** Mostrar reportes */
        btnActualizarReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txaReportes.setText("");
                Stack<Reporte> reportes = new Stack<>();
                for(Mensaje m: Inicio.sistema.getMensajes()){
                    if (m instanceof Reporte){
                        if (!((Reporte) m).isPanico()){
                            reportes.push((Reporte) m);
                        }
                    }
                }
                if (reportes.isEmpty())
                    txaReportes.setText("No hay nada que reportar");
                while (!reportes.isEmpty()){
                    Reporte aux = reportes.pop();
                    txaReportes.append("Fecha: " + aux.getEnviado().toString() + "\n" +
                                       "Descripción: " + aux.getMensaje() + "\n\n");
                }
            }
        });
        btnActualizarCarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCarga.setText("");
                Camion c = Inicio.sistema.buscarCamion(p);
                if (c == null){
                    JOptionPane.showMessageDialog(null, "No está registrado como conductor de uno de los camiones designados");
                } else {
                    if(c.getCarga().isEmpty()){
                        txtCarga.setText("No tiene carga asignada en el sistema");
                    } else {
                        for (Envio envio: c.getCarga()){
                            txtCarga.append("Paquete " + envio.getId() +
                                            "\n" + envio.getPaquete());
                        }
                    }
                }
            }
        });
    }
    public JPanel getPanel(){
        return InicioConducto;
    }
}
