package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private JTextField userIdField;
    private JButton loginButton;
    private boolean succeeded;

    public LoginDialog(Frame parent) {
        super(parent, "Inicio Sesión", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("ID Usuario NH: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(label, cs);

        userIdField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(userIdField, cs);

        loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes poner la lógica para verificar el ID del usuario
                JOptionPane.showMessageDialog(LoginDialog.this,
                        "Has entrado con: " + getUserId(),
                        "Iniciar Sesión",
                        JOptionPane.INFORMATION_MESSAGE);
                succeeded = true;
                dispose(); // Cierra el diálogo
            }
        });
        JPanel bp = new JPanel();
        bp.add(loginButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUserId() {
        return userIdField.getText().trim();
    }

    public boolean isSucceeded() {
        return succeeded;
    }
}
