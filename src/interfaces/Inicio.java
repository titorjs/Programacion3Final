package interfaces;
import interfaces.admin.AdminManejarUsuarios;
import sistema.Sistema;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JPanel inicio;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JButton soloQuieresVerUnButton;
    private JButton eresEmpleadoClickAquíButton;
    private AdminManejarUsuarios adminPanel;

    public Inicio() {
        soloQuieresVerUnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(inicio);
                este.setContentPane(new AdminManejarUsuarios().getPanel());
                este.revalidate();
            }
        });

        /*
        Aquí se maneja el ingreso para usuarios y el caso especial del admin
         */
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getInicio(){
        return inicio;
    }

    public static void main(String[] args) {
        /**
         * A través de esta clase se manejarán todos los métodos y necesidades de la interfaz
         * gráfica
         */
        Sistema sistema = new Sistema();

        JFrame frame = new JFrame("Transcormogal");
        frame.setContentPane(new Inicio().getInicio());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
