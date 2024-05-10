package dao.interfaces;

import biz.Reserva;

import java.util.ArrayList;

public interface ReservaDAO {

    public ArrayList<Reserva> getReservas(int cliente_id) throws Exception;
    public Reserva getReservabyCliente(int cliente_id) throws Exception;
    public int updateReserva(Reserva h,int nuevaHab, String nuevaFentrada, String nuevaFsalida) throws Exception;
    public int addReserva(int cliente_id, int habitacion_id, String fecha_ingreso, String fecha_salida)throws Exception;
    public int getReservaId(int cliente_id, String fecha_ingreso) throws Exception;
    public ArrayList<Reserva> getReservas(String nombre, String fechaNac) throws Exception;

}
