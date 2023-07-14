package interfaces.usuario;

import clases.Envio;
import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verPaquete {
    private JPanel verPaquete;
    private JTextArea jtextAreaVisualizar;
    private JButton btnBuscar;
    private JTextField txtPaquteVisualizar;
    private JButton btnRegresar;

    public JPanel getVerPaquete() {
        return verPaquete;
    }

    public verPaquete() {
    btnBuscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             try{
                 /**
                  * Busca el envio en base a su id realizando las previas validaciones, una vez encontrado el envio
                  * se muestra el detalle de Receptor y el estado del paquete
                  */
                 int id = Integer.parseInt(txtPaquteVisualizar.getText());
                 Envio env = Inicio.sistema.buscarEnvio(id);
                 if (env!=null){
                     int estado=env.getEstado();
                     String state=Inicio.sistema.mostrarEstadoPaquete(estado);
                     jtextAreaVisualizar.setText("Receptor: "+env.getReceptor().getNombre()+"\n"+
                             "Estado Paquete: "+state);
                 }else {
                     JOptionPane.showMessageDialog(null, "No existe envio con ese ID");
                 }

             }catch (NumberFormatException x){
                 int id = -1;
                 if (id==-1){
                     JOptionPane.showMessageDialog(null, "Porfavor, llene los campos correctamente");
                 }
             }

        }
    });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(verPaquete);
                este.setContentPane(new Inicio().getPanel());
                este.revalidate();
            }
        });
    }

}
