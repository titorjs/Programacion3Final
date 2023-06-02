package clases;

import java.util.Date;

public class ReporteVia extends Mensaje {
    private Direccion direccion;

    public ReporteVia(Persona emisor, Persona receptor, String mensaje, Date enviado, Direccion direccion) {
        super(emisor, receptor, mensaje, enviado);
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
