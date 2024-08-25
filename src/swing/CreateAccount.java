package swing;

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount extends JFrame implements ActionListener {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnCreate;
    private JButton btnCancel;
    private UserDAO userDAO;

    public CreateAccount() {
        setTitle("Create Account");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the UserDAO
        userDAO = new UserDAO();

        // Create UI components
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        tfUsername = new JTextField(20);
        pfPassword = new JPasswordField(20);
        pfConfirmPassword = new JPasswordField(20);
        btnCreate = new JButton("Create");
        btnCancel = new JButton("Cancel");

        // Add action listeners to buttons
        btnCreate.addActionListener(this);
        btnCancel.addActionListener(this);

        // Layout the components in a panel
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(lblUsername);
        panel.add(tfUsername);
        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(lblConfirmPassword);
        panel.add(pfConfirmPassword);
        panel.add(btnCreate);
        panel.add(btnCancel);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreate) {
            // Create new user
            String username = tfUsername.getText();
            String password = new String(pfPassword.getPassword());
            String confirmPassword = new String(pfConfirmPassword.getPassword());
            if (password.equals(confirmPassword)) {
                if (userDAO.createUser(username, password)) {
                    JOptionPane.showMessageDialog(this, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Login();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to create account", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCancel) {
            new Login();
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateAccount());
    }
}
