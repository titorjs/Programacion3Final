package clases;

import java.util.Date;

public class ReporteServicios extends Mensaje{
    private Envio envio;

    public ReporteServicios(Persona emisor, Persona receptor, String mensaje, Date enviado, Envio envio) {
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
