package dao.implementations;

import biz.hoteles;
import dao.interfaces.hotelesDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
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
        return null;
    }

    @Override
    public hoteles getHotelByReserva(int reserva_id) throws Exception {
        return null;
    }

    @Override
    public int updateHotel(hoteles h) throws Exception {
        return 0;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
