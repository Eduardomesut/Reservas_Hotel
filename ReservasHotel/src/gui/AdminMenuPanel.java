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
        StringBuilder message = new StringBuilder("Reservas:\n" + ph.getReservasnombre(nombre,fechaNac));
        JOptionPane.showMessageDialog(this, message);
    }
}
