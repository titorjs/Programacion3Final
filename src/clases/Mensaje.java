package clases;

import java.util.Date;

public class Mensaje {
    private Persona emisor;
    private Persona receptor;
    private String mensaje;
    private Date enviado;

    public Mensaje(Persona emisor, Persona receptor, String mensaje, Date enviado) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.mensaje = mensaje;
        this.enviado = enviado;
    }

    public Persona getEmisor() {
        return emisor;
    }

    public void setEmisor(Persona emisor) {
        this.emisor = emisor;
    }

    public Persona getReceptor() {
        return receptor;
    }

    public void setReceptor(Persona receptor) {
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getEnviado() {
        return enviado;
    }

    public void setEnviado(Date enviado) {
        this.enviado = enviado;
    }
}
