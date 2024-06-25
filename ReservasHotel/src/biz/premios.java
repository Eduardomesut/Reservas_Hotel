package biz;

public class premios {

    private int premioId;
    private String nombre;
    private String descripcion;
    private int coste;


    @Override
    public String toString() {
        return premioId + " :: " + nombre + " :: " + descripcion + " :: " + coste;
    }

    public premios(int premioId, String nombre, String descripcion, int coste) {
        this.premioId = premioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
    }

    public premios(String nombre, String descripcion, int coste) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.coste = coste;
    }

    public int getPremioId() {
        return premioId;
    }

    public void setPremioId(int premioId) {
        this.premioId = premioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int conste) {
        this.coste = conste;
    }
}
