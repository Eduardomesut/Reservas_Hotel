package dao.interfaces;

import biz.clientes;
import biz.password;

public interface passwordDAO {

    public int addPassword(int cliente_id, String textPass)throws Exception;
    public String getPasswordByCliente(int cliente_id) throws Exception;

}
