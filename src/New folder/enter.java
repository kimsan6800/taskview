import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class enter extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel,emailLabel,passLabel;
    JTextField textField1, textField2, textField3;

    enter(){

        userLabel = new JLabel();
        userLabel.setText("Username");

        textField1 = new JTextField(20);

        emailLabel =new JLabel();
        emailLabel.setText("Email");

        textField2= new JTextField(20);

        passLabel =new JLabel();
        passLabel.setText("Password");


        textField3= new JPasswordField(20);

        b1 = new JButton("LOGIN");

        newPanel = new JPanel();
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(emailLabel);
        newPanel.add(textField2);
        newPanel.add(passLabel);
        newPanel.add(textField3);
        newPanel.add(b1);
        getContentPane().setBackground(Color.red);


        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        setTitle("Login Form");


        setFont(new Font("Times New Roman",Font.BOLD,11));
        setVisible(true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override

    public void actionPerformed(ActionEvent e) {
        String Username=textField1.getText();
        String Email=textField2.getText();
        String Password=textField3.getText();





        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root","");
            Statement statement =connection.createStatement();
              statement.executeUpdate("insert into credentials(Username,Email,Password) values("+"'"+Username+"',"+"'"+Email+"',"+"'"+Password+"')");
            JOptionPane.showMessageDialog(enter.this, "Account Created");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(enter.this, "Error Creating Account");
            System.out.println(ex);
        }



}}

