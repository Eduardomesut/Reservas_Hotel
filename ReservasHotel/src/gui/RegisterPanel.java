package gui;

import biz.ProgramaHotel;
import biz.clientes;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;

    public RegisterPanel(HotelGUI frame, ProgramaHotel ph) {
        this.frame = frame;
        this.ph = ph;
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Nombre y Apellidos:"));
        JTextField nameField = new JTextField();
        add(nameField);

        add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        JTextField dobField = new JTextField();
        add(dobField);

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(e -> registerUser(nameField.getText(), dobField.getText()));
        add(registerButton);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }

    private void registerUser(String name, String dob) {
        try {
            clientes newClient = new clientes(name, dob);
            ph.addCliente(newClient);
            int userId = ph.darIDNHUsuario(name, dob);
            if (userId != -1) {
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
