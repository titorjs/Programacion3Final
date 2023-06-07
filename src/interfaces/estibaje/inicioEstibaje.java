package interfaces.estibaje;

import javax.swing.*;

public class inicioEstibaje {

    private JPanel principal;

    public static void main(String[] args) {
        JFrame frame = new JFrame("inicioEstibaje");
        frame.setContentPane(new inicioEstibaje().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
