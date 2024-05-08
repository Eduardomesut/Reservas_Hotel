package dao.interfaces;


import biz.habitaciones;

import java.util.ArrayList;

public interface habitacionesDAO {

    public ArrayList<habitaciones> getHabitacionesFromHotel(int hotel_id) throws Exception;
    public habitaciones getHabitacionByReserva(int reserva_id) throws Exception;
    public int updateHabitacion(habitaciones h,String numeroNuevo, String tipoNuevo) throws Exception;
}
