package clases;

import java.util.Date;

public class EnvioRecibidoDomicilio extends Envio{
    private Direccion direccionRecibida;

    public EnvioRecibidoDomicilio(String id, int estado, Date fechaRecibido, Persona solicitante, Persona receptor, Paquete paquete, Sucursal sucursalRecibida, Sucursal sucursalEntrega, Direccion direccionEntrega, Direccion direccionRecibida) {
        super(id, estado, fechaRecibido, solicitante, receptor, paquete, sucursalRecibida, sucursalEntrega, direccionEntrega);
        this.direccionRecibida = direccionRecibida;
    }

    public Direccion getDireccionRecibida() {
        return direccionRecibida;
    }

    public void setDireccionRecibida(Direccion direccionRecibida) {
        this.direccionRecibida = direccionRecibida;
    }
}
