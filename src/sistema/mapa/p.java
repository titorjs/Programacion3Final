package sistema.mapa;

import clases.Direccion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Interfaz de prueba para la clase Mapa
 */
public class p {
    private JPanel p;
    private JPanel canvasMapa;

    public p() {
        Mapa m = new Mapa();
        canvasMapa.setLayout(new BorderLayout());
        canvasMapa.add(m, BorderLayout.CENTER);

        m.dibujarMapa();

        Direccion inicio = m.buscarVertice(new Direccion(100, 100));

        ArrayList<Direccion> puntos = new ArrayList<>();
        puntos.add(new Direccion(350, 350));
        puntos.add(new Direccion(250, 350));
        puntos.add(new Direccion(50, 350));
        puntos.add(new Direccion(450, 500));

        m.imprimirRutaOptima(m.referenciaMasCercana(puntos), inicio);

        for (Direccion d: m.referenciaMasCercana(puntos)){
            m.addDotVertex(d, Color.BLACK);
        }

        for (Direccion d: puntos){
            m.addDotVertex(d, Color.GREEN);
        }

        m.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("p");
        frame.setContentPane(new p().p);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
