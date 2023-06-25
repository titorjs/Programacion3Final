package interfaces.usuario;

import clases.Persona;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioInicio {
    private JPanel usuarioInicio;
    private JButton btnGenerarPedido;
    private JButton btnEstadoPaquete;
    private JButton btnCancelarEntrega;
    private JButton btnCambiarDatosEnvio;
    private JTextField txtNombreFIjo;
    private JButton btnCerrarSesion;


    public UsuarioInicio(Persona p) {
        txtNombreFIjo.setText(p.getNombre());
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(usuarioInicio);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });
    }

    /*
            Metodo get del panel
        */
    public JPanel getUsuarioInicio() {
        return usuarioInicio;
    }
}
