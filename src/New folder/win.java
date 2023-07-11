import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class win extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel,emailLabel,passLabel;
    JTextField textField1, textField2, textField3;

    win(){

        userLabel = new JLabel();
        userLabel.setText("Name");

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
        String Name=textField1.getText();
        String Email=textField2.getText();
        String Password = textField3.getText();

        Connection connection=null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            Statement statement = connection.createStatement();
//            System.out.println("select * from tasks where Name= "+"'"+Name+"' ");
            ResultSet resultSet= statement.executeQuery("select * from tasks where Username= "+"'"+Name+"' ");
            while (resultSet.next()){
                var dbName = resultSet.getString("Username");
                var dbPass = resultSet.getString("Password");
                var dbEmail=resultSet.getString("Email");
                if (Name.equals(dbName)&& Password.equals(dbPass)&& Email.equals(dbEmail)){
                    JOptionPane.showMessageDialog(this,"Account created");
//                    this.dispose();
                    Test tst=new Test();

                }else
                    JOptionPane.showMessageDialog(this,"Failed");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }


    }
}


