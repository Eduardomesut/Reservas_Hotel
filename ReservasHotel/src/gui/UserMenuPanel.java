package gui;

import biz.ProgramaHotel;
import biz.Reserva;
import biz.hoteles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserMenuPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;
    private int userID;

    public UserMenuPanel(HotelGUI frame, ProgramaHotel ph, int userID) throws Exception {
        this.frame = frame;
        this.ph = ph;
        this.userID = userID;
        setLayout(new GridLayout(5, 1, 10, 10));

        String userName = ph.nombre(userID);
        add(new JLabel("Bienvenido al menú " + userName));

        JButton searchRoomsButton = new JButton("Buscar habitaciones");
        searchRoomsButton.addActionListener(e -> {
            try {
                showAvailableRooms();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(searchRoomsButton);

        JButton makeReservationButton = new JButton("Hacer una reserva");
        makeReservationButton.addActionListener(e -> makeReservation());
        add(makeReservationButton);

        JButton viewReservationsButton = new JButton("Ver tus reservas");
        viewReservationsButton.addActionListener(e -> {
            try {
                showReservations();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(viewReservationsButton);

        JButton backButton = new JButton("Salir");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }

    private void showAvailableRooms() throws Exception {
        // Implementación para mostrar habitaciones disponibles
        // Supongamos que muestra un diálogo con habitaciones
        ArrayList<hoteles> hotels = ph.getHoteles();
        String message = "Hoteles disponibles:\n";
        for (hoteles hotel : hotels) {
            message += "ID: " + hotel.getHotel_id() + " - " + hotel.getNombre() + " - " + hotel.getUbicacion() + "\n";
        }
        JOptionPane.showMessageDialog(this, message);
    }

    private void makeReservation() {
        // Implementación para hacer una reserva
        // Mostramos un diálogo para obtener ID de habitación y fechas
        String roomID = JOptionPane.showInputDialog(this, "Ingrese ID de habitación:");
        String checkIn = JOptionPane.showInputDialog(this, "Fecha de ingreso (YYYY-MM-DD):");
        String checkOut = JOptionPane.showInputDialog(this, "Fecha de salida (YYYY-MM-DD):");
        try {
            Reserva newReservation = new Reserva(userID, Integer.parseInt(roomID), checkIn, checkOut);
            ph.addReserva(newReservation);
            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al hacer la reserva: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showReservations() throws Exception {
        // Implementación para mostrar reservas
        ArrayList<Reserva> reservations = ph.getReservas(userID);
        String message = "Tus reservas:\n";
        for (Reserva reservation : reservations) {
            message += reservation.toString() + "\n";
        }
        JOptionPane.showMessageDialog(this, message);
    }
}
