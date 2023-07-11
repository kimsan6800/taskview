
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Test extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel,emailLabel,passLabel;
    JTextField textField1, textField2, textField3;
    JComboBox<String>dropdown;

    Test(){

        userLabel = new JLabel();
        userLabel.setText("Name");

        textField1 = new JTextField(20);

        emailLabel =new JLabel();
        emailLabel.setText("Email");

        textField2= new JTextField(20);

        passLabel =new JLabel();
        passLabel.setText("Password");


        textField3= new JPasswordField(20);
        String[] task1 = { "taskId", "task1", "description","status" };
        JComboBox cb=new JComboBox();
        dropdown = new JComboBox<>(task1);
        dropdown.setBounds(125, 100, 250, 35);



        b1 = new JButton("Create Account");
        newPanel.setLayout(new FlowLayout());
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
        setTitle("create acc" );


        setFont(new Font("Times New Roman",Font.BOLD,11));
        setVisible(true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Name=textField1.getText();
        String Email =textField2.getText();
        String Password = textField3.getText();
        Connection connection= null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root","");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into credentials(Username,Email,Password) values("+"'"+Name+"',"+"'"+Email+"',"+"'"+Password+"')");
            JOptionPane.showMessageDialog(this,"Account Created successful");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Failed");
            System.out.println( ex);
        }




}}