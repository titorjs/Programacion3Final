package interfaces.estibaje.interfaces_listaaentregar;

import interfaces.estibaje.InicioEstibaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    public JPanel getJpListaEntregar() {
        return jpListaEntregar;
    }
}
