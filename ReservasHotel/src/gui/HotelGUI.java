package gui;

import biz.ProgramaHotel;

import javax.swing.*;
import java.awt.*;

public class HotelGUI extends JFrame {
    private ProgramaHotel ph;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public HotelGUI() {
        setTitle("Sistema de Gestión de Hoteles NH");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ph = new ProgramaHotel(); // Suponiendo que esta clase ya está definida con sus métodos.
        initUI();
    }

    private void initUI() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JPanel mainPanel = new MainPanel(this);
        JPanel registerPanel = new RegisterPanel(this, ph);
        JPanel loginPanel = new LoginPanel(this, ph);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(registerPanel, "Register");
        cardPanel.add(loginPanel, "Login");

        add(cardPanel, BorderLayout.CENTER);
    }

    public void showCard(String card) {
        cardLayout.show(cardPanel, card);
    }

    public void successfulLogin(int userID) throws Exception {
        UserMenuPanel userMenuPanel = new UserMenuPanel(this, ph, userID);
        cardPanel.add(userMenuPanel, "UserMenu");
        cardLayout.show(cardPanel, "UserMenu");
    }

    public void switchToAdminMenu() throws Exception {
        String adminMenuKey = "Admin";
        // Remueve el panel si ya existe
        cardPanel.remove(cardPanel.getComponentCount() - 1);
        AdminMenuPanel adminMenuPanel = new AdminMenuPanel(this, ph);
        cardPanel.add(adminMenuPanel, adminMenuKey);
        cardLayout.show(cardPanel, adminMenuKey);
    }



    public void switchToUserMenu(int userID) throws Exception {
        String userMenuKey = "UserMenu";
        // Remueve el panel si ya existe
        cardPanel.remove(cardPanel.getComponentCount() - 1);
        UserMenuPanel userMenuPanel = new UserMenuPanel(this, ph, userID);
        cardPanel.add(userMenuPanel, userMenuKey);
        cardLayout.show(cardPanel, userMenuKey);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            HotelGUI frame = new HotelGUI();
            frame.setVisible(true);
        });
    }
}
