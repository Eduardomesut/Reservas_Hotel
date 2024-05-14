package biz;

public class password {
    private int id_password;
    private int cliente_id;
    private String textPass;

    public password(int id_password, int cliente_id, String textPass) {
        this.id_password = id_password;
        this.cliente_id = cliente_id;
        this.textPass = textPass;
    }

    @Override
    public String toString() {
        return  id_password + " :: " + cliente_id + " :: " + textPass;
    }

    public int getId_password() {
        return id_password;
    }

    public void setId_password(int id_password) {
        this.id_password = id_password;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getTextPass() {
        return textPass;
    }

    public void setTextPass(String textPass) {
        this.textPass = textPass;
    }
}
