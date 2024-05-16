package dao.interfaces;

import biz.clientes;

import java.util.ArrayList;

public interface clientesDAO {

    public ArrayList<clientes> getClientes(String nombre, String nacimiento) throws Exception;
    public clientes getClienteByReserva(int reserva_id) throws Exception;
    public int updateCliente(String fecha_entrada, String fecha_salida, int reserva_id) throws Exception;
    public int addCliente(String nombre, String fechaNac)throws Exception;
    public int getClienteId(String nombre, String fechaNac) throws Exception;
    public String getNombreCliente (int id_cliente)throws Exception;
}
