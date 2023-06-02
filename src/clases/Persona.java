package clases;

public class Persona {
    private String nombre;
    private String cedula;
    private String telefono;
    private String contrasenia;
    private TipoCuenta tipo;

    public Persona(String nombre, String cedula, String telefono, String contrasenia, TipoCuenta tipo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public boolean validarContrasenia(String contrasenia){
        return this.contrasenia.equals(contrasenia);
    }
}
