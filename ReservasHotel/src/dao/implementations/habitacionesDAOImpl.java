package dao.implementations;

import biz.Reserva;
import biz.habitaciones;
import dao.interfaces.habitacionesDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        ArrayList<habitaciones>al = new ArrayList<>();
        habitaciones hab = null;
        ResultSet rs = null;
        String sql = "SELECT habitacion_id, hotel_id, numero, tipo FROM habitaciones WHERE hotel_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, hotel_id);
            rs = pst.executeQuery();
            while (rs.next()){
                hab = new habitaciones(rs.getInt("habitacion_id"), rs.getInt("hotel_id"), rs.getString("numero"), rs.getString("tipo"));
                al.add(hab);
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return al;
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
