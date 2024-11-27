import javax.swing.*;

// Login Screen
class LoginScreen {
    JFrame frame;

    public LoginScreen() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        frame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        frame.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        frame.add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(260, 150, 100, 30);
        frame.add(signupButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = FileManager.getUser(username);

            if (user != null && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + user.name);
                frame.dispose();
                new DashboardScreen(user);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        signupButton.addActionListener(e -> {
            frame.dispose();
            new SignupScreen();
        });

        frame.setVisible(true);
    }
}

