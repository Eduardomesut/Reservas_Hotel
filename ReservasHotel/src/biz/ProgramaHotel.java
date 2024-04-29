package biz;

import dao.implementations.clientesDAOImpl;

import java.util.ArrayList;

public class ProgramaHotel {




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
}
