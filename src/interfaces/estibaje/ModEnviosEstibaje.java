package interfaces.estibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModEnviosEstibaje {
    private JPanel jpModEnviosEstibaje;
    private JButton btnBuscarEnvio;
    private JTextField txtBuscarEnvio;
    private JButton btnRegresarInicioEstibaje;
    private JTextArea txtAInfoEnvio;
    private JButton btnModReceptor;
    private JButton btnModPaquete;
    private JButton btnModDireccion;

    public ModEnviosEstibaje() {
        btnBuscarEnvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel(){
        return jpModEnviosEstibaje;
    }


}
