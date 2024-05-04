package biz;

import dao.implementations.ReservaDAOImpl;
import dao.implementations.clientesDAOImpl;

import java.util.ArrayList;

public class ProgramaHotel {
    //Hay que hacer de nuevo ProgramaHotel para definir los requisitos y las funciones mas relevantes a realizar y hacerlas conjuntamente

    public int addReserva (Reserva h)throws  Exception {
        int r = 0;
        try (ReservaDAOImpl re = new ReservaDAOImpl(); clientesDAOImpl cl = new clientesDAOImpl();){
            re.addReserva(h.getCliente_id(), h.getHabitacion_id(),h.getFecha_ingreso(),h.getFecha_salida());
            cl.updateCliente(h.getFecha_ingreso(),h.getFecha_salida(),re.getReservaId(h.getCliente_id(),h.getFecha_ingreso()));
        }catch (Exception e){
            throw e;
        }
        return r;
    }
    public int addCliente (clientes h) throws Exception{
        int r = 0;
        try (clientesDAOImpl c = new clientesDAOImpl();){
            r = c.addCliente(h.getNombre(),h.getFechaNac());
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    //Usos de prueba
    //Inicio de sesión

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

    public int darIDNHUsuario (String nombre, String fechaNac)throws Exception{
        int idNH = -1;
        try (clientesDAOImpl c = new clientesDAOImpl();){
            idNH = c.getClienteId(nombre,fechaNac);
        }catch (Exception e){
            throw e;
        }
        return idNH;
    }

    // Aqui tenemos que hacer todo el addReserva para que luego utilize los datos de reserva para updatear al cliente


    public ArrayList<Reserva> getReservas() throws Exception {
        ArrayList<Reserva> al = new ArrayList<>();
        try (ReservaDAOImpl re = new ReservaDAOImpl();){
            al = re.getReservas();
        }catch (Exception e){
            throw e;
        }
        return al;
    }
    public String nombre (int id_usuario) throws Exception{
        String nombre = "";
        try (clientesDAOImpl c = new clientesDAOImpl();){
            nombre = c.getNombreCliente(id_usuario);
        }catch (Exception e){
            throw e;
        }

        return nombre;
    }

}
