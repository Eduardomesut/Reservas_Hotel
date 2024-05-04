package gui;

import biz.ProgramaHotel;
import biz.Reserva;
import biz.clientes;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Scanner;

public class exec {

    //Hay que hacer de nuevo ProgramaHotel para definir los requisitos y las funciones mas relevantes a realizar y hacerlas conjuntamente
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProgramaHotel ph = null;
        try {
            String opcion;
            ph = new ProgramaHotel();
            do {
                System.out.println("Quiere iniciar sesión o registrarse?");
                System.out.println("Pulse 1 para registrarse o 2 para iniciar sesion");
                opcion = sc.nextLine();
                if (!opcion.equalsIgnoreCase("s")){
                    int id = -1;
                    if (opcion.equalsIgnoreCase("1")){
                        id = registrarse(ph, sc);
                    } else if (opcion.equalsIgnoreCase("2")) {
                        id = iniciarSesion(ph, sc);
                    }
                    if (id != -1){
                        System.out.println("Desea acceder al menú de usuario? ");
                        menu(id, ph, sc);
                    }else{
                        System.out.println("ID erróneo");
                    }
                }
            }while (!opcion.equalsIgnoreCase("s"));


        }catch (Exception e){
            e.printStackTrace();
        }

        //Inicio de sesion de usuario y añadir nuevo cliente
        //iniciarSesion (ph, sc);


    }
    public static int registrarse(ProgramaHotel ph, Scanner sc){
        int id = -1;
        String nombre;
        String fecha_nac;
        System.out.println("Has elegido iniciar sesión en la cadena NH");
        System.out.println("-----------------------------------");
        System.out.print("Introduce tu nombre y apellidos: ");
        nombre = sc.nextLine();
        System.out.print("Introduce tu fecha de nacimiento (YYYY-MM-DD): ");
        fecha_nac = sc.nextLine();
        try {
            clientes add = new clientes(nombre,fecha_nac);
            ph.addCliente(add);
            System.out.println("Enhorabuena! Esta registrado en NH Hoteles");
            int IDNH = ph.darIDNHUsuario(add.getNombre(),add.getFechaNac());
            System.out.println("Aqui tienes tu ID personal para iniciar sesión, recuerda... No lo olvide");
            System.out.println("Usuario NH: " + IDNH);
            if (IDNH != -1){
                id = IDNH;
            }else {
                System.out.println("Error en la generación de su ID, vuelva a intentarlo");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }
    public static void addReserva (ProgramaHotel ph, Scanner sc) throws Exception {
        int id = 17;
        int habitacion_id = 2;
        String fechaIngreso;
        String fechaSalida;
        System.out.print("Fecha ingreso: ");
        fechaIngreso = sc.nextLine();
        System.out.print("Fecha salida: ");
        fechaSalida = sc.nextLine();
        Reserva nueva = new Reserva(id,habitacion_id,fechaIngreso,fechaSalida);
        ph.addReserva(nueva);
    }
    public static int iniciarSesion(ProgramaHotel ph, Scanner sc){
        int id = -1;
        System.out.println("introduce su ID de NH: ");
        id = Integer.parseInt(sc.nextLine());
        return id;
    }
    public static void menu (int IDNH, ProgramaHotel ph, Scanner sc)throws Exception{
        String nombre = "";
        nombre = ph.nombre(IDNH);

        System.out.println("Bienvenido al menú " + nombre);
    }
}
