package dao.implementations;

import biz.hoteles;
import dao.interfaces.hotelesDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class hotelesDAOImpl implements hotelesDAO, AutoCloseable {

    Connection con = null;
    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }
    public hotelesDAOImpl() throws Exception{

        con = DriverManager.getConnection(Configuration.URL);
    }
    @Override
    public ArrayList<hoteles> getHoteles() throws Exception {
        ArrayList<hoteles> al = new ArrayList<>();
        hoteles add = null;
        ResultSet rs = null;
        String sql = "SELECT hotel_id, nombre, ubicacion FROM hoteles;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            rs = pst.executeQuery();
            while (rs.next()){
                add = new hoteles(rs.getInt("hotel_id"), rs.getString("nombre"), rs.getString("ubicacion"));
                al.add(add);
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
    public hoteles getHotelByReserva(int reserva_id) throws Exception {
        hoteles nuevo = null;
        ResultSet rs = null;
        String sql = "SELECT h.hotel_id, h.nombre, h.ubicacion FROM hoteles as h JOIN habitaciones AS ha ON h.hotel_id = ha.hotel_id JOIN reserva AS r ON ha.habitacion_id = r.habitacion_id WHERE r.reserva_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1,reserva_id);
            rs = pst.executeQuery();
            while (rs.next()){
             nuevo = new hoteles(rs.getInt("h.hotel_id"), rs.getString("h.nombre"), rs.getString("h.ubicacion"));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return nuevo;
    }

    @Override
    public int updateHotel(hoteles h, String nombre, String ubicacion) throws Exception {
        int r = 0;
        String sql = "UPDATE hoteles SET nombre = ?, ubicacion = ? WHERE hotel_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setString(1,nombre);
            pst.setString(2,ubicacion);
            pst.setInt(3,h.getHotel_id());
            r = pst.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
