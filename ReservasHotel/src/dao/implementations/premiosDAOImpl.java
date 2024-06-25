package dao.implementations;

import biz.premios;
import dao.interfaces.premiosDAO;
import utils.Configuration;

import java.sql.*;

import java.util.ArrayList;

public class premiosDAOImpl implements premiosDAO,AutoCloseable{

    Connection con = null;

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public premiosDAOImpl() throws Exception{

        con = DriverManager.getConnection(Configuration.URL);
    }
    @Override
    public int addpremio(premios nuevo) throws Exception {
        int r = 0;
        String sql = "INSERT INTO premios (nombre, descripcion, coste) VALUES (?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setString(1, nuevo.getNombre());
            pst.setString(2, nuevo.getDescripcion());
            pst.setInt(3, nuevo.getCoste());
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public ArrayList<premios> listaPremios() throws Exception {
        ArrayList<premios>al = new ArrayList<>();
        ResultSet rs = null;
        premios nuevo = null;
        String sql = "SELECT premioId, nombre, descripcion, coste FROM premios;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            rs = pst.executeQuery();
            while (rs.next()){
                nuevo = new premios(rs.getInt("premioId"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("coste"));
                al.add(nuevo);
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
