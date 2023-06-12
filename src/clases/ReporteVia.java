package clases;

import java.time.LocalDateTime;

public class ReporteVia extends Mensaje {
    private Direccion direccion;

    public ReporteVia(Persona emisor, String mensaje, LocalDateTime enviado, Direccion direccion) {
        super(emisor, null, mensaje, enviado);
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "De: " + getEmisor().getNombre() + "(" + getEmisor().getCedula() + ")\n" +
                "Mensaje: " + getMensaje() + "\n" +
                "Fecha: " + getEnviado().toString() + "\n";
    }
}
