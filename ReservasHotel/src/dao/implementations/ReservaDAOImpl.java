package dao.implementations;

import biz.Reserva;
import dao.interfaces.ReservaDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
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
    public int updateReserva(Reserva h) throws Exception {
        return 0;
    }

    @Override
    public void close() throws Exception {

    }
}
