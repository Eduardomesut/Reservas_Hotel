package dao.implementations;

import biz.datosUsuario;
import dao.interfaces.datosUsuarioDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class datosUsuarioDAOImpl implements datosUsuarioDAO, AutoCloseable{

    Connection con = null;

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public datosUsuarioDAOImpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }

    @Override
    public int insertDatos(int cliente_id, double sueldo, int puntos) throws Exception {
        int r = 0;
        String sql = "INSERT INTO datos_usuario (id_usuario, saldo, puntosNH) VALUES (?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, cliente_id);
            pst.setDouble(2, sueldo);
            pst.setInt(3, puntos);
            r = pst.executeUpdate();

        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public datosUsuario getDatosUser(int cliente_id) throws Exception {
        datosUsuario dat = null;
        ResultSet rs = null;
        String sql = "SELECT id_usuario, saldo, puntosNH FROM datos_usuario WHERE id_usuario = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, cliente_id);
            rs = pst.executeQuery();
            while (rs.next()){
                dat = new datosUsuario(rs.getInt("id_usuario"), rs.getDouble("saldo"), rs.getInt("puntosNH"));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return dat;
    }

    @Override
    public int updateSaldo(int cliente_id, double saldo) {
        return 0;
    }

    @Override
    public int updatePuntos(int cliente_id, int puntos) {
        return 0;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
