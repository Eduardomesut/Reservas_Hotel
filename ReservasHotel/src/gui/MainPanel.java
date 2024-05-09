package gui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private HotelGUI frame;

    public MainPanel(HotelGUI frame) {
        this.frame = frame;
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnRegister = new JButton("Registrarse");
        btnRegister.addActionListener(e -> frame.showCard("Register"));
        add(btnRegister);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> frame.showCard("Login"));
        add(btnLogin);

        JButton btnAdmin = new JButton("Modo Administrador");
        btnAdmin.addActionListener(e -> frame.showCard("Login"));
        add(btnAdmin);

        JButton btnExit = new JButton("Salir");
        btnExit.addActionListener(e -> System.exit(0));
        add(btnExit);
    }
}
