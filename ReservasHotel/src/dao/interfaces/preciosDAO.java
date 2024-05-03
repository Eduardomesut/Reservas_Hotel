package dao.interfaces;

import biz.Reserva;
import biz.precios;

import java.util.ArrayList;

public interface preciosDAO {

    public precios getPrecio(int id_habitacion, String fecha_entrada) throws Exception;
    public precios getPrecioByReserva(int id_reserva) throws Exception;
    public int updatePrecio(precios cambio) throws Exception;
    public int addprecios(precios add)throws Exception;

}
