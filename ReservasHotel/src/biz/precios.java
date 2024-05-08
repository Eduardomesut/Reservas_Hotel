package biz;

public class precios {
    private int id_precio;
    private double temporada_alta;
    private double temporada_media;
    private double temporada_baja;
    private int id_habitacion;

    public precios(int id_precio, double temporada_alta, double temporada_media, double temporada_baja, int id_habitacion) {
        this.id_precio = id_precio;
        this.temporada_alta = temporada_alta;
        this.temporada_media = temporada_media;
        this.temporada_baja = temporada_baja;
        this.id_habitacion = id_habitacion;
    }

    @Override
    public String toString() {
        return  id_precio + " :: " + temporada_alta + " :: " + temporada_media + " :: " + temporada_baja + " :: " + id_habitacion;
    }

    public int getId_precio() {
        return id_precio;
    }

    public void setId_precio(int id_precio) {
        this.id_precio = id_precio;
    }

    public double getTemporada_alta() {
        return temporada_alta;
    }

    public void setTemporada_alta(double temporada_alta) {
        this.temporada_alta = temporada_alta;
    }

    public double getTemporada_media() {
        return temporada_media;
    }

    public void setTemporada_media(double temporada_media) {
        this.temporada_media = temporada_media;
    }

    public double getTemporada_baja() {
        return temporada_baja;
    }

    public void setTemporada_baja(double temporada_baja) {
        this.temporada_baja = temporada_baja;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }
}