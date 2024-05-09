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
    public ArrayList<clientes> getClientes(String nombre) throws Exception {
        ArrayList<clientes> al = new ArrayList<clientes>();
        clientes c = null;
        int r = 0;
        ResultSet rs = null;
        String sql = "SELECT nombre, fechaNac FROM clientes WHERE 1 = 1 ";
        if (nombre != null && !nombre.equals("")){
            sql = sql + " AND nombre LIKE ? ";
        }
        try (PreparedStatement pst = con.prepareStatement(sql);){
            if (nombre != null && !nombre.equals("")){
                r++;
                pst.setString(r,nombre + "%");
            }
            rs = pst.executeQuery();
            while (rs.next()){
                c = new clientes(rs.getString("nombre"), rs.getString("fechaNac"));
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
    public int updateCliente(String fecha_entrada, String fecha_salida, int reserva_id) throws Exception {
        int r = 0;
        int s = 0;
        String sql = "UPDATE clientes SET fecha_ingreso = ? WHERE id = (SELECT cliente_id FROM reservas WHERE reserva_id = ?);";
        String sql2 = "UPDATE clientes SET fecha_salida = ? WHERE id = (SELECT cliente_id FROM reservas WHERE reserva_id = ?);";
        try (PreparedStatement psto = con.prepareStatement(sql); PreparedStatement pstt = con.prepareStatement(sql2);){
            psto.setString(1,fecha_entrada);
            psto.setInt(2, reserva_id);
            pstt.setString(1, fecha_salida);
            pstt.setInt(2,reserva_id);
            r = psto.executeUpdate();
            s = pstt.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        //Later
        return r;
    }

    @Override
    public int addCliente(String nombre, String fechaNac) throws Exception {
        int r = 0;

        String sql = "INSERT INTO clientes (nombre, fechaNac) VALUES (?, ?);";
        try (PreparedStatement rs = con.prepareStatement(sql);){
        rs.setString(1, nombre);
        rs.setString(2, fechaNac);
        r = rs.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public int getClienteId(String nombre, String fechaNac) throws Exception {
        int cliente_id = -1;
        ResultSet rs = null;
        String sql = "SELECT id FROM clientes WHERE nombre = ? AND fechaNac = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setString(1,nombre);
            pst.setString(2,fechaNac);
            rs = pst.executeQuery();
            while (rs.next()){
                cliente_id = rs.getInt("id");
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return cliente_id;
    }

    @Override
    public String getNombreCliente(int id_cliente) throws Exception {
        String nombre = null;
        ResultSet rs = null;
        String sql = "SELECT nombre FROM clientes WHERE id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,id_cliente);
            rs = pst.executeQuery();
            while (rs.next()){
                nombre = rs.getString("nombre");
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return nombre;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
