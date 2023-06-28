package interfaces.usuario;

import clases.Envio;
import clases.Persona;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class estadoPaquete {
    private JPanel estadoPaquete;
    private JComboBox cboIDPaquete;
    private JButton btnVisualizarPaquete;
    private JTextArea txtEstadoPaquete;
    private JButton btnRegresar;
    private Persona usuario;
public estadoPaquete(Persona p) {
    usuario=p;
    actualizarCboId();
    /*
        Redirecciona a Interfaz Inicio Usuario
     */
    btnRegresar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame este = (JFrame) SwingUtilities.getWindowAncestor(estadoPaquete);
            este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
            este.revalidate();
        }
    });
    /*
        Muestra el estado del paquete
     */
    btnVisualizarPaquete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Envio> lista=Inicio.sistema.enviosUsuario(usuario);
            //Valida si el usuario tiene envios en el sistema
            if (lista.isEmpty()){
                JOptionPane.showMessageDialog(null,"Todavía no tiene envíos agregados, debe generar un envio para visualizar el estado de su paquete");
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(estadoPaquete);
                este.setContentPane(new UsuarioInicio(p).getUsuarioInicio());
                este.revalidate();
            }else{
                int id=Integer.parseInt(cboIDPaquete.getSelectedItem().toString());
                Envio sapo=Inicio.sistema.buscarEnvio(id);
                int estado=sapo.getEstado();
                String state=Inicio.sistema.mostrarEstadoPaquete(estado);
                txtEstadoPaquete.setText("Usuario: "+usuario.getNombre()+"\n"+
                "Estado Paquete: "+state);
            }
        }
    });
}
    /*
    Método get del panel
     */
    public JPanel getEstadoPaquete() {
        return estadoPaquete;
    }
    /*
        Método para agregar los ID's del usuario al combobox
     */
    public void actualizarCboId (){
        ArrayList<Envio> envios = Inicio.sistema.enviosUsuario(usuario);
        for (Envio e: envios){
            int id=e.getId();
            cboIDPaquete.addItem(id);
        }
    }
}
