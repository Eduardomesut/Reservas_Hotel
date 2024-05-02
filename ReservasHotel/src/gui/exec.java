package gui;

import biz.ProgramaHotel;
import biz.Reserva;
import biz.clientes;

import java.util.ArrayList;
import java.util.Scanner;

public class exec {

    //Hay que hacer de nuevo ProgramaHotel para definir los requisitos y las funciones mas relevantes a realizar y hacerlas conjuntamente
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProgramaHotel ph = null;
        try {
            ph = new ProgramaHotel();

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            ArrayList<Reserva> reservas = ph.getReservas();
            System.out.println("-----RESERVAS-----");
            for (Reserva reser:reservas) {
                System.out.println(reser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        //Inicio de sesion de usuario y añadir nuevo cliente
        //iniciarSesion (ph, sc);


        // Cuando añada una reserva updatear tambien el clientes para añadir fecha de entrada y salida

        //Hacer un método para que al iniciar sesión el usuario consiga ver su id asignado para su cuenta,para posteriores consultas

        //Prueba desechable de clientes para el funcionamiento de la DB
        try {
            //Hacer metodo para que se añadan las reservas desde la aplicación
            Reserva r = new Reserva(19, 3, 2, "2024-06-12", "2024-06-15");
            ph.addReserva(r);
            clientes nuevo = ph.getClienteByReserva(3);
            System.out.println(nuevo);
            ArrayList<clientes>al = ph.getClientes();
            System.out.println("CLIENTES");
            System.out.println("------------------");
            for (clientes cliente: al) {
                System.out.println(cliente);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void iniciarSesion(ProgramaHotel ph, Scanner sc){

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

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
