package dao.implementations;

import biz.password;
import dao.interfaces.passwordDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class passwordDAOImpl implements passwordDAO, AutoCloseable {

    Connection con = null;
    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public passwordDAOImpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }
    @Override
    public int addPassword(int cliente_id, String textPass) throws Exception {
        int r = 0;
        String sql = "INSERT INTO password (cliente_id, pass) VALUES (?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, cliente_id);
            pst.setString(2, textPass);
            pst.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public String getPasswordByCliente(int cliente_id) throws Exception {
        String password = null;
        ResultSet rs = null;
        String sql = "SELECT pass FROM password WHERE cliente_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, cliente_id);
            rs = pst.executeQuery();
            while (rs.next()){
                password = rs.getString("pass");
            }

        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return password;
    }
    @Override
    public void close() throws Exception {
        con.close();
    }
}
