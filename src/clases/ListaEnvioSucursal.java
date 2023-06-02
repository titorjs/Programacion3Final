package clases;

import java.util.ArrayList;

public class ListaEnvioSucursal extends ListaEnvio{
    private Sucursal sucursalDestion;

    public ListaEnvioSucursal(ArrayList<Envio> envios, Sucursal sucursalOrigen, Sucursal sucursalDestion) {
        super(envios, sucursalOrigen);
        this.sucursalDestion = sucursalDestion;
    }

    public Sucursal getSucursalDestion() {
        return sucursalDestion;
    }

    public void setSucursalDestion(Sucursal sucursalDestion) {
        this.sucursalDestion = sucursalDestion;
    }
}
