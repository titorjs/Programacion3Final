package interfaces.admin;
import sistema.Sistema;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JPanel{
    private JPanel inicio;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JButton soloQuieresVerUnButton;
    private JButton eresEmpleadoClickAquíButton;
    public static Sistema sistema;
    private AdminManejarUsuarios adminPanel;

    public Inicio() {
        sistema = new Sistema();
        adminPanel = new AdminManejarUsuarios();

        soloQuieresVerUnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAdminPanel();
            }
        });
    }

    public void mostrarAdminPanel() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(inicio);
        frame.dispose();
        frame.setContentPane(adminPanel);
        frame.revalidate();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio");
        frame.setContentPane(new Inicio().inicio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
