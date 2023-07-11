

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class homepages extends JFrame implements ActionListener {
    JButton b1, b2;
    JPanel newPanel;
    JLabel userLabel, passLabel,emaillabel;
    JTextField textField1, textField2,textField3;

    public homepages() {
        userLabel = new JLabel();
        userLabel.setText("Username");
        userLabel.setBounds(50, 100, 75, 25);
        textField1 = new JTextField(20);

        passLabel = new JLabel();
        passLabel.setText("Password");
        passLabel.setBounds(50, 100, 75, 25);
        textField2 = new JPasswordField(20);

        emaillabel=new JLabel();
        emaillabel.setText("Email");
        emaillabel.setBounds(50,100,75,25);
        textField3=new JTextField(20);

        b1 = new JButton("Create task");
        b1.setBounds(125, 250, 250, 35);
        b1.setFocusable(false);

        b2 = new JButton("Assign to");
        b2.setBounds(125, 250, 250, 35);
        b2.setFocusable(false);

        newPanel = new JPanel();
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(emaillabel);
        newPanel.add(textField3);
        newPanel.add(b1);
        newPanel.add(b2);
        newPanel.setLayout(new FlowLayout());

        add(newPanel, BorderLayout.CENTER);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        b1.addActionListener(this);
        b2.addActionListener(this);
        setTitle("User Form");

        setFont(new Font("Times New Roman", Font.BOLD, 12));
        setVisible(true);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b2) {
            String Username = textField1.getText();
            String Password = textField2.getText();
            String Email=textField3.getText();

            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM credentials WHERE username = '" + Username + "'");
                statement.setString(1, Username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String dbUsername = resultSet.getString("Username");
                    String dbPassword = resultSet.getString("Password");
                    String dbEmail=resultSet.getString("Email");
                    String dbaccess = resultSet.getString("access");

                    if (Username.equals(dbUsername) && Password.equals(dbPassword) &&Email.equals(dbEmail)) {
                        this.dispose();
                        if (dbaccess.equals("admin")) {
                            // Open admin functionality
                            homepage homepage = new homepage();
                            homepage.display();
                        } else {
                            // Open user functionality
                            Admin homepage = new Admin();
                            homepage.display();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Login failed! Incorrect username or password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed! User not found.");
                }

                statement.close();
                resultSet.close();
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        if (e.getSource() == b1) {
            this.dispose();
            homepage log = new homepage();
            log.display();
        }
    }
}

class AdminHomepage {
    public void display() {
        // Implement your admin functionality here
        System.out.println("Welcome to the Admin Homepage");
    }}
        // Perform admin