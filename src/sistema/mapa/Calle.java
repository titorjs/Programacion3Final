package sistema.mapa;

import clases.Direccion;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Calle extends DefaultWeightedEdge{
    private String nombre;
    /** !!! A lo mejor no del 0 al 1, sino solo que sea positivo
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

    @Override
    public double getWeight(){
        double distancia = Direccion.distancia((Direccion) getSource(), (Direccion) getTarget());
        return distancia + indiceCongestion*distancia;
    }

    @Override
    public boolean equals(Object o){
        return this == o;
    }
}
