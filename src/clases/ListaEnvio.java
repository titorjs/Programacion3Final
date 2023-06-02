package clases;

import java.util.ArrayList;

public class ListaEnvio {
    private ArrayList<Envio> envios;
    private Sucursal sucursalOrigen;

    public ListaEnvio(ArrayList<Envio> envios, Sucursal sucursalOrigen) {
        this.envios = envios;
        this.sucursalOrigen = sucursalOrigen;
    }

    public ArrayList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(ArrayList<Envio> envios) {
        this.envios = envios;
    }

    public Sucursal getSucursalOrigen() {
        return sucursalOrigen;
    }

    public void setSucursalOrigen(Sucursal sucursalOrigen) {
        this.sucursalOrigen = sucursalOrigen;
    }
}
