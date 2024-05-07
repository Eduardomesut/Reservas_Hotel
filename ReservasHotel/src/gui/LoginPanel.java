package gui;

import biz.ProgramaHotel;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;

    public LoginPanel(HotelGUI frame, ProgramaHotel ph) {
        this.frame = frame;
        this.ph = ph;
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("ID de NH:"));
        JTextField idField = new JTextField();
        add(idField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> {
            try {
                loginUser(Integer.parseInt(idField.getText()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(loginButton);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }

    private void loginUser(int id) throws Exception {
        if (ph.getIDCorrecto(id)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión correcto. Bienvenido " + ph.nombre(id));
            frame.switchToUserMenu(id);
        } else {
            JOptionPane.showMessageDialog(this, "ID incorrecto, intente nuevamente.");
        }
    }
}
