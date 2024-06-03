package dao.interfaces;

import biz.premios;

import java.util.ArrayList;

public interface premiosDAO {

    public int addpremio(premios nuevo) throws Exception;
    public ArrayList<premios> listaPremios()throws Exception;
}
