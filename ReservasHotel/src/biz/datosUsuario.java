package biz;

public class datosUsuario {

    private int id_cliente;
    private double saldo;
    private int puntosNH;

    @Override
    public String toString() {
        return id_cliente + " :: " + saldo + " :: " + puntosNH;
    }

    public datosUsuario(int id_cliente, double saldo, int puntosNH) {
        this.id_cliente = id_cliente;
        this.saldo = saldo;
        this.puntosNH = puntosNH;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getPuntosNH() {
        return puntosNH;
    }

    public void setPuntosNH(int puntosNH) {
        this.puntosNH = puntosNH;
    }
}
