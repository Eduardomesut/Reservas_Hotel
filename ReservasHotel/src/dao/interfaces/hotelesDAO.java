package dao.interfaces;


import biz.hoteles;

import java.util.ArrayList;

public interface hotelesDAO {
    public ArrayList<hoteles> getHoteles() throws Exception;
    public hoteles getHotelByReserva(int reserva_id) throws Exception;
    public int updateHotel(hoteles h, String nombre, String ubicacion) throws Exception;
    public String getNombreHotel(int habitacion_id)throws Exception;
    public int registrarHotel (String nombre, String ubicacion) throws Exception;
}
