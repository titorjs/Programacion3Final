package interfaces;


import javax.swing.*;

public class Inicio {
    private JPanel inicio;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;

    public Inicio() {

}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio");
        frame.setContentPane(new Inicio().inicio);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
