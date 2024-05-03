package dao.interfaces;

import biz.Reserva;
import biz.precios;

import java.util.ArrayList;

public interface preciosDAO {

    public precios getPrecio(int id_habitacion, String fecha_entrada) throws Exception;
    public precios getPrecioByReserva(int id_reserva) throws Exception;
    public int updatePrecio(int id_cambio, double cambio_alto, double cambio_medio, double cambio_bajo) throws Exception;
    public int addprecios(double precio_alto, double precio_medio, double precio_bajo, int habitacion_id)throws Exception;

}
