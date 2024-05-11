package gui;

import biz.*;

import javax.swing.*;
import biz.ProgramaHotel;
import biz.Reserva;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminMenuPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;

    public AdminMenuPanel(HotelGUI frame, ProgramaHotel ph) throws Exception {
        this.frame = frame;
        this.ph = ph;
        setLayout(new GridLayout(5, 1, 10, 10));


        add(new JLabel("Menú de administrador"));

        JButton listarClientes = new JButton("Listar clientes");
        listarClientes.addActionListener(e -> {
            try {
                listarClientes();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(listarClientes);


        JButton backButton = new JButton("Salir");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }
    private void listarClientes() throws Exception {
        ArrayList<clientes>al = new ArrayList<>();
        String nombre = JOptionPane.showInputDialog(this, "Nombre a buscar:");
        al = ph.getClientes(nombre);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Un botón por fila
        String message = "Clientes:\n";
        for (clientes cli:al) {
            JButton button = new JButton(cli.getNombre() + " - " + cli.getFechaNac());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        showReservasClientes(cli.getNombre(), cli.getFechaNac());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            panel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        JOptionPane.showMessageDialog(null, scrollPane, "Selecciona un cliente", JOptionPane.PLAIN_MESSAGE);

    }
    private void showReservasClientes (String nombre, String fechaNac) throws Exception{
        ArrayList<Reserva>al = new ArrayList<>();
        al = ph.getReservasnombre(nombre, fechaNac);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Un botón por fila
        String message = "Reservas:\n";
        for (Reserva reser:al) {
            JPanel reservaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton editButton = new JButton("Editar");
            JButton deleteButton = new JButton("Eliminar");

            editButton.addActionListener(e -> {
                try {
                    editarReserva(reser);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            deleteButton.addActionListener(e -> {
                try {
                    eliminarReserva(reser);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });

            reservaPanel.add(new JLabel("ID: " + reser.getCliente_id() + " - " + reser.getFecha_ingreso() + " - " + reser.getFecha_salida()));
            reservaPanel.add(editButton);
            reservaPanel.add(deleteButton);
            panel.add(reservaPanel);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        JOptionPane.showMessageDialog(null, scrollPane, "Selecciona una reserva a editar", JOptionPane.PLAIN_MESSAGE);
    }

    private void editarReserva (Reserva reser) throws Exception {
        int id_habitacion = Integer.parseInt(JOptionPane.showInputDialog(this, "Habitación que asignar:"));
        String fechaEntrada = JOptionPane.showInputDialog(this, "Nueva fecha de entrada:");
        String fechaSalida = JOptionPane.showInputDialog(this, "Nueva fecha de salida:");
        ph.updateReserva(reser, id_habitacion, fechaEntrada, fechaSalida);
        JOptionPane.showMessageDialog(this, "Reserva cambiada correctamente");


    }
    private void eliminarReserva (Reserva reser) throws Exception {

        //Borrar reserva

    }
}
