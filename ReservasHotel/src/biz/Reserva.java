package biz;

public class Reserva {
    private int reserva_id;
    private int cliente_id;
    private int habitacion_id;

    private String fecha_ingreso;
    private String fecha_salida;

    @Override
    public String toString() {
        return  reserva_id + " :: " + cliente_id + " :: " + habitacion_id+ " :: " + fecha_ingreso + " :: " + fecha_salida;
    }

    public Reserva(int reserva_id, int cliente_id, int habitacion_id, String fecha_ingreso, String fecha_salida) {
        this.reserva_id = reserva_id;
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
    }

    public Reserva(int cliente_id, int habitacion_id, String fecha_ingreso, String fecha_salida) {
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
    }

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
}
