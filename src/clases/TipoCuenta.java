package clases;

public enum TipoCuenta {
    ADMINISTRADOR("Cuenta administrador, maneja usuarios"),
    USUARIO("Cuenta normal de usuario"),
    ESTIBAJE("Cuenta relacionada con bodega"),
    CONDUCTOR("Cuenta relacionada con el conductor entre sucursales"),
    REPARTIDOR("Cuenta para quien entrega dentro de la ciudad"),
    EJECUTIVO("Cuenta para personas del área administrativa de la empresa");

    private final String descripcion;

    TipoCuenta(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
