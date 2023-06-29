package clases;

import java.util.SortedSet;

public class Camion implements Comparable<Camion>{
    private int id;
    private SortedSet carga;
    private Persona conductor;

    public Camion(int id, SortedSet carga, Persona conductor) {
        this.id = id;
        this.carga = carga;
        this.conductor = conductor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SortedSet getCarga() {
        return carga;
    }

    public void setCarga(SortedSet carga) {
        this.carga = carga;
    }

    public Persona getConductor() {
        return conductor;
    }

    public void setConductor(Persona conductor) {
        this.conductor = conductor;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", carga=" + carga +
                ", conductor=" + conductor +
                '}';
    }

    @Override
    public int compareTo(Camion o) {

        return id-o.id;
    }
}
