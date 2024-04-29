package dao.implementations;

import biz.clientes;
import dao.interfaces.clientesDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class clientesDAOImpl implements clientesDAO, AutoCloseable {

    Connection con = null;

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public clientesDAOImpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }
Scanner sc = new Scanner(System.in);

    @Override
    public ArrayList<clientes> getClientes() throws Exception {
        ArrayList<clientes> al = new ArrayList<clientes>();
        clientes c = null;
        ResultSet rs = null;
        String sql = "SELECT id, nombre, fechaNac, fecha_ingreso, fecha_salida FROM clientes;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            rs = pst.executeQuery();
            while (rs.next()){
                c = new clientes(rs.getInt("id"), rs.getString("nombre"), rs.getString("fechaNac"),
                        rs.getString("fecha_ingreso"), rs.getString("fecha_salida"));
                al.add(c);
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
    public clientes getClienteByReserva(int reserva_id) throws Exception {
        clientes c = null;
        ResultSet rs = null;
        String sql = "SELECT c.id, c.nombre, c.fechaNac, c.fecha_ingreso, c.fecha_salida FROM clientes AS c JOIN reservas as r ON c.id = r.cliente_id WHERE r.reserva_id = ?;";
        try (PreparedStatement pts = con.prepareStatement(sql)){
            pts.setInt(1,reserva_id);
            rs = pts.executeQuery();
            while (rs.next()){
                c = new clientes(rs.getInt("id"), rs.getString("nombre"), rs.getString("fechaNac"),
                        rs.getString("fecha_ingreso"), rs.getString("fecha_salida"));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return c;
    }

    @Override
    public int updateCliente(int reserva_id) throws Exception {
        int r = 0;
        int s = 0;
        String sql = "UPDATE clientes SET fecha_ingreso = (Select fecha_ingreso FROM reservas WHERE reserva_id = ?);";
        String sql2 = "UPDATE clientes SET fecha_salida = (Select fecha_salida FROM reservas WHERE reserva_id = ?);";
        try (PreparedStatement psto = con.prepareStatement(sql); PreparedStatement pstt = con.prepareStatement(sql2);){
            psto.setInt(1,reserva_id);
            pstt.setInt(1, reserva_id);
            r = psto.executeUpdate();
            s = pstt.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        //Later
        return r;
    }

    @Override
    public int addCliente(clientes h) throws Exception {
        int r = 0;

        String sql = "INSERT INTO clientes (nombre, fechaNac, fecha_ingreso, fecha_salida) VALUES (?, ?, ?, ?);";
        try (PreparedStatement rs = con.prepareStatement(sql);){
        rs.setString(1, h.getNombre());
        rs.setString(2, h.getFechaNac());
        rs.setString(3, h.getFechaEntrada());
        rs.setString(4, h.getFechaSalida());
        r = rs.executeUpdate();

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
