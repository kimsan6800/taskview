
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class label extends JFrame implements ActionListener {
    JButton b1, b2;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    JTextField textField1, textField2;
    JTextArea outputArea;

    public label() {
        userLabel = new JLabel();
        userLabel.setText("Name");
        userLabel.setBounds(50, 100, 75, 25);
        textField1 = new JTextField(20);

        passLabel = new JLabel();
        passLabel.setText("Password");
        passLabel.setBounds(50, 150, 75, 25);
        textField2 = new JPasswordField(20);

        b1 = new JButton("Create Account");
        b1.setBounds(125, 250, 250, 35);
        b1.setFocusable(false);

        b2 = new JButton("Login");
        b2.setBounds(125, 300, 250, 35);
        b2.setFocusable(false);

        outputArea = new JTextArea();
        outputArea.setBounds(50, 350, 325, 100);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        newPanel.add(b2);
        newPanel.add(outputArea);

        getContentPane().setBackground(Color.blue);
        add(newPanel, BorderLayout.CENTER);

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
        String Name = textField1.getText();
        String Password = textField2.getText();

        // Append the Name and Password to the output area
        outputArea.append("Name: " + Name + "\n");
        outputArea.append("Password: " + Password + "\n");
        outputArea.append("------------------------\n");

        if (Name.equals("") && Password.equals("")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            this.dispose();
            homepage home = new homepage();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed!");
        }
    }

}
