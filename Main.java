import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Base class: User
class User {
    protected String username, name, password, securityQuestion, securityAnswer;

    public User() {}

    public User(String username, String name, String password, String securityQuestion, String securityAnswer) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    // Encapsulation: Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

// Utility class for file handling
class FileManager {
    private static final String FILE_NAME = "users.txt";

    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.username + "," + user.name + "," + user.password + "," +
                    user.securityQuestion + "," + user.securityAnswer);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving user data: " + e.getMessage());
        }
    }

    public static User getUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data: " + e.getMessage());
        }
        return null;
    }
}

// Main Application
class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}

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

// Signup Screen
class SignupScreen {
    JFrame frame;

    public SignupScreen() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        frame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);
        frame.add(usernameField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 100, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 200, 30);
        frame.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 100, 30);
        frame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 150, 200, 30);
        frame.add(passwordField);

        JLabel securityQuestionLabel = new JLabel("Security Question:");
        securityQuestionLabel.setBounds(50, 200, 150, 30);
        frame.add(securityQuestionLabel);

        JTextField securityQuestionField = new JTextField();
        securityQuestionField.setBounds(150, 200, 200, 30);
        frame.add(securityQuestionField);

        JLabel securityAnswerLabel = new JLabel("Answer:");
        securityAnswerLabel.setBounds(50, 250, 100, 30);
        frame.add(securityAnswerLabel);

        JTextField securityAnswerField = new JTextField();
        securityAnswerField.setBounds(150, 250, 200, 30);
        frame.add(securityAnswerField);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(150, 300, 100, 30);
        frame.add(signupButton);

        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String securityQuestion = securityQuestionField.getText();
            String securityAnswer = securityAnswerField.getText();

            User user = new User(username, name, password, securityQuestion, securityAnswer);
            FileManager.saveUser(user);
            JOptionPane.showMessageDialog(frame, "Signup successful!");
            frame.dispose();
            new LoginScreen();
        });

        frame.setVisible(true);
    }
}

// Dashboard Screen
class DashboardScreen {
    JFrame frame;

    public DashboardScreen(User user) {
        frame = new JFrame("Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + user.name + "!");
        frame.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        frame.add(logoutButton);

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginScreen();
        });

        frame.setVisible(true);
    }
}
