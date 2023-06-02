package clases;

public class Dimension {
    /**
     * El alto, ancho y largo están en centímetros
     * El peso en kilos
     */
    private double alto;
    private double ancho;
    private double largo;
    private double peso;

    public Dimension(double alto, double ancho, double largo, double peso) {
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.peso = peso;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Calcula el volumen en cm3
     * @return regresa el volumen en cm3
     */
    public double volumen(){
        return alto*ancho*largo;
    }

    /**
     * Calcula el volumen en m3
     * @return regresa el volumen en m3
     */
    public double volumenMetros(){
        return volumen()/1000000;
    }
}
