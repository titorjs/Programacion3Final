package clases;

public class Persona implements Comparable<Persona>{
    private String nombre;
    /** Para los admins, se usará un valor de cédula empezado en 50, ya que no están en el rango
     * normal de cédulas */
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

    /**
     * Método para validar la contraseña, así no se devuelve como tal la contraseña
     * @param contrasenia valor para validar contraseña
     * @return true: si la contraseña ingresada es correcta,
     *         false: si la contraseña ingresada es incorrecta
     */
    public boolean validarContrasenia(String contrasenia){
        return this.contrasenia.equals(contrasenia);
    }

    /**
     * !!! Modificar método To string
     * @return
     */
    @Override
    public String toString() {
        return nombre + " " + cedula + " " + tipo.name();
    }

    /**
     * Método que se debe implementar para que el set funcione correctamente
     * @param o objeto a comparar
     * @return
     */
    @Override
    public int compareTo(Persona o) {
        return compararCedula(this.cedula, o.cedula);
    }

    /**
     * Método para comparar que cédula es mayor numéricamente, ya que se maneja como string
     * @param cedula1 primera cédula a comparar
     * @param cedula2 segunda cédula a comparar
     * @return 0 si son iguales, un entero positivo si cedula 1 es mayor a cedula 2
     *         un entero negativo si la cedula 2 es mayor a la cedula 1
     */
    public static int compararCedula(String cedula1, String cedula2){
        if(cedula1.equals(cedula2)){
            return 0;
        }

        int comparacion = 0;
        for(int i = 0; i < 10; i++){
            comparacion = cedula1.charAt(i) - cedula2.charAt(i);
            if(comparacion != 0){
                break;
            }
        }
        return comparacion;
    }
}
