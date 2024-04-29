package biz;

import java.util.Date;

public class clientes {

    private int id;
    private String nombre;
    private String fechaNac;
    private String fechaEntrada;
    private String fechaSalida;



    @Override
    public String toString() {
        return  id + " :: " + nombre + " :: " + fechaNac + " :: " + fechaEntrada + " :: " + fechaSalida ;
    }

    public clientes(int id, String nombre, String fechaNac, String fechaEntrada, String fechaSalida) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public int getId() {
        return id;
    }



    public String getNombre() {
        return nombre;
    }



    public String getFechaNac() {
        return fechaNac;
    }



    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
