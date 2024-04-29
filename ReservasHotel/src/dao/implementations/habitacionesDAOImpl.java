package dao.implementations;

import biz.habitaciones;
import dao.interfaces.habitacionesDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class habitacionesDAOImpl implements habitacionesDAO, AutoCloseable{

    Connection con = null;

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public habitacionesDAOImpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }


    @Override
    public ArrayList<habitaciones> getHabitacionesFromHotel(int hotel_id) throws Exception {
        return null;
    }

    @Override
    public habitaciones getHabitacionByReserva(int reserva_id) throws Exception {
        return null;
    }

    @Override
    public int updateHabitacion(habitaciones h) throws Exception {
        return 0;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
