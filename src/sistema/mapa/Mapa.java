package sistema.mapa;
import clases.Direccion;

import edu.umd.cs.piccolo.PCanvas;
import edu.umd.cs.piccolo.nodes.PPath;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PAffineTransform;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.*;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Set;

public class Mapa extends PCanvas{
    private Graph<Direccion, Calle> graph ;
    public Mapa() {
        this.graph = new SimpleWeightedGraph<>(Calle.class);

        setBounds(0, 0, 1500, 1500);
        centrar();

        /**
         * Inicializar con datos aleatorios una ciudad rectangular
         */

        for (int y = 0; y < 15; y++){
            for (int x = 0; x < 15; x++){
                Direccion aux = new Direccion(y*100, x*100);
                graph.addVertex(aux);
                Calle calle;
                if (y > 0){
                    calle = new Calle("", (float) Math.random());
                    graph.addEdge(aux, buscarVertice(new Direccion((y - 1)*100, x*100)), calle);
                }
                if (x > 0){
                    calle = new Calle("", (float) Math.random());
                    graph.addEdge(aux, buscarVertice(new Direccion(y*100, (x - 1)*100)), calle);
                }
            }
        }
    }
    public Mapa(Graph<Direccion, Calle> graph){
        this.graph = graph;
    }
    public Graph<Direccion, Calle> getGraph() {
        return graph;
    }
    public void addLine(int x1, int y1, int x2, int y2, Color color) {
        PPath line = PPath.createLine(x1, y1, x2, y2);
        line.setStrokePaint(color);
        getLayer().addChild(line);
    }
    public void addDot(int x, int y, int diametro, Color relleno, Color borde){
        PPath circle = PPath.createEllipse(x - diametro/2, y - diametro/2, diametro, diametro);
        circle.setPaint(relleno);
        if (borde == null)
            circle.setStroke(null);
        else
            circle.setStrokePaint(borde);
        getLayer().addChild(circle);

    }
    public void limpiarCanvas(){
        this.getLayer().removeAllChildren();
        this.repaint();
    }
    public void dibujarMapa(){
        this.getLayer().removeAllChildren();

        Set<Calle> edges = graph.edgeSet();

        for(Calle c: edges){
            addLineEdge(c, Color.GRAY);
        }

        Set<Direccion> vertexs = graph.vertexSet();

        for (Direccion d: vertexs){
            addDot((int) d.getLongitud(), (int) d.getLatitud(), 5,Color.GRAY, null);
        }

        this.repaint();
    }
    public void addLineEdge(Calle c, Color color){
        Direccion target = graph.getEdgeTarget(c);
        Direccion source = graph.getEdgeSource(c);

        addLine((int) target.getLongitud(), (int)target.getLatitud(), (int)source.getLongitud(),(int)source.getLatitud(), color);
        PText nombre = new PText(c.getNombre() + "(" + (new DecimalFormat("#.00")).format(c.getWeight()) + ")");//!!! Está puesto el peso solo para visualizar mejor
        Direccion medio = Direccion.intermedio(target, source);
        nombre.setBounds(medio.getLongitud(), medio.getLatitud(), 1, 1);
        getLayer().addChild(nombre);
    }
    public void addDotVertex(Direccion d, Color color){
        addDot((int) d.getLongitud(), (int) d.getLatitud(), 5, color, null);
    }
    public void dibujarRuta(ArrayList<Calle> ruta){
        for (Calle c: ruta){
            addLineEdge(c, Color.RED);
        }
        this.repaint();
    }
    public GraphPath<Direccion, Calle> rutaEntre(Direccion inicio, Direccion fin){
        DijkstraShortestPath<Direccion, Calle> shortestPath = new DijkstraShortestPath<>(graph);
        return shortestPath.getPath(inicio, fin);
    }
    public Direccion buscarVertice(Direccion buscado){
        for (Direccion d: graph.vertexSet()){
            if (d.getLatitud() == buscado.getLatitud() && d.getLongitud() == buscado.getLongitud())
                return d;
        }
        return null;
    }
    public Direccion referenciaMasCercana(Direccion objetivo){
        Direccion aux = null;
        double minimo = 100000;

        for(Direccion d: graph.vertexSet()) {
            if (Direccion.distancia(d, objetivo) < minimo) {
                minimo = Direccion.distancia(d, objetivo);
                aux = d;
            }
        }
        return aux;
    }
    public void centrar(){
        PAffineTransform zoom = new PAffineTransform();
        zoom.setTransform(0.23, 0, 0, 0.23, 13, 13);
        this.getCamera().setViewTransform(zoom);
    }
    public ArrayList<Direccion> referenciaMasCercana(ArrayList<Direccion> paquetes){
        ArrayList<Direccion> puntos = new ArrayList<>();
        for (Direccion d: paquetes){
            puntos.add(referenciaMasCercana(d));
        }
        return puntos;
    }
    public void imprimirRutaOptima(ArrayList<Direccion> puntos, Direccion inicio){
        ArrayList<Calle> ruta = buscarRutaOptima(puntos, inicio);

        for(Calle c: ruta){
            addLineEdge(c, Color.red);
        }

        addDotVertex(inicio, Color.BLUE);

        for (Direccion d: puntos){
            this.addDotVertex(d, Color.GREEN);
        }
    }
    public double pesoRuta(GraphPath<Direccion, Calle> ruta){
        double peso = 0;
        for(Calle c: ruta.getEdgeList()){
            peso += c.getWeight();
        }
        return peso;
    }
    public ArrayList<Calle> buscarRutaOptima(ArrayList<Direccion> puntos, Direccion inicio){
        ArrayList<Calle> ruta = new ArrayList<>();

        DijkstraShortestPath<Direccion, Calle> shortestPath = new DijkstraShortestPath<>(graph);
        double corta = Float.MAX_VALUE;
        double auxCorta = 0;
        GraphPath<Direccion, Calle> res = null, aux;
        Direccion auxD = null;

        while(!puntos.isEmpty()){
            for (Direccion d: puntos){
                aux = shortestPath.getPath(inicio, d);
                auxCorta = pesoRuta(aux);
                if( auxCorta < corta){
                    res = aux;
                    corta = auxCorta;
                    auxD = d;
                }
            }
            inicio = auxD;
            puntos.remove(auxD);
            ruta.addAll(res.getEdgeList());
            corta = Float.MAX_VALUE;
        }
        return ruta;
    }
}
