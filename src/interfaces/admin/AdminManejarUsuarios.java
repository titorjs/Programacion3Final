package interfaces.admin;

import interfaces.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminManejarUsuarios{
    private JPanel ManejarUsuarios;
    private JButton button1;
    private JButton button2;
    private Inicio inicio;

    public AdminManejarUsuarios() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarInicio();
            }
        });
    }

    private void cambiarInicio() {
        inicio = new Inicio();
        JFrame este = (JFrame) SwingUtilities.getWindowAncestor(ManejarUsuarios);
        este.setContentPane(inicio.getInicio());
        este.revalidate();
    }

    public JPanel getPanel(){
        return ManejarUsuarios;
    }


}
