package gui;

import biz.ProgramaHotel;
import biz.clientes;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class RegisterPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;

    public RegisterPanel(HotelGUI frame, ProgramaHotel ph) {
        this.frame = frame;
        this.ph = ph;
        setLayout(new GridLayout(6, 2, 10, 10));

        // Nombre y apellidos
        add(new JLabel("Nombre y Apellidos:"));
        JTextField nameField = new JTextField();
        add(nameField);

        // Fecha de nacimiento con JDatePicker
        add(new JLabel("Fecha de Nacimiento:"));
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new UserMenuPanel.DateLabelFormatter());
        add(datePicker);
        //Contraseña
        add(new JLabel("Introduce una contraseña: "));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(e -> {
            Date selectedDate = (Date) datePicker.getModel().getValue();
            // Formatear la fecha como se requiera, por ejemplo, a YYYY-MM-DD
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dob = dateFormat.format(selectedDate);
            char[] passwordArray = passwordField.getPassword();
            String passwordString = new String(passwordArray);
            registerUser(nameField.getText(), dob,passwordString);
        });
        add(registerButton);

        // Botón de volver
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }

    private void registerUser(String name, String dob, String textPass) {
        try {
            clientes newClient = new clientes(name, dob);
            ph.addCliente(newClient);
            int userId = ph.darIDNHUsuario(name, dob);
            if (userId != -1) {
                ph.addPassword(userId, textPass);
                JOptionPane.showMessageDialog(this, "Registrado con éxito. Su ID de usuario es: " + userId + ". Bienvenido " + name);
                frame.switchToUserMenu(userId);
            } else {
                JOptionPane.showMessageDialog(this, "Error en la generación de su ID, vuelva a intentarlo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
