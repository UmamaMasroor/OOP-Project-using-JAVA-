import javax.swing.*;
import java.awt.*;

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
