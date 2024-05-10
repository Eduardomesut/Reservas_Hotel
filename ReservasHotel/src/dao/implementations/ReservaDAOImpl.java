package dao.implementations;

import biz.Reserva;
import dao.interfaces.ReservaDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservaDAOImpl implements ReservaDAO, AutoCloseable {

    Connection con = null;

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public ReservaDAOImpl() throws Exception{

        con = DriverManager.getConnection(Configuration.URL);
    }
    @Override
    public ArrayList<Reserva> getReservas(int cliente_id) throws Exception {
        ArrayList<Reserva> al = new ArrayList<>();
        Reserva r = null;
        ResultSet rs = null;
        String sql = "SELECT reserva_id, cliente_id, habitacion_id, fecha_ingreso, fecha_salida FROM reservas WHERE cliente_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,cliente_id);
            rs = pst.executeQuery();
            while (rs.next()){
                r = new Reserva(rs.getInt("reserva_id"), rs.getInt("cliente_id"), rs.getInt("habitacion_id"), rs.getString("fecha_ingreso"), rs.getString("fecha_salida"));
                al.add(r);
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
    public Reserva getReservabyCliente(int cliente_id) throws Exception {

        Reserva r = null;
        ResultSet rs = null;
        String sql = "SELECT reserva_id, cliente_id, habitacion_id, fecha_ingreso, fecha_salida FROM reservas WHERE cliente_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,cliente_id);
            rs = pst.executeQuery();
            while (rs.next()){
                r = new Reserva(rs.getInt("reserva_id"), rs.getInt("cliente_id"), rs.getInt("habitacion_id"), rs.getString("fecha_ingreso"), rs.getString("fecha_salida"));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return r;
    }

    @Override
    public int updateReserva(Reserva h, int nuevaHab, String nuevaFentrada, String nuevaFsalida) throws Exception {
        int r = 0;
        String sql = "UPDATE reservas SET habitacion_id = ?, fecha_ingreso = ?, fecha_salida = ? WHERE reserva_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,nuevaHab);
            pst.setString(2,nuevaFentrada);
            pst.setString(3,nuevaFsalida);
            pst.setInt(4,h.getReserva_id());
            r = pst.executeUpdate();

        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public int addReserva(int cliente_id, int habitacion_id, String fecha_ingreso, String fecha_salida) throws Exception {
        int r = 0;
        String sql = "INSERT INTO reservas (cliente_id, habitacion_id, fecha_ingreso, fecha_salida) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, cliente_id);
            pst.setInt(2, habitacion_id);
            pst.setString(3, fecha_ingreso);
            pst.setString(4, fecha_salida);
            r = pst.executeUpdate();

        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public int getReservaId(int cliente_id, String fecha_ingreso) throws Exception {
        int reserva_id = -1;
        ResultSet rs = null;
        String sql = "SELECT reserva_id FROM reservas WHERE cliente_id = ? AND fecha_ingreso = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,cliente_id);
            pst.setString(2,fecha_ingreso);
            rs = pst.executeQuery();
            while (rs.next()){
             reserva_id = rs.getInt("reserva_id");
            }

        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return reserva_id;
    }

    @Override
    public ArrayList<Reserva> getReservas(String nombre, String fechaNac) throws Exception {
        ArrayList<Reserva> al = new ArrayList<>();
        Reserva r = null;
        ResultSet rs = null;
        String sql = "SELECT r.reserva_id, r.cliente_id, r.habitacion_id, r.fecha_ingreso, r.fecha_salida FROM reservas AS r JOIN clientes AS c ON r.cliente_id = c.id WHERE c.nombre = ? AND fechaNac = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setString(1,nombre);
            pst.setString(2,fechaNac);
            rs = pst.executeQuery();
            while (rs.next()){
                r = new Reserva(rs.getInt("reserva_id"), rs.getInt("cliente_id"), rs.getInt("habitacion_id"), rs.getString("fecha_ingreso"), rs.getString("fecha_salida"));
                al.add(r);
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
    public void close() throws Exception {
        con.close();
    }
}
