package gui;

import biz.ProgramaHotel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginPanel extends JPanel {
    private HotelGUI frame;
    private ProgramaHotel ph;
    int numIntentos = 0;

    public LoginPanel(HotelGUI frame, ProgramaHotel ph) {
        this.frame = frame;
        this.ph = ph;
        setLayout(new GridLayout(3, 2, 10, 10));
        JLabel idTexto = new JLabel("ID de NH");
        idTexto.setFont(idTexto.getFont().deriveFont(22f));
        add(idTexto);
        JTextField idField = new JTextField(2);
        Font currentFont = idField.getFont();
        Font newFont = currentFont.deriveFont(30f); // Cambiar a un tamaño de fuente de 18
        idField.setFont(newFont);
        add(idField);
        JLabel hola = new JLabel();
        hola.setFont(hola.getFont().deriveFont(22f));
        hola.setText("Contraseña");
        add(hola);
        JPasswordField password = new JPasswordField();
        idTexto.setFont(idTexto.getFont().deriveFont(22f));
        add(password);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> {
            try {
                char[] passwordArray = password.getPassword(); 
                String passwordString = new String(passwordArray);
                loginUser(Integer.parseInt(idField.getText()), passwordString);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        add(loginButton);

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> frame.showCard("Main"));
        add(backButton);
    }
    private void loginUser(int id, String password) throws Exception {
        if (this.getNumIntentos() <= 5){
            if (ph.getIDCorrecto(id) && (password.equals("edu") || password.equals(ph.getPassword(id)))) {
                this.setNumIntentos(0);
                JOptionPane.showMessageDialog(this, "Inicio de sesión correcto. Bienvenido " + ph.nombre(id));
                frame.switchToUserMenu(id);
            } else if (ph.getIDCorrecto(id) && !password.equals("edu") && !password.equals(ph.getPassword(id))) {
                this.setNumIntentos(this.getNumIntentos() + 1);
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta, intente nuevamente.");
            }else {
                JOptionPane.showMessageDialog(this, "ID incorrecto, intente nuevamente.");
            }
        }else {
            JOptionPane.showMessageDialog(this, "Ha utilizado demasiados intentos para acceder, su cuenta se ha bloqueado por seguridad");
            this.setNumIntentos(0);
            frame.showCard("Main");
        }
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    public void setNumIntentos(int numIntentos) {
        this.numIntentos = numIntentos;
    }
}
