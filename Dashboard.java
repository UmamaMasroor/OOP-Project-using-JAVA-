import javax.swing.*;
import java.awt.*;

// Dashboard Screen
class DashboardScreen {
    JFrame frame;
    JPanel contentPanel;


    public DashboardScreen(User user) {
        // Create main frame
        frame = new JFrame("Dashboard - Tourism Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + user.name + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(welcomeLabel, BorderLayout.NORTH);

        // Navigation panel (left side menu)
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(10, 1, 5, 5));
        navigationPanel.setPreferredSize(new Dimension(200, 0));
        navigationPanel.setBackground(Color.LIGHT_GRAY);

        // Add menu buttons to navigation panel
        String[] menuItems = {"Add Personal Details", "Update Personal Details", "View Details",
                "Delete Personal Details", "Check Package", "Book Package",
                "Payment", "About", "Logout"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.addActionListener(e -> handleNavigation(item)); // Add event handler
            navigationPanel.add(button);
        }
        frame.add(navigationPanel, BorderLayout.WEST);

        // Content panel (right side)
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(createWelcomePanel(), "Welcome"); // Default welcome panel
        frame.add(contentPanel, BorderLayout.CENTER);


        frame.setVisible(true);
    }

    // Create welcome panel
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome to the Tourism Management System!", JLabel.CENTER);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // Handle navigation
    private void handleNavigation(String action) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();

        if (action.equals("Logout")) {
            frame.dispose();
            new LoginScreen();
            return;
        }
// Check if the panel with the given action already exists
        boolean panelExists = false;

        for (Component component : contentPanel.getComponents()) {
            if (component.getName() != null && component.getName().equals(action)) {
                panelExists = true;
                break;
            }
        }

        if (!panelExists) {
            JPanel newPanel = createFormPanel(action);
            newPanel.setName(action); // Assign a name to the panel
            contentPanel.add(newPanel, action); // Add the new panel dynamically
        }


        cl.show(contentPanel, action); // Show selected panel
    }

    // Create form panels for each section dynamically
    private JPanel createFormPanel(String formName) {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(formName, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        // Example fields for demonstration
        JLabel label1 = new JLabel("Field 1:");
        JTextField textField1 = new JTextField();
        JLabel label2 = new JLabel("Field 2:");
        JTextField textField2 = new JTextField();
        JButton submitButton = new JButton("Submit");

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(new JLabel()); // Spacer
        panel.add(submitButton);

        return panel;
    }
}
