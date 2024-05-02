package dao.implementations;

import biz.Reserva;
import biz.precios;
import dao.interfaces.preciosDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class preciosDAOImpl implements preciosDAO, AutoCloseable {

    Connection con = null;

    static {
        try {
            Class.forName(Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public preciosDAOImpl() throws Exception {
        con = DriverManager.getConnection(Configuration.URL);
    }

    @Override
    public precios getPrecio(int id_habitacion, String fecha_entrada) throws Exception {
        return null;
    }

    @Override
    public precios getPrecioByReserva(Reserva nueva) throws Exception {
        return null;
    }

    @Override
    public int updatePrecio(precios cambio) throws Exception {
        return 0;
    }

    @Override
    public int addprecios(precios add) throws Exception {
        return 0;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
