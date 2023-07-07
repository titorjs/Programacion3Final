package clases;

public class Direccion {
    private double latitud;
    private double longitud;

    public Direccion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public static double distancia(Direccion d1, Direccion d2){
        double x = Math.pow(d1.longitud - d1.longitud,2);
        double y = Math.pow(d1.latitud - d2.latitud,2);
        return Math.sqrt(x + y);
    }

    @Override
    public String toString() {
        return "Direccion:"+latitud+","+longitud;
    }
}
