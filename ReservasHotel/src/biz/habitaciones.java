package biz;

public class habitaciones {
    private int id_hab;
    private int id_cliente;
    private String num_habitacion;
    private String tipo;

    @Override
    public String toString() {
        return  id_hab + " :: " + id_cliente + " :: " + num_habitacion + " :: " + tipo;
    }

    public habitaciones(int id_hab, int id_cliente, String num_habitacion, String tipo) {
        this.id_hab = id_hab;
        this.id_cliente = id_cliente;
        this.num_habitacion = num_habitacion;
        this.tipo = tipo;
    }

    public int getId_hab() {
        return id_hab;
    }

    public void setId_hab(int id_hab) {
        this.id_hab = id_hab;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(String num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
