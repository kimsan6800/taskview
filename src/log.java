
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.jar.Attributes;

public class log extends JFrame implements ActionListener {
    JButton b1, b2;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    JTextField textField1, textField2;

    log() {

        userLabel = new JLabel();
        userLabel.setText("Name");
        userLabel.setBounds(50, 100, 75, 25);
        textField1 = new JTextField(20);


        SpringLayout layout = new SpringLayout();

        passLabel = new JLabel();
        passLabel.setText("Password");
        passLabel.setBounds(50, 100, 75, 25);
        textField2 = new JPasswordField(20);

        b1 = new JButton("Create Account");
        b1.setBounds(125, 250, 250, 35);
        b1.setFocusable(false);

        b2 = new JButton("Login");
        b2.setBounds(125, 250, 250, 35);
        b2.setFocusable(false);

        newPanel = new JPanel();
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        newPanel.add(b2);
        newPanel.setLayout(new FlowLayout());

//        newPanel.setLayout(new SpringLayout());
        SpringLayout springlayout = new SpringLayout();
//        getContentPane().setBackground(Color.CYAN);
        add(newPanel, BorderLayout.CENTER);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        b1.addActionListener(this);
        b2.addActionListener(this);
        setTitle("Login Form");


        setFont(new Font("Times New Roman", Font.BOLD, 12));
        setVisible(true);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b2) {
            String Name = textField1.getText();
            String Password = textField2.getText();

//        print the Name and Password in raws
            System.out.println("Name" + Name);
            System.out.println("Password" + Password);


            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from credentials where Username = '" + Name + "'");
                while (resultSet.next()) {
                    var dbusername = resultSet.getString("Username");
                    var dbpass = resultSet.getString("Password");
                    if (Name.equals(dbusername) && Password.equals(dbpass)) {
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Login Successful!");
                    } else {
                        JOptionPane.showMessageDialog(this, "login failed!");

                    }

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }

        if (e.getSource() == b1) {
            this.dispose();
             homepage log=new homepage();
        }

    }
}

