package gui;

import biz.*;
import javax.swing.*;
import biz.ProgramaHotel;
import biz.Reserva;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

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

        JButton registrarHotel = new JButton("Registrar nuevo hotel");
        registrarHotel.addActionListener(e -> {
            try {
                registrarHotel();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(registrarHotel);

        JButton addHabitacion = new JButton("Añadir nueva habitación");
        addHabitacion.addActionListener(e -> {
            try {
                meterHabitacion();

            }catch (Exception ex){
                throw new RuntimeException(ex);
            }

        });
        add(addHabitacion);

        JButton backButton = new JButton("Salir");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }
    private void listarClientes() throws Exception {
        ArrayList<clientes>al = new ArrayList<>();
        String nombre = JOptionPane.showInputDialog(this, "Nombre a buscar:");
        String nacimiento = JOptionPane.showInputDialog(this, "Año de nacimiento:");

        al = ph.getClientes(nombre, nacimiento);
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
    private void registrarHotel () throws Exception{
        String nombre = JOptionPane.showInputDialog(this, "Nombre del hotel:");
        String ubicacion = JOptionPane.showInputDialog(this, "Ubicación del hotel:");
        this.ph.registrarHotel(nombre, ubicacion);
        JOptionPane.showMessageDialog(this, "Hotel " + nombre + " registrado correctamente!");



        //Hacer opcion para mirar en cada hotel lod detalles de las habitaciones y poder eliminar hoteles y acceder a sus
        // habitaciones y editar y eliminar estas




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

    private void editarReserva(Reserva reser) throws Exception {
        int id_habitacion = Integer.parseInt(JOptionPane.showInputDialog(this, "Habitación que asignar:"));

        // Modelo de fecha
        UtilDateModel modelEntrada = new UtilDateModel();
        UtilDateModel modelSalida = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // Panel de fecha
        JDatePanelImpl datePanelEntrada = new JDatePanelImpl(modelEntrada);
        JDatePanelImpl datePanelSalida = new JDatePanelImpl(modelSalida);

        // DatePicker
        JDatePickerImpl datePickerEntrada = new JDatePickerImpl(datePanelEntrada, new DateLabelFormatter());
        JDatePickerImpl datePickerSalida = new JDatePickerImpl(datePanelSalida, new DateLabelFormatter());

        // Mostrar los pickers en un JOptionPane
        int result = JOptionPane.showConfirmDialog(null, datePickerEntrada, "Nueva fecha de entrada:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Date fechaEntrada = (Date) datePickerEntrada.getModel().getValue();
            result = JOptionPane.showConfirmDialog(null, datePickerSalida, "Nueva fecha de salida:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                Date fechaSalida = (Date) datePickerSalida.getModel().getValue();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String strFechaEntrada = format.format(fechaEntrada);
                String strFechaSalida = format.format(fechaSalida);

                ph.updateReserva(reser, id_habitacion, strFechaEntrada, strFechaSalida);
                JOptionPane.showMessageDialog(this, "Reserva cambiada correctamente");
            }
        }
    }
    private void meterHabitacion ()throws Exception{
        ArrayList<hoteles> hotels = ph.getHoteles();

        // Crear un panel para los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Un botón por fila

        // Crear un botón para cada hotel y agregarlo al panel
        for (hoteles hotel : hotels) {
            JButton button = new JButton(hotel.getNombre() + " - " + hotel.getUbicacion());
            button.addActionListener(e -> {
                String numero = JOptionPane.showInputDialog(this, "Número de habitación:");
                String tipo = JOptionPane.showInputDialog(this, "Tipo de la habitación:");
                double precio_alto = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio en la temporada alta"));
                double precio_medio = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio en la temporada media"));
                double precio_bajo = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio en la temporada baja"));
                try {
                    this.ph.meterHabitacion(hotel.getHotel_id(), numero, tipo, precio_alto, precio_medio,precio_bajo);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "Habitación " + numero + " añadida correctamente!");
            });
            panel.add(button);
        }

        // Crear y mostrar el diálogo con los botones
        JDialog dialog = new JDialog((Frame) null, "Seleccione un hotel", false);
        dialog.getContentPane().add(new JScrollPane(panel));
        dialog.setSize(300, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
    // Clase para formatear la fecha
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
    private void eliminarReserva (Reserva reser) throws Exception {
        ph.deleteReserva(reser);
        JOptionPane.showMessageDialog(this, "Reserva eliminada correctamente");
    }
}
