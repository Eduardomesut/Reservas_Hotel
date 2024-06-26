package dao.interfaces;


import biz.habitaciones;

import java.util.ArrayList;

public interface habitacionesDAO {

    public ArrayList<habitaciones> getHabitacionesFromHotel(int hotel_id) throws Exception;
    public habitaciones getHabitacionByReserva(int reserva_id) throws Exception;
    public int updateHabitacion(habitaciones h,String numeroNuevo, String tipoNuevo) throws Exception;

    public boolean habitacionOcupada(int idHab, String fechaE, String fechaS) throws Exception;
    //habitacion ocupada boolean

    public int addHabitacion(int hotel_id, String numero, String tipo) throws Exception;

    public int getIdHab(int hotel_id, String numero, String tipo)throws Exception;

}
