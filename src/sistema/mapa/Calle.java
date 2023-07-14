package sistema.mapa;

import clases.Direccion;
import org.jgrapht.graph.DefaultWeightedEdge;

/** Extiende DefaultWeightedEdge para que los metodos de Dijkstra funcionen */
public class Calle extends DefaultWeightedEdge{
    private String nombre;
    /**
     *  Valor que va del 0 al 1, representando el porcentaje de congestión
     *  Siendo 1 100% y 0 0%.
     * Se usa para calcular la mejor ruta de la siguiente manera,
     * se calcula un nuevo peso en base a la distancia y el índice como sigue:
     * peso = distancia + distancia * índice de congestión */
    private float indiceCongestion;
    public Calle(){}
    public Calle(String nombre, float indiceCongestion) {
        this.nombre = nombre;
        this.indiceCongestion = indiceCongestion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getIndiceCongestion() {
        return indiceCongestion;
    }
    public void setIndiceCongestion(float indiceCongestion) {
        this.indiceCongestion = indiceCongestion;
    }

    /**
     * Sobreescritura de getWeight para que los métodos de las clases de graphT funcionen correctamente
     * @return valor asignado a la arista en función a la congestión y distancia
     */
    @Override
    public double getWeight(){
        double distancia = Direccion.distancia((Direccion) getSource(), (Direccion) getTarget());
        return distancia + indiceCongestion*distancia;
    }
    /**
     * Sobreescritura de equals para que los métodos de las clases de graphT funcionen correctamente
     * @return
     */
    @Override
    public boolean equals(Object o){
        return this == o;
    }
}
