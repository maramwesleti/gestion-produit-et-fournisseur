package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class WelcomePage extends JFrame implements ActionListener {
    private JButton btnLogin;
    private JButton btnCreateAccount;
    private Image backgroundImage;

    public WelcomePage() {
        setTitle("Welcome");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        backgroundImage = loadImage("/login.jpg");
        btnLogin = new JButton("Login");
        btnCreateAccount = new JButton("Create Account");
        btnLogin.addActionListener(this);
        btnCreateAccount.addActionListener(this);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setOpaque(false); 
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnCreateAccount);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);
        setVisible(true);
    }

    
    private Image loadImage(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            new Login();
            dispose(); 
        } else if (e.getSource() == btnCreateAccount) {
            new CreateAccount();
            dispose(); 
        }
    }

    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}
