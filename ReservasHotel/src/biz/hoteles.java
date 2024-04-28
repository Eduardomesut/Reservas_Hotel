package biz;

public class hoteles {

    private int hotel_id;
    private String nombre;
    private String ubicacion;

    @Override
    public String toString() {
        return  hotel_id + " :: " + nombre + " :: " + ubicacion;
    }

    public hoteles(int hotel_id, String nombre, String ubicacion) {
        this.hotel_id = hotel_id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
