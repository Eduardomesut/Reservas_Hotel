package biz;

import dao.implementations.clientesDAOImpl;

import java.util.ArrayList;

public class ProgramaHotel {



    //Prueba desechable para sacar los clientes
    public ArrayList<clientes> getClientes() throws Exception {
        ArrayList<clientes> al = new ArrayList<>();
        try (clientesDAOImpl c = new clientesDAOImpl();){
            al = c.getClientes();
        } catch (Exception e) {
            throw e;
        }
        return al;
    }
}
