package sistema.mapa;
import clases.Direccion;

import clases.Sucursales;
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
    private Graph<Direccion, Calle> graph;
    private Sucursales sucursal;

    /**
     * Constructor por defecto, con aristas con pesos al azar
     */
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
    public Graph<Direccion, Calle> getGraph() {
        return graph;
    }
    public Sucursales getSucursal() {
        return sucursal;
    }
    public void setSucursal(Sucursales sucursal){
        this.sucursal = sucursal;
    }

    /**
     * Añadir un linea de cierto color al Canvas
     * @param x1 punto x1 para la recta
     * @param y1 punto y1 para la recta
     * @param x2 punto x2 para la recta
     * @param y2 punto y2 para la recta
     * @param color color para la recta
     */
    public void addLine(int x1, int y1, int x2, int y2, Color color) {
        /** Crear un objeto de tipo linea */
        PPath line = PPath.createLine(x1, y1, x2, y2);
        /** darle color */
        line.setStrokePaint(color);
        /** añadirlo al canvas */
        getLayer().addChild(line);
    }

    /**
     * Añadir un círculo de cierto diametro al canvas
     * @param x posición x para el círculo
     * @param y posición y para el círculo
     * @param diametro diametro del círculo
     * @param relleno color para el relleno
     * @param borde color para el borde
     */
    public void addDot(int x, int y, int diametro, Color relleno, Color borde){
        /** Creación objeto tipo círculo, el centro se ajusta en base al diametro */
        PPath circle = PPath.createEllipse(x - diametro/2, y - diametro/2, diametro, diametro);
        /** Rellenar el círculo */
        circle.setPaint(relleno);
        /** Se agrega o no al borde */
        if (borde == null)
            circle.setStroke(null);
        else
            circle.setStrokePaint(borde);
        /** añadirlo al canvas */
        getLayer().addChild(circle);
    }

    /**
     * Borra todos los elementos del canvas
     */
    public void limpiarCanvas(){
        this.getLayer().removeAllChildren();
        this.repaint();
    }

    /**
     * Dibuja el mapa con los pesos de las calles
     */
    public void dibujarMapa(){
        /** Elimina todo lo del Canvas */
        this.getLayer().removeAllChildren();

        /** Obtener todas las aristas */
        Set<Calle> edges = graph.edgeSet();

        /** Dibujar todas las calles */
        for(Calle c: edges){
            addLineEdge(c, Color.GRAY);
        }

        /** Lista de vértices */
        Set<Direccion> vertexs = graph.vertexSet();

        for (Direccion d: vertexs){
            /** Añadimos un punto por cada vértice */
            addDot((int) d.getLongitud(), (int) d.getLatitud(), 5,Color.GRAY, null);
        }

        /** Repintamos para que hagan efecto los cambios */
        this.repaint();
    }
    /** Añade una línea en base a la arista */
    public void addLineEdge(Calle c, Color color){
        /** Obtnemos el vértice de salida y llegada */
        Direccion target = graph.getEdgeTarget(c);
        Direccion source = graph.getEdgeSource(c);

        /** Añadimos la linea y el texto que es el peros */
        addLine((int) target.getLongitud(), (int)target.getLatitud(), (int)source.getLongitud(),(int)source.getLatitud(), color);
        /** Texto para la calle que se pone en el centro de la linea */
        PText nombre = new PText(c.getNombre() + "(" + (new DecimalFormat("#.00")).format(c.getWeight()) + ")");//!!! Está puesto el peso solo para visualizar mejor
        Direccion medio = Direccion.intermedio(target, source);
        nombre.setBounds(medio.getLongitud(), medio.getLatitud(), 1, 1);
        getLayer().addChild(nombre);
    }
    /** Añade una línea en base al vértice */
    public void addDotVertex(Direccion d, Color color){
        addDot((int) d.getLongitud(), (int) d.getLatitud(), 5, color, null);
    }
    /** Buscar vértice que coincida con las coordenadas */
    public Direccion buscarVertice(Direccion buscado){
        for (Direccion d: graph.vertexSet()){
            if (d.getLatitud() == buscado.getLatitud() && d.getLongitud() == buscado.getLongitud())
                return d;
        }
        return null;
    }
    /** Buscar el vértice que esté más cerca de la dirección asignada */
    public Direccion referenciaMasCercana(Direccion objetivo){
        // Valores iniciales
        Direccion aux = null;
        double minimo = 100000;

        /** Recorremos los vertices en busca del que tenga la menor distancia */
        for(Direccion d: graph.vertexSet()) {
            if (Direccion.distancia(d, objetivo) < minimo) {
                minimo = Direccion.distancia(d, objetivo);
                aux = d;
            }
        }
        return aux;
    }
    /** Centrar la imagen del mapa en base a lo preestablecido */
    public void centrar(){
        PAffineTransform zoom = new PAffineTransform();
        zoom.setTransform(0.23, 0, 0, 0.23, 13, 13);
        this.getCamera().setViewTransform(zoom);
    }
    /** Buscar el vértice que esté más cerca de la dirección asignada, pero para una lista de direcciones */
    public ArrayList<Direccion> referenciaMasCercana(ArrayList<Direccion> paquetes){
        ArrayList<Direccion> puntos = new ArrayList<>();
        /** Aplica el método por cada elemento de la lista */
        for (Direccion d: paquetes){
            puntos.add(referenciaMasCercana(d));
        }
        return puntos;
    }
    /** Se imprime la ruta óptima en el canvas */
    public void imprimirRutaOptima(ArrayList<Direccion> puntos, Direccion inicio){
        /** Aplica el método para obtener la lista de aristas a seguir y luego las grafica */
        ArrayList<Calle> ruta = buscarRutaOptima(puntos, inicio);

        /** Graficación de las lineas */
        for(Calle c: ruta){
            addLineEdge(c, Color.red);
        }

        /** Se denota el inicio */
        addDotVertex(inicio, Color.BLUE);

        /** Se grafican los puntos */
        for (Direccion d: puntos){
            this.addDotVertex(d, Color.GREEN);
        }
    }
    /** Obtiene el peso total que cuesta seguir una ruta */
    public double pesoRuta(GraphPath<Direccion, Calle> ruta){
        double peso = 0;
        for(Calle c: ruta.getEdgeList()){
            peso += c.getWeight();
        }
        return peso;
    }
    /** Busca la rúta óptima para seguir recorriendo una lista de puntos desde un inicio */
    public ArrayList<Calle> buscarRutaOptima(ArrayList<Direccion> puntos, Direccion inicio){
        /** Datos iniciales */
        ArrayList<Calle> ruta = new ArrayList<>();
        inicio = buscarVertice(inicio);

        /** Instancia de Dijkstra para trabajar */
        DijkstraShortestPath<Direccion, Calle> shortestPath = new DijkstraShortestPath<>(graph);
        double corta = Float.MAX_VALUE;
        double auxCorta = 0;
        GraphPath<Direccion, Calle> res = null, aux;
        Direccion auxD = null;

        /** Se itera y eliminar sobre los puntos dados */
        while(!puntos.isEmpty()){
            /** Se recorre desde el inicio hasta todos los demás y se ve cuál está más cerca */
            for (Direccion d: puntos){
                aux = shortestPath.getPath(inicio, d);
                auxCorta = pesoRuta(aux);
                if( auxCorta < corta){
                    res = aux;
                    corta = auxCorta;
                    auxD = d;
                }
            }
            /** El más corto se asigna como nuevo inicio y se elimina de la lista de puntos a recorrer */
            inicio = auxD;
            puntos.remove(auxD);
            /** Se añade la ruta seguida a la ruta total */
            ruta.addAll(res.getEdgeList());
            corta = Float.MAX_VALUE;
        }
        return ruta;
    }
}
