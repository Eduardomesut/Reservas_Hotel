package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelApp extends JFrame {
    public HotelApp() {
        setTitle("Hotel Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana
        initUI();
    }

    private void initUI() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

        JButton userButton = new JButton("Inicio de sesión / Registro");
        userButton.setPreferredSize(new Dimension(200, 75));

                userButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(HotelApp.this);
                        loginDlg.setVisible(true);
                        // Puedes usar loginDlg.isSucceeded() para verificar si el usuario ingresó correctamente su ID
                        if (loginDlg.isSucceeded()) {
                            JOptionPane.showMessageDialog(HotelApp.this,
                                    "Inicio de sesión con exito! Bienvenido " + loginDlg.getUserId());
                        } else {
                            JOptionPane.showMessageDialog(HotelApp.this,
                                    "Inicio de sesión fallido!");
                        }
                    }
                });

        JButton adminButton = new JButton("Acceso modo administrador");
        adminButton.setPreferredSize(new Dimension(200, 75));

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HotelApp.this, "Clickado en modo administración");
            }
        });

        add(userButton);
        add(adminButton);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new HotelApp().setVisible(true);
        });
    }
}