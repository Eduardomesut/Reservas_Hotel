package dao.interfaces;

import biz.Reserva;

import java.util.ArrayList;

public interface ReservaDAO {

    public ArrayList<Reserva> getReservas() throws Exception;
    public Reserva getReservabyCliente(int cliente_id) throws Exception;
    public int updateReserva(Reserva h,int nuevaHab, String nuevaFentrada, String nuevaFsalida) throws Exception;
    public int addReserva(Reserva h)throws Exception;



}
