package dao.interfaces;

import biz.clientes;

import java.util.ArrayList;

public interface clientesDAO {

    public ArrayList<clientes> getClientes() throws Exception;
    public clientes getClienteByReserva(int reserva_id) throws Exception;
    public int updateCliente(int reserva_id) throws Exception;
    public int addCliente(clientes h)throws Exception;
}
