import javax.swing.*;
import java.awt.*;

// Signup Screen
class SignupScreen {
    JFrame frame;

    public SignupScreen() {
        frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.PINK);
//        ImageIcon backgroundImage = new ImageIcon("D:/OOP-Project-using-JAVA/images/src/signup.jpg");
//        JLabel backgroundLabel = new JLabel(backgroundImage);
//        backgroundLabel.setBounds(0, 0, 400, 400);

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

