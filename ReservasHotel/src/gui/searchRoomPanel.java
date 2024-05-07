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

    public searchRoomPanel(HotelGUI frame, ProgramaHotel ph, int hotel_id, int user_id) throws Exception {
        this.frame = frame;
        this.ph = ph;
        this.hotel_id = hotel_id;
        setLayout(new GridLayout(5, 1, 10, 10));
        add(new JLabel("Habitaciones disponibles"));

            try {
                JTextArea textArea = new JTextArea(showAvailableRooms(hotel_id).toString());
                textArea.setEditable(false); // Si solo quieres mostrar la información
                JScrollPane scrollPane = new JScrollPane(textArea); // Para manejar el desbordamiento de texto
                JOptionPane.showMessageDialog(null, scrollPane); // Mostrar en un diálogo

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        JButton backButton = new JButton("Salir");
        backButton.addActionListener(e -> {
            try {
                frame.switchToUserMenu(user_id);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(backButton);
    }

    private String showAvailableRooms(int hotel_id) throws Exception {

        // Implementación para mostrar habitaciones disponibles
        // Supongamos que muestra un diálogo con habitaciones
        ArrayList<habitaciones> habs = ph.getHabitaciones(hotel_id);
        StringBuilder message = new StringBuilder("habitaciones disponibles:\n");

        for (habitaciones hab : habs) {
            message.append("\nID: ")
                    .append(hab.getId_hab())
                    .append(" - ")
                    .append(hab.getTipo())
                    .append(" - ")
                    .append(hab.getNum_habitacion())
                    .append("\n");
        }
        return message.toString();
    }


}
