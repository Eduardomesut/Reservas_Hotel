package gui;

import biz.ProgramaHotel;
import biz.Reserva;
import biz.habitaciones;
import biz.hoteles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        makeReservationButton.addActionListener(e -> {
            try {
                makeReservation();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
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
        // Obtener la lista de hoteles
        ArrayList<hoteles> hotels = ph.getHoteles();

        // Crear un panel para los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Un botón por fila

        // Crear un botón para cada hotel y agregarlo al panel
        for (hoteles hotel : hotels) {
            JButton button = new JButton(hotel.getNombre() + " - " + hotel.getUbicacion());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showRoomsForHotel(hotel.getHotel_id());
                }
            });
            panel.add(button);
        }

        // Crear y mostrar el diálogo con los botones
        JScrollPane scrollPane = new JScrollPane(panel); // Para manejar el desbordamiento de texto
        JOptionPane.showMessageDialog(null, scrollPane, "Seleccione un hotel", JOptionPane.PLAIN_MESSAGE);
    }

    private void showRoomsForHotel(int hotel_id) {
        try {
            JTextArea textArea = new JTextArea(showAvailableRooms2(hotel_id).toString());
            textArea.setEditable(false); // Si solo quieres mostrar la información
            JScrollPane scrollPane = new JScrollPane(textArea); // Para manejar el desbordamiento de texto
            JOptionPane.showMessageDialog(null, scrollPane, "Habitaciones disponibles", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    private void makeReservation() throws Exception {
        double precio = 0;
        // Implementación para hacer una reserva
        // Mostramos un diálogo para obtener ID de habitación y fechas
        String roomID = JOptionPane.showInputDialog(this, "Ingrese ID de habitación:");
        String checkIn = JOptionPane.showInputDialog(this, "Fecha de ingreso (YYYY-MM-DD):");
        String checkOut = JOptionPane.showInputDialog(this, "Fecha de salida (YYYY-MM-DD):");
        precio = ph.getPrecioByHabyFecha(Integer.parseInt(roomID), checkIn, checkOut);
        JOptionPane pago = new JOptionPane();
        int respuesta = (pago.showConfirmDialog(this, "Precio: " + precio + " Euros", "Confirmación de reserva", JOptionPane.YES_NO_OPTION));
        if (respuesta == JOptionPane.YES_OPTION){
            try {
                Reserva newReservation = new Reserva(userID, Integer.parseInt(roomID), checkIn, checkOut);
                ph.addReserva(newReservation);
                JOptionPane.showMessageDialog(this, "Reserva realizada con éxito!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al hacer la reserva: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (respuesta == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Reserva cancelada");
        } else{
            JOptionPane.showMessageDialog(this, "Saliendo" + pago.getInputValue().toString() + JOptionPane.ERROR_MESSAGE);
        }
    }
    private void showReservations() throws Exception {
        // Implementación para mostrar reservas
        String message = "Tus reservas:\n" + ph.getReservas(userID);
        JOptionPane.showMessageDialog(this, message);
    }

    private String showAvailableRooms2(int hotel_id) throws Exception {

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
