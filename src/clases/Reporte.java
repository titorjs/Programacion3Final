package clases;

import java.time.LocalDateTime;

public class Reporte extends Mensaje{
    private boolean panico;
    public Reporte(Persona emisor, LocalDateTime enviado, String mensaje,boolean panico) {
        super(emisor, null, mensaje, enviado);
        this.panico = panico;
    }

    public boolean isPanico() {
        return panico;
    }

    @Override
    public String toString() {
        return "ReportePanico: " + getEmisor() + " " + getEnviado().toString();
    }
}