package dao.implementations;

import biz.Reserva;
import dao.interfaces.ReservaDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    public ArrayList<Reserva> getReservas() throws Exception {
        return null;
    }

    @Override
    public Reserva getReservabyCliente(int cliente_id) throws Exception {
        return null;
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
    public int addReserva(Reserva h) throws Exception {
        int r = 0;
        String sql = "INSERT INTO reservas VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1, h.getReserva_id());
            pst.setInt(2, h.getCliente_id());
            pst.setInt(3, h.getHabitacion_id());
            pst.setString(4, h.getFecha_ingreso());
            pst.setString(5, h.getFecha_salida());
            r = pst.executeUpdate();

        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public void close() throws Exception {

    }
}
