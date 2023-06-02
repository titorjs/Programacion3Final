package interfaces;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JPanel inicio;

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
    }
}
