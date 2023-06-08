package interfaces;
import sistema.Sistema;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JPanel inicio;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton btnIngresar;
    private JButton btnVerPaquete;
    private JButton btnLoginEmpleado;
    private JButton btnSolicitarCuenta;
    public static Sistema sistema;

    public Inicio() {
        btnVerPaquete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /*
        Aquí se maneja el ingreso para usuarios
         */
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnLoginEmpleado.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Redirección a interfaz LoginEmpleados
                 */
                JFrame este = (JFrame) SwingUtilities.getWindowAncestor(inicio);
                este.setContentPane(new LoginEmpleados().getPanel());
                este.revalidate();
            }
        });
    }

    public JPanel getPanel(){

        return inicio;
    }

    public static void main(String[] args) {
        /**
         * A través de esta clase se manejarán todos los métodos y necesidades de la interfaz
         * gráfica
         */

        sistema = new Sistema();

        JFrame frame = new JFrame("Transcormogal");
        frame.setContentPane(new Inicio().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
