import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLoginApp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton loginButton;
    private JComboBox<String> userComboBox;
    private Connection connection;
    private Statement statement;
    private Statement adminStatement;
    private Statement adminConnection;

    public AdminLoginApp() {
        setTitle("Admin Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
//        JLabel passwordLabel = new JLabel("Password: ");
//        passwordField = new JPasswordField();
//        JLabel emailLabel = new JLabel("Email: ");
//        emailField = new JTextField();
        userComboBox = new JComboBox<>();
        loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
//        panel.add(passwordLabel);
//        panel.add(passwordField);
//        panel.add(emailLabel);
//        panel.add(emailField);
        panel.add(new JLabel());
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username =usernameField .getText();
                String selectedUser = (String) userComboBox.getSelectedItem();
                if (selectedUser.equals("Admin")) {
                    createAdminTask(username);
                } else {
                    createUserTask(username, selectedUser);
                }
            }
        });

        add(panel);
        setVisible(true);


    }




    private boolean validateAdmin() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            statement = connection.createStatement();
            String query = "SELECT * FROM general" ;
            ResultSet resultSet = statement.executeQuery(query);
            userComboBox.addItem("Admin");
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                userComboBox.addItem(username);
            }
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            statement=connection.createStatement();
            String query ="SELECT * FROM general";
            ResultSet resultSet =statement.executeQuery(query);
            while (resultSet.next()){
                String username= resultSet.getString("username");
                userComboBox.addItem(username);
            }
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAdminTask(String task) {
        try {
            Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
            Statement = Connection.createStatement();
            String query = "INSERT INTO tasks (task, assigned_to) VALUES ('" + task + "', 'Admin')";
            int rowsAffected = Statement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(AdminLoginApp.this, "Task created and assigned to Admin successfully!");
            } else {
                JOptionPane.showMessageDialog(AdminLoginApp.this, "Failed to create task or assign to Admin!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Statement != null)
                    Statement.close();
                if (Connection != null)
                    Connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void createUserTask(String task, String username) {
        try {
            Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
            Statement = Connection.createStatement();
            String query = "INSERT INTO tasks (task, assigned_to) VALUES ('" + task + "', '" + username + "')";
            int rowsAffected = Statement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(AdminLoginApp.this, "Task created and assigned to " + username + " successfully!");
            } else {
                JOptionPane.showMessageDialog(AdminLoginApp.this, "Failed to create task or assign to " + username + "!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Statement != null)
                    Statement.close();
                if (Connection != null)
                    Connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }}
