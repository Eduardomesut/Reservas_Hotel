package biz;

import dao.implementations.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ProgramaHotel {
    //Hay que hacer de nuevo ProgramaHotel para definir los requisitos y las funciones mas relevantes a realizar y hacerlas conjuntamente

    public int addReserva (Reserva h)throws  Exception {
        int r = 0;
        try (ReservaDAOImpl re = new ReservaDAOImpl(); clientesDAOImpl cl = new clientesDAOImpl(); habitacionesDAOImpl ha = new habitacionesDAOImpl();){
                re.addReserva(h.getCliente_id(), h.getHabitacion_id(),h.getFecha_ingreso(),h.getFecha_salida());
                cl.updateCliente(h.getFecha_ingreso(),h.getFecha_salida(),re.getReservaId(h.getCliente_id(),h.getFecha_ingreso()));

        }catch (Exception e){
            throw e;
        }
        return r;
    }
    public boolean habitacionOcupada (int hab_id, String fechaE, String fechaS) throws Exception{
        try (habitacionesDAOImpl ha = new habitacionesDAOImpl();){
            if (ha.habitacionOcupada(hab_id, fechaE, fechaS)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw e;
        }
    }
    public int addCliente (clientes h) throws Exception{
        int r = 0;
        try (clientesDAOImpl c = new clientesDAOImpl(); datosUsuarioDAOImpl d = new datosUsuarioDAOImpl();){
            r = c.addCliente(h.getNombre(),h.getFechaNac());
            d.insertDatos(c.getClienteId(h.getNombre(),h.getFechaNac()), 0,0);
        }catch (Exception e){
            throw e;
        }
        return r;
    }
    public int puntosNH (int id_cliente) throws Exception{
        int puntos = 0;
        try (datosUsuarioDAOImpl da = new datosUsuarioDAOImpl();){
            puntos = da.getDatosUser(id_cliente).getPuntosNH();
        }catch (Exception e){
            throw  e;
        }
        return puntos;
    }
    public double saldo (int id_cliente) throws Exception{
        double saldo = 0;
        try (datosUsuarioDAOImpl da = new datosUsuarioDAOImpl();){
            saldo = da.getDatosUser(id_cliente).getSaldo();

        }catch (Exception e){
            throw e;
        }
        return saldo;
    }

    //Usos de prueba
    //Inicio de sesión

    //Pruebas desechable para sacar los clientes
    public ArrayList<clientes> getClientes(String nombre, String nacimiento) throws Exception {
        ArrayList<clientes> al = new ArrayList<>();
        try (clientesDAOImpl c = new clientesDAOImpl();){
            al = c.getClientes(nombre, nacimiento);
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


    public String getReservas(int cliente_id) throws Exception {
        String message = "";
        ArrayList<Reserva> al = new ArrayList<>();
        try (ReservaDAOImpl re = new ReservaDAOImpl();hotelesDAOImpl ho = new hotelesDAOImpl();){
            al = re.getReservas(cliente_id);
            for (Reserva reser:al) {
                message += ho.getNombreHotel(reser.getHabitacion_id()) + " :: " + reser + "\n";
            }
        }catch (Exception e){
            throw e;
        }
        return message;
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
    public void meterHabitacion (int hotel_id, String numero, String tipo, double precio_alto, double precio_medio, double precio_bajo) throws Exception{
        int habitacion_id = 0;
        int hotel;
        try (habitacionesDAOImpl hab = new habitacionesDAOImpl(); preciosDAOImpl pre = new preciosDAOImpl();){
            hotel = hab.addHabitacion(hotel_id, numero, tipo);
            habitacion_id = hab.getIdHab(hotel_id, numero, tipo);
            pre.addprecios(precio_alto, precio_medio, precio_bajo, habitacion_id);
        }catch (Exception e){
            throw e;
        }
    }
    public Reserva reservaDeUsuario (int id_usuario)throws Exception{
        Reserva nueva = null;
        try (ReservaDAOImpl r = new ReservaDAOImpl();){
            nueva = r.getReservabyCliente(id_usuario);
        }
        return nueva;
    }
    public ArrayList<hoteles> getHoteles () throws Exception{
        ArrayList<hoteles>al = new ArrayList<>();
        try (hotelesDAOImpl h = new hotelesDAOImpl();){
            al = h.getHoteles();
        }catch (Exception e){
            throw e;
        }
        return al;
    }
    public ArrayList<habitaciones> getHabitaciones(int id_hotel)throws Exception{
        ArrayList<habitaciones> al = new ArrayList<>();
        try (habitacionesDAOImpl ha = new habitacionesDAOImpl();){
            al = ha.getHabitacionesFromHotel(id_hotel);
        }catch (Exception e){
            throw e;
        }
        return al;
    }
    public boolean getIDCorrecto(int id_cliente) throws Exception{
        try (clientesDAOImpl cl = new clientesDAOImpl();){
            if (cl.getNombreCliente(id_cliente) != null){
                return true;
            }
        }catch (Exception e){
            throw e;
        }
        return false;
    }

    public double getPrecioByHabyFecha(int id_habitacion, String fecha_entrada, String fecha_salida) throws Exception {
        double precio = 0;
        try (preciosDAOImpl pr = new preciosDAOImpl();) {
            precios nuevo = pr.getPrecio(id_habitacion, fecha_entrada, fecha_salida);
            LocalDate fechaEntrada = LocalDate.parse(fecha_entrada, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate fechaSalida = LocalDate.parse(fecha_salida, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int diasReserva = (int)ChronoUnit.DAYS.between(fechaEntrada,fechaSalida);
            int mes = fechaEntrada.getMonthValue();
            if (mes >= 6 && mes <= 8) {
                precio = nuevo.getTemporada_alta() * diasReserva;
            } else if ((mes >= 3 && mes <= 5) || (mes >= 9 && mes <= 11)) {
                precio = nuevo.getTemporada_media() * diasReserva;
            } else {
                precio = nuevo.getTemporada_baja() * diasReserva;
            }
        } catch (Exception e) {
            throw e;
        }
        return precio;
    }

    public ArrayList<Reserva> getReservasnombre(String nombre, String fechaNac) throws Exception {
        ArrayList<Reserva> al = new ArrayList<>();
        try (ReservaDAOImpl re = new ReservaDAOImpl(); hotelesDAOImpl ho = new hotelesDAOImpl();){
            al = re.getReservas(nombre, fechaNac);

        }catch (Exception e){
            throw e;
        }
        return al;
    }
    public void updateReserva (Reserva reser, int nuevaHab, String nuevaFentrada, String nuevaFsalida) throws Exception{
     try (ReservaDAOImpl re = new ReservaDAOImpl();){
         re.updateReserva(reser, nuevaHab, nuevaFentrada, nuevaFsalida);
     }catch (Exception e){
         throw e;
     }
    }
    public void deleteReserva (Reserva reser) throws Exception {
        try (ReservaDAOImpl re = new ReservaDAOImpl();){
            re.deleteReserva(reser);
        }catch (Exception e){
            throw e;
        }
    }
    public void addPassword(int cliente_id, String textPass) throws Exception{
        try (passwordDAOImpl pass = new passwordDAOImpl();){
            pass.addPassword(cliente_id,textPass);
        }catch (Exception e){
            throw e;
        }
    }
    public String getPassword (int id_cliente) throws Exception{
        String password = null;
        try (passwordDAOImpl pass = new passwordDAOImpl();){
            password = pass.getPasswordByCliente(id_cliente);
        }catch (Exception e){
            throw e;
        }
        return password;
    }
    public int registrarHotel (String nombre, String ubicacion) throws Exception{
        int r = 0;
        try (hotelesDAOImpl ho = new hotelesDAOImpl();){
            ho.registrarHotel(nombre, ubicacion);
        }catch (Exception e){
            throw e;
        }
        return r;
    }
    public int addPuntosNH (int id_cliente, int puntos) throws Exception{
        int r = 0;
        try (datosUsuarioDAOImpl dat = new datosUsuarioDAOImpl();){
            r = dat.updatePuntos(id_cliente, puntos);
        }catch (Exception e){
            throw e;
        }
        return r;
    }
    public int addSaldo (int id_cliente, double saldo) throws Exception{
        int r = 0;
        try (datosUsuarioDAOImpl dat = new datosUsuarioDAOImpl();){
            r = dat.updateSaldo(id_cliente, saldo);
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    public ArrayList<premios>listadoPremios() throws Exception{
        ArrayList<premios>al = new ArrayList<>();
        try (premiosDAOImpl prem = new premiosDAOImpl();){
            al = prem.listaPremios();

        }catch (Exception e){
            throw e;
        }
        return al;
    }
}
