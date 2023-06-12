package clases;

import java.time.LocalDateTime;

public class ReporteVia extends Mensaje {
    private Direccion direccion;

    public ReporteVia(Persona emisor, Persona receptor, String mensaje, LocalDateTime enviado, Direccion direccion) {
        super(emisor, null, mensaje, enviado);
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
