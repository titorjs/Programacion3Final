package clases;

import java.time.*;

public class ReporteServicios extends Mensaje{
    private Envio envio;

    public ReporteServicios(Persona emisor, Persona receptor, String mensaje, LocalDateTime enviado, Envio envio) {
        super(emisor, receptor, mensaje, enviado);
        this.envio = envio;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }
}
