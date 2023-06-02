package clases;

public enum Sucursales {
    GYE("Guayaquil"),
    UIO_S("Quito sur"),
    UIO_N("Quito norte"),
    STO_DGO("Santo Domingo");

    private final String descripcion;

    Sucursales(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}