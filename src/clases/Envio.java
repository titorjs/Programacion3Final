package clases;

import java.util.Date;

public class Envio {
    /**
     * Este id debe de ser único por envio y permitirá a las personas
     * hacer seguimiento de su paquete y comunicarse con el repartidor o la oficina
     */
    private String id;
    /**
     * El estado refleja en que estado del proceso de entrega se encuentra el paquete.
     * Los valores van como sigue:
     *  0. Se debe retirar en el domicilio del cliente
     *  1. Se recibió en oficina
     *  2. Enviandose a la sucursal correcta
     *  3. En sucursal correcta
     *  4. En curso a domicilio
     *  5. Entregado
     */
    private int estado;
    private Date fechaRecibido;
    /**
     * Tanto el solicitante como el receptor pueden ser personas no registradas en la base
     * en cuyo caso solo se mostrará la información y no habrá chat
     */
    private Persona solicitante;
    private Persona receptor;
    private Paquete paquete;
    private Sucursal sucursalRecibida;
    private Sucursal sucursalEntrega;
    private Direccion direccionEntrega;

    public Envio(String id, int estado, Date fechaRecibido, Persona solicitante, Persona receptor, Paquete paquete, Sucursal sucursalRecibida, Sucursal sucursalEntrega, Direccion direccionEntrega) {
        this.id = id;
        this.estado = estado;
        this.fechaRecibido = fechaRecibido;
        this.solicitante = solicitante;
        this.receptor = receptor;
        this.paquete = paquete;
        this.sucursalRecibida = sucursalRecibida;
        this.sucursalEntrega = sucursalEntrega;
        this.direccionEntrega = direccionEntrega;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Persona solicitante) {
        this.solicitante = solicitante;
    }

    public Persona getReceptor() {
        return receptor;
    }

    public void setReceptor(Persona receptor) {
        this.receptor = receptor;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Sucursal getSucursalRecibida() {
        return sucursalRecibida;
    }

    public void setSucursalRecibida(Sucursal sucursalRecibida) {
        this.sucursalRecibida = sucursalRecibida;
    }

    public Sucursal getSucursalEntrega() {
        return sucursalEntrega;
    }

    public void setSucursalEntrega(Sucursal sucursalEntrega) {
        this.sucursalEntrega = sucursalEntrega;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
}
