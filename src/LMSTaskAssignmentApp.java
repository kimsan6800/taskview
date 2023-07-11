import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LMSTaskAssignmentApp extends JFrame {
    private JTextField taskField;
    private JComboBox<String> userComboBox;
    private JButton createTaskButton;

    private Connection adminConnection;
    private Connection userConnection;
    private Statement adminStatement;
    private Statement userStatement;

    public LMSTaskAssignmentApp() {
        setTitle("LMS Task Assignment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel taskLabel = new JLabel("Task: ");
        taskField = new JTextField();
        JLabel userLabel = new JLabel("User: ");
        userComboBox = new JComboBox<>();
        createTaskButton = new JButton("Create Task");

        panel.add(taskLabel);
        panel.add(taskField);
        panel.add(userLabel);
        panel.add(userComboBox);
        panel.add(new JLabel());
        panel.add(createTaskButton);

        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                String selectedUser = (String) userComboBox.getSelectedItem();
                if (selectedUser.equals("Admin")) {
                    createAdminTask(task);
                } else {
                    createUserTask(task, selectedUser);
                }
            }
        });

        add(panel);
        setVisible(true);

        initializeUserComboBox();
    }

    private void initializeUserComboBox() {
        try {
            adminConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
            adminStatement = adminConnection.createStatement();
            String adminQuery = "SELECT username FROM users";
            ResultSet adminResultSet = adminStatement.executeQuery(adminQuery);
            userComboBox.addItem("Admin");
            while (adminResultSet.next()) {
                String username = adminResultSet.getString("username");
                userComboBox.addItem(username);
            }

            userConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
            userStatement = userConnection.createStatement();
            String userQuery = "SELECT username FROM users";
            ResultSet userResultSet = userStatement.executeQuery(userQuery);
            while (userResultSet.next()) {
                String username = userResultSet.getString("username");
                userComboBox.addItem(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (adminStatement != null)
                    adminStatement.close();
                if (userStatement != null)
                    userStatement.close();
                if (adminConnection != null)
                    adminConnection.close();
                if (userConnection != null)
                    userConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAdminTask(String task) {
        try {
            adminConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
            adminStatement = adminConnection.createStatement();
            String query = "INSERT INTO tasks (task, assigned_to) VALUES ('" + task + "', 'Admin')";
            int rowsAffected = adminStatement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(LMSTaskAssignmentApp.this, "Task created and assigned to Admin successfully!");
            } else {
                JOptionPane.showMessageDialog(LMSTaskAssignmentApp.this, "Failed to create task or assign to Admin!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (adminStatement != null)
                    adminStatement.close();
                if (adminConnection != null)
                    adminConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createUserTask(String task, String username) {
        try {
            userConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password");
            userStatement = userConnection.createStatement();
            String query = "INSERT INTO tasks (task, assigned_to) VALUES ('" + task + "', '" + username + "')";
            int rowsAffected = userStatement.executeUpdate(query);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(LMSTaskAssignmentApp.this, "Task created and assigned to " + username + " successfully!");
            } else {
                JOptionPane.showMessageDialog(LMSTaskAssignmentApp.this, "Failed to create task or assign to " + username + "!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (userStatement != null)
                    userStatement.close();
                if (userConnection != null)
                    userConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

