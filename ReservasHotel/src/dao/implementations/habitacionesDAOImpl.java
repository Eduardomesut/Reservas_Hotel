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
        habitaciones nueva = null;
        ResultSet rs = null;
        String sql = "SELECT SELECT h.habitacion_id, h.hotel_id, h.numero, h.tipo FROM habitaciones AS h JOIN reservas AS r ON h.habitacion_id = r.habitacion_id WHERE r.reserva_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,reserva_id);
            rs = pst.executeQuery();
            while (rs.next()){
                nueva = new habitaciones(rs.getInt("h.habitacion_id"), rs.getInt("h.hotel_id"), rs.getString("h.numero"), rs.getString("h.tipo"));
            }

        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return nueva;
    }

    @Override
    public int updateHabitacion(habitaciones h, String numeroNuevo, String tipoNuevo) throws Exception {
        int r = 0;
        int id = h.getId_hab();
        String sql = "UPDATE habitaciones SET numero = ? ,tipo = ? WHERE habitacion_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setString(1,numeroNuevo);
            pst.setString(2,tipoNuevo);
            pst.setInt(3,h.getId_hab());
            r = pst.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public boolean habitacionOcupada(int idHab, String fechaE, String fechaS) throws Exception {
        int reservada = 0;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS numero FROM reservas WHERE habitacion_id = ? AND (fecha_ingreso <= ? AND fecha_salida >= ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,idHab);
            pst.setString(2, fechaS);
            pst.setString(3, fechaE);
            rs = pst.executeQuery();
            while (rs.next()){
                reservada += rs.getInt("numero");
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        if (reservada > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int addHabitacion(int hotel_id, String numero, String tipo) throws Exception {
        int r = 0;
        String sql = "INSERT INTO habitaciones (hotel_id, numero, tipo) VALUES (?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, hotel_id);
            pst.setString(2,numero);
            pst.setString(3,tipo);
            r = pst.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public int getIdHab(int hotel_id, String numero, String tipo) throws Exception {
        int id = 0;
        ResultSet rs = null;
        String sql = "SELECT habitacion_id FROM habitaciones WHERE hotel_id = ? AND numero = ? AND tipo = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,hotel_id);
            pst.setString(2,numero);
            pst.setString(3,tipo);
            rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getInt("habitacion_id");
            }
        }catch (Exception e){
            throw  e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return id;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
