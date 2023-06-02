package clases;

public class Camion {
    private String id;
    private ListaEnvio carga;
    private Persona conductor;

    public Camion(String id, ListaEnvio carga, Persona conductor) {
        this.id = id;
        this.carga = carga;
        this.conductor = conductor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListaEnvio getCarga() {
        return carga;
    }

    public void setCarga(ListaEnvio carga) {
        this.carga = carga;
    }

    public Persona getConductor() {
        return conductor;
    }

    public void setConductor(Persona conductor) {
        this.conductor = conductor;
    }
}
