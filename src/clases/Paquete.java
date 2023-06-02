package clases;

public class Paquete {
    private Dimension dimension;
    private String detalle;

    public Paquete(Dimension dimension, String detalle) {
        this.dimension = dimension;
        this.detalle = detalle;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
