package clases;

import java.time.LocalDateTime;
import java.util.Date;

public class CalificacionServicio extends Mensaje{
    private int calificacion;

    public CalificacionServicio(Persona emisor, Persona receptor, String mensaje, LocalDateTime enviado, int calificacion) {
        super(emisor, receptor, mensaje, enviado);
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
