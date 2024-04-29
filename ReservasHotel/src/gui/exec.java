package gui;

import biz.ProgramaHotel;
import biz.clientes;

import java.util.ArrayList;
import java.util.Scanner;

public class exec {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProgramaHotel ph = null;
        try {
            ph = new ProgramaHotel();

        }catch (Exception e){
            e.printStackTrace();
        }




        //Prueba desechable de clientes para el funcionamiento de la DB
        try {
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
}
