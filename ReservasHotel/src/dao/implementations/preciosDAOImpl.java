package dao.implementations;

import biz.Reserva;
import biz.precios;
import dao.interfaces.preciosDAO;
import utils.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public precios getPrecio(int id_habitacion, String fecha_entrada, String fecha_salida) throws Exception {
        precios nuevo = null;
        ResultSet rs = null;
        String sql = "SELECT id, temporada_alta, temporada_media, temporada_baja, id_habitacion FROM precios WHERE id_habitacion =?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setInt(1,id_habitacion);
            rs = pst.executeQuery();
            while (rs.next()){
                nuevo = new precios(rs.getInt("id"), rs.getDouble("temporada_alta"), rs.getDouble("temporada_media"), rs.getDouble("temporada_baja"), rs.getInt("id_habitacion"));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return nuevo;
    }

    @Override
    public precios getPrecioByReserva(int id_reserva) throws Exception {
        precios nuevo = null;
        ResultSet rs = null;
        String sql = "SELECT p.id, p.temporada_alta, p.temporada_media, p.temporada_baja, p.id_habitacion FROM precios AS p JOIN reservas AS r ON p.id_habitacion = r.habitacion_id WHERE r.reserva_id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql)){
            pst.setInt(1,id_reserva);
            rs = pst.executeQuery();
            while (rs.next()){
                nuevo = new precios(rs.getInt("p.id"), rs.getDouble("p.temporada_alta"), rs.getDouble("p.temporada_media"), rs.getDouble("p.temporada_baja"), rs.getInt("p.id_habitacion"));
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return nuevo;
    }

    @Override
    public int updatePrecio(int idPrecio_cambio, double cambio_alto, double cambio_medio, double cambio_bajo) throws Exception {
        int r = 0;
        String sql = "UPDATE precios SET temporada_alta = ?, temporada_media = ?, temporada_baja = ? WHERE id = ?;";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setDouble(1,cambio_alto);
            pst.setDouble(2,cambio_medio);
            pst.setDouble(3,cambio_bajo);
            pst.setInt(4,idPrecio_cambio);
            r = pst.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public int addprecios(double precio_alto, double precio_medio, double precio_bajo, int habitacion_id) throws Exception {
        int r = 0;
        String sql = "INSERT INTO precios (temporada_alta, temporada_media, temporada_baja, id_habitacion) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pst = con.prepareStatement(sql);){
            pst.setDouble(1,precio_alto);
            pst.setDouble(2,precio_medio);
            pst.setDouble(3,precio_bajo);
            pst.setInt(4,habitacion_id);
            r = pst.executeUpdate();
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
