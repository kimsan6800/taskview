import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame implements ActionListener {

    JButton b1;
    JPanel newPanel;
    JLabel userLabel,passLabel;
    JTextField textField1,textField2;

    login(){
        userLabel = new JLabel();
        userLabel.setText("Username");

        textField1 = new JTextField(30);

        passLabel =new JLabel();
        passLabel.setText("Password");

        textField2 = new JPasswordField(30);

        b1 = new JButton("SUBMIT");

        newPanel = new JPanel();
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);


        add(newPanel, BorderLayout.CENTER);

        b1.addActionListener(this);
        setTitle("Login Form");

        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String Username = textField1.getText();
        String Password = passLabel.getText();
        String Email=textField2.getText();


        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root","");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tasks where Username = '"+Username+"'");
            while(resultSet.next()){
                var dbusername = resultSet.getString("Username");
                var dbemail=resultSet.getString("Email");
                var dbpass = resultSet.getString("Password");
                if (Username.equals(dbusername)&& Email.equals(dbemail)&& Password.equals(dbpass)){
//                this.dispose();
                    Test tst= new Test();
                homepage home = new homepage();
                win ent=new win();
                    JOptionPane.showMessageDialog(this,"Login Successful!");

                }else{
                    JOptionPane.showMessageDialog(this,"login failed!");

                }

            }


        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
