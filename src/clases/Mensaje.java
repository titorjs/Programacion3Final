package clases;

import java.time.*;

public class Mensaje implements Comparable<Mensaje>{
    private Persona emisor;
    private Persona receptor;
    private String mensaje;
    private LocalDateTime enviado;

    public Mensaje(Persona emisor, Persona receptor, String mensaje, LocalDateTime enviado) {
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

    public LocalDateTime getEnviado() {
        return enviado;
    }

    public void setEnviado(LocalDateTime enviado) {
        this.enviado = enviado;
    }

    @Override
    public String toString() {
        return "De: " + emisor.getNombre() + "(" + emisor.getCedula() + ")\n" +
               "Para: " + receptor.getNombre() + "(" + receptor.getCedula() + ")\n" +
               "Mensaje: " + mensaje + "\n" +
               "Fecha: " + enviado.toString() + "\n";

    }

    @Override
    public int compareTo(Mensaje o) {

        return enviado.compareTo(o.enviado);
    }
}
