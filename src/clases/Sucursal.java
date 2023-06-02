package clases;

import java.util.ArrayList;
import java.util.SortedSet;

public class Sucursal {
    private Direccion direccion;
    private SortedSet<Envio> inventario;
    private Sucursales zona;
    private ArrayList<Persona> equipo;

    public Sucursal(Direccion direccion, SortedSet<Envio> inventario, Sucursales zona, ArrayList<Persona> equipo) {
        this.direccion = direccion;
        this.inventario = inventario;
        this.zona = zona;
        this.equipo = equipo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public SortedSet<Envio> getInventario() {
        return inventario;
    }

    public void setInventario(SortedSet<Envio> inventario) {
        this.inventario = inventario;
    }

    public Sucursales getZona() {
        return zona;
    }

    public void setZona(Sucursales zona) {
        this.zona = zona;
    }

    public ArrayList<Persona> getEquipo() {
        return equipo;
    }

    public void setEquipo(ArrayList<Persona> equipo) {
        this.equipo = equipo;
    }
}
