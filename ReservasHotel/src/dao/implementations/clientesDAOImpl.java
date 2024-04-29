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
        return null;
    }

    @Override
    public int updateCliente(clientes h) throws Exception {
        return 0;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
