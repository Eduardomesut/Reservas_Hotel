package gui;

import biz.*;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

    public class UserMenuPanel extends JPanel {
        private HotelGUI frame;
        private ProgramaHotel ph;
        private int userID;
        private JLabel welcomeLabel;


        public UserMenuPanel(HotelGUI frame, ProgramaHotel ph, int userID) throws Exception {
            this.frame = frame;
            this.ph = ph;
            this.userID = userID;
            int puntos = ph.puntosNH(userID);
            double saldo = ph.saldo(userID);

            setLayout(new GridLayout(6, 1, 10, 10));

            String userName = ph.nombre(userID);
            welcomeLabel = new JLabel("Bienvenido al menú " + userName + "                         " +
                    "                  Puntos NH: " + puntos + "pts" + "   Saldo: " + saldo + "€");
            add(welcomeLabel);

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
                    String roomID = JOptionPane.showInputDialog(this, "Ingrese ID de habitación:");
                    makeReservation(roomID);
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

            JButton datosUsuario = new JButton("Datos Usuario");
            datosUsuario.addActionListener(e -> {
                try {
                    dataUser();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            add(datosUsuario);

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
                button.addActionListener(e -> {
                    showRoomsForHotel(hotel.getHotel_id());
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

        private void showRoomsForHotel(int hotel_id) {
            try {
                // Crear y configurar el JFrame para las habitaciones
                JFrame frame = new JFrame("Habitaciones Disponibles en el Hotel");
                frame.setSize(400, 400);
                frame.setLayout(new BorderLayout());

                // Cargar la imagen de fondo
                ImageIcon imageIcon = null;
                // Cargamos la imagen de fondo.
                if (hotel_id == 1){
                    imageIcon = new ImageIcon("./img/hotel.jpg");
                } else if (hotel_id == 2){
                    imageIcon = new ImageIcon("./img/andorra.jpg");
                } else if (hotel_id == 3) {
                    imageIcon = new ImageIcon("./img/dubai.jpg");
                } else if (hotel_id == 4) {
                    imageIcon = new ImageIcon("./img/york.jpg");
                }else {
                    imageIcon = new ImageIcon("./img/hotel.jpg");
                }

                imageIcon = scaleImage(imageIcon, 400,400);
                JLabel label = new JLabel(imageIcon);
                frame.add(label, BorderLayout.CENTER);

                // Crear un panel transparente para los botones de habitaciones
                JPanel textPanel = new JPanel();
                textPanel.setOpaque(false);
                textPanel.setLayout(new GridLayout(0, 1));

                // Obtener y mostrar las habitaciones disponibles
                ArrayList<habitaciones> habs = ph.getHabitaciones(hotel_id);
                for (habitaciones hab : habs) {
                    JButton button = new JButton(hab.getNum_habitacion() + " - " + hab.getTipo());
                    String habitacion = hab.getId_hab() + "";
                    button.addActionListener(e -> {
                        try {
                            makeReservation(habitacion);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    textPanel.add(button);
                }

                frame.add(textPanel, BorderLayout.SOUTH);
                frame.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        private void dataUser () throws Exception{
            // Hacer método y base de datos de datos de los datos de la cuenta como el sueldo de NH
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1)); // Un botón por fila
            JButton dinero = new JButton("Añadir dinero al saldo");
            dinero.addActionListener(e -> {
                Double dineroAdd = Double.parseDouble(JOptionPane.showInputDialog("Dinero a introducir: "));
                try {
                    ph.addSaldo(this.userID, ph.saldo(this.userID) + dineroAdd);
                    updateWelcomeLabel();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            panel.add(dinero);

            JButton puntos = new JButton("Canjear tus puntos NH");
            puntos.addActionListener(e -> {
                //premios a canjear
                //Recorrer con botones toda la lista de premios

                try {
                    listarPremios();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            });
            panel.add(puntos);


            JDialog dialog = new JDialog((Frame) null, "Seleccione una opción", false);
            dialog.getContentPane().add(new JScrollPane(panel));
            dialog.setSize(300, 400);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }

        private void makeReservation(String id_habitacion) throws Exception {
            double precio = 0;

            // Configuración común para las propiedades de los date pickers
            Properties properties = new Properties();
            properties.put("text.today", "Today");
            properties.put("text.month", "Month");
            properties.put("text.year", "Year");

            // Modelo de fecha y panel para check-in
            UtilDateModel modelCheckIn = new UtilDateModel();
            JDatePanelImpl datePanelCheckIn = new JDatePanelImpl(modelCheckIn);
            JDatePickerImpl datePickerCheckIn = new JDatePickerImpl(datePanelCheckIn, new DateLabelFormatter());

            // Modelo de fecha y panel para check-out
            UtilDateModel modelCheckOut = new UtilDateModel();
            JDatePanelImpl datePanelCheckOut = new JDatePanelImpl(modelCheckOut);
            JDatePickerImpl datePickerCheckOut = new JDatePickerImpl(datePanelCheckOut, new DateLabelFormatter());

            // Mostrar los date pickers en un JOptionPane para seleccionar fechas
            JOptionPane.showConfirmDialog(null, datePickerCheckIn, "Fecha de ingreso:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showConfirmDialog(null, datePickerCheckOut, "Fecha de salida:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            Date checkInDate = (Date) datePickerCheckIn.getModel().getValue();
            Date checkOutDate = (Date) datePickerCheckOut.getModel().getValue();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String checkIn = format.format(checkInDate);
            String checkOut = format.format(checkOutDate);
            if (this.ph.habitacionOcupada(Integer.parseInt(id_habitacion), checkIn, checkOut)){
                JOptionPane.showMessageDialog(this, "Lo sentimos, no hay habitación para estas fechas...");
            }else {
                precio = ph.getPrecioByHabyFecha(Integer.parseInt(id_habitacion), checkIn, checkOut);
                JOptionPane pago = new JOptionPane();
                int respuesta = pago.showConfirmDialog(this, "Precio: " + precio + " Euros", "Confirmación de reserva", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    try {
                        if (ph.saldo(this.userID) - precio >= 0){
                            Reserva newReservation = new Reserva(userID, Integer.parseInt(id_habitacion), checkIn, checkOut);
                            ph.addReserva(newReservation);
                            JOptionPane.showMessageDialog(this, "Reserva realizada con éxito!");
                            ph.addPuntosNH(this.userID, (int)(ph.puntosNH(this.userID) + precio * 10));
                            ph.addSaldo(this.userID, ph.saldo(this.userID) - precio);
                            JOptionPane.showMessageDialog(this, "Enhorabuena como cliente te llevas " + ((int)precio * 10) + " puntos NH!");
                            //Meter 10 puntos NH por cada euros gastado en la reserva
                            updateWelcomeLabel();
                        }else{
                            JOptionPane.showMessageDialog(this, "Reserva cancelada, no dispone de saldo suficiente");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error al hacer la reserva: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (respuesta == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "Reserva cancelada");
                } else {
                    JOptionPane.showMessageDialog(this, "Saliendo");
                }
            }
        }

        private void showReservations() throws Exception {
            // Implementación para mostrar reservas
            String message = "Tus reservas:\n" + ph.getReservas(userID);
            JOptionPane.showMessageDialog(this, message);
        }
        private void listarPremios () throws Exception{
            ArrayList<premios> al = new ArrayList<>();
            al = this.ph.listadoPremios();
            ImageIcon imageIcon = null;

// Cargamos la imagen de fondo.
            JLabel label = new JLabel(imageIcon);
            frame.add(label, BorderLayout.CENTER);
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1)); // Un botón por fila
            String message = "Premios:\n";
            for (premios premio : al) {
                JButton button = new JButton(premio.getNombre() + " - " + premio.getCoste() + " puntos");
                button.addActionListener(e -> {
                    ImageIcon foto = null;
                    // Cargar la imagen del premio
                    if (premio.getPremioId() == 1){
                        foto = new ImageIcon("./img/tele.jpg");
                    } else if (premio.getPremioId() == 2) {
                        foto = new ImageIcon("./img/teclado.jpg");
                    } else if (premio.getPremioId() == 3) {
                        foto = new ImageIcon("./img/bici.jpg");
                    } else if (premio.getPremioId() == 4) {
                        foto = new ImageIcon("./img/smarthphone.jpg");
                    } else if (premio.getPremioId() == 5) {
                        foto = new ImageIcon("./img/angeles.jpg");

                    }else {
                        foto = new ImageIcon("./img/premio.jpg");
                    }
                    foto = scaleImage(foto,50,50);

                    JLabel fotoLabel = new JLabel(foto);
                    JLabel descripcionLabel = new JLabel("<html><p>" + premio.getDescripcion() + "</p></html>");

                    // Crear el panel de confirmación
                    JPanel confirmacionPanel = new JPanel();
                    confirmacionPanel.setLayout(new BorderLayout());
                    confirmacionPanel.add(fotoLabel, BorderLayout.NORTH);
                    confirmacionPanel.add(descripcionLabel, BorderLayout.CENTER);

                    int respuesta = JOptionPane.showConfirmDialog(this, confirmacionPanel, "Confirmación de canjeo", JOptionPane.YES_NO_OPTION);

                    if (respuesta == JOptionPane.YES_OPTION) {
                        try {
                            if (this.ph.puntosNH(this.userID) - premio.getCoste() >= 0) {
                                this.ph.addPuntosNH(this.userID, this.ph.puntosNH(this.userID) - premio.getCoste());
                                updateWelcomeLabel();
                            } else {
                                JOptionPane.showMessageDialog(this, "No tienes puntos suficientes");
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    } else if (respuesta == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(this, "Premio no recibido");
                    } else {
                        JOptionPane.showMessageDialog(this, "Saliendo");
                    }
                });
                panel.add(button);
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            JOptionPane.showMessageDialog(null, scrollPane, "Selecciona un premio a canjear", JOptionPane.PLAIN_MESSAGE);

        }

        public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
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
        private void updateWelcomeLabel() throws Exception {
            int puntos = ph.puntosNH(userID);
            double saldo = ph.saldo(userID);
            String userName = ph.nombre(userID);
            welcomeLabel.setText("Bienvenido al menú " + userName + "                         " +
                    "                  Puntos NH: " + puntos + "pts" + "   Saldo: " + saldo + "€");
        }
        public ImageIcon scaleImage(ImageIcon icon, int w, int h)
        {
            int nw = icon.getIconWidth();
            int nh = icon.getIconHeight();

            if(icon.getIconWidth() > w)
            {
                nw = w;
                nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
            }

            if(nh > h)
            {
                nh = h;
                nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
            }

            return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
        }

    }
