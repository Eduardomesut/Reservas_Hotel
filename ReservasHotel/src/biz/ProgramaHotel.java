package biz;

import dao.implementations.ReservaDAOImpl;
import dao.implementations.clientesDAOImpl;

import java.util.ArrayList;

public class ProgramaHotel {


    //Hay que hacer de nuevo ProgramaHotel para definir los requisitos y las funciones mas relevantes a realizar y hacerlas conjuntamente

    //Inicio de sesión
    public int addCliente (clientes h) throws Exception{

        int r = 0;
        try (clientesDAOImpl c = new clientesDAOImpl();){
            r = c.addCliente(h);
        }catch (Exception e){
            throw e;
        }

        return r;
    }

    //Pruebas desechable para sacar los clientes
    public ArrayList<clientes> getClientes() throws Exception {
        ArrayList<clientes> al = new ArrayList<>();
        try (clientesDAOImpl c = new clientesDAOImpl();){
            al = c.getClientes();
        } catch (Exception e) {
            throw e;
        }
        return al;
    }
    public clientes getClienteByReserva(int id_reserva)throws Exception {
        clientes nuevo = null;
        try (clientesDAOImpl c = new clientesDAOImpl();){
            nuevo = c.getClienteByReserva(id_reserva);
        }catch (Exception e){
            throw e;
        }

        return nuevo;
    }

    // Aqui tenemos que hacer todo el addReserva para que luego utilize los datos de reserva para updatear al cliente
    public Reserva addReserva (Reserva r)throws  Exception {

        try (ReservaDAOImpl re = new ReservaDAOImpl(); clientesDAOImpl cl = new clientesDAOImpl();){
            re.addReserva(r);
            cl.updateCliente(r.getFecha_ingreso(), r.getFecha_salida(), r.getReserva_id());
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    public ArrayList<Reserva> getReservas() throws Exception {
        ArrayList<Reserva> al = new ArrayList<>();
        try (ReservaDAOImpl re = new ReservaDAOImpl();){
            al = re.getReservas();
        }catch (Exception e){
            throw e;
        }
        return al;
    }

}
