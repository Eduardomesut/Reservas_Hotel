package dao.interfaces;

import biz.datosUsuario;

public interface datosUsuarioDAO {

    public int insertDatos (int cliente_id, double sueldo, int puntos) throws Exception;

    public datosUsuario getDatosUser(int cliente_id) throws Exception;

    public int updateSaldo(int cliente_id, double saldo);
    public int updatePuntos(int cliente_id, int puntos);
}
