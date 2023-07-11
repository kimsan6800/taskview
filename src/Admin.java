import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame implements ActionListener {

    JButton b1;
    JTextField textField1 ;
    JPanel newPanel;
    JLabel l1;

    static JTable table;
    DefaultTableModel model;
    JComboBox<String> dropdown;

    Admin() {

        String[] columnNames = { "taskId","task","description","status","Assignto"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        newPanel = new JPanel();
        add(newPanel, BorderLayout.CENTER);
        l1= new JLabel();
//        l1.setText("Task1");
        textField1=new JTextField(20);


        String[] options = { "option 1`", "option 2", "option 3"};
        dropdown = new JComboBox<>(options);
        dropdown.setBounds(125, 100, 250, 35);

        b1 = new JButton("Create Task");
        b1.setBounds(125, 250, 250, 35);
        b1.setFocusable(false);

        newPanel.add(textField1);
        newPanel.add(dropdown);
        newPanel.add(b1);
        newPanel.add(table);
        b1.addActionListener(this);

        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Home Page");

//        populateTable();
//        }

//        public void populateTable() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from general ");
            int i = 0;
            while (resultSet.next()) {

                String Name = resultSet.getString("Name");
                String Email = resultSet.getString("Email");
                String Password = resultSet.getString("Password");


                model.addRow(new Object[]{Name,Email,Password });
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Name=textField1.getText();
        String selectedOption = dropdown.getSelectedItem().toString(); // Get the

        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into general(Name) values("+"'"+ Name +"')");
            System.out.println("insert into general(Name)values("+"'"+ Name +"')");
            JOptionPane.showMessageDialog(this,"Task Created successful");
            textField1.setText(""); // Clear the text field
            // Add an empty row for the new task
            model.addRow(new Object[] { Name, null, null }); // Add an empty row for the new task
//            System.out.println("insert into (admin) values("+"'"+task+"')");

            // Set the updated model to the table
            table.setModel(model);
//            newPanel.invalidate();
//            newPanel.revalidate();
//            newPanel.repaint();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void display() {
    }
}