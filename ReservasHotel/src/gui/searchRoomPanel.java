package gui;

import biz.ProgramaHotel;
import biz.habitaciones;
import biz.hoteles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class searchRoomPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;
    private int hotel_id;

    public searchRoomPanel(HotelGUI frame, ProgramaHotel ph, int hotel_id) throws Exception {
        this.frame = frame;
        this.ph = ph;
        this.hotel_id = hotel_id;
        setLayout(new GridLayout(5, 1, 10, 10));
        add(new JLabel("Habitaciones disponibles"));

            try {
                add(new JLabel(showAvailableRooms(hotel_id)));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        JButton backButton = new JButton("Salir");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }

    private String showAvailableRooms(int hotel_id) throws Exception {

        // Implementación para mostrar habitaciones disponibles
        // Supongamos que muestra un diálogo con habitaciones
        ArrayList<habitaciones> habs = ph.getHabitaciones(hotel_id);
        String message = "habitaciones disponibles:\n";
        for (habitaciones hab : habs) {
            message += "ID: " + hab.getId_hab() + " - " + hab.getTipo() + " - " + hab.getNum_habitacion() + "\n";

        }
        return message;
    }


}
