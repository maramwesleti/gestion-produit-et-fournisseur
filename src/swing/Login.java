package swing;

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnCreateAccount;
    private UserDAO userDAO;

    public Login() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the UserDAO
        userDAO = new UserDAO();

        // Create UI components
        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");
        tfUsername = new JTextField(20);
        pfPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");
        btnCreateAccount = new JButton("Create Account");

        // Add action listeners to buttons
        btnLogin.addActionListener(this);
        btnCreateAccount.addActionListener(this);

        // Layout the components in a panel
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(lblUsername);
        panel.add(tfUsername);
        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(btnLogin);
        panel.add(btnCreateAccount);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            // Authenticate user
            String username = tfUsername.getText();
            String password = new String(pfPassword.getPassword());
            if (userDAO.getUser(username, password) != null) {
                new Choix();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnCreateAccount) {
            new CreateAccount();
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
