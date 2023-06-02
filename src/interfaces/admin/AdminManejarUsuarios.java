package interfaces.admin;

import javax.swing.*;

public class AdminManejarUsuarios extends JPanel{
    private JPanel ManejarUsuarios;
    private JButton button1;
    private JButton button2;

    public JPanel getManejarUsuarios() {
        return ManejarUsuarios;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AdminManejarUsuarios");
        frame.setContentPane(new AdminManejarUsuarios().ManejarUsuarios);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
