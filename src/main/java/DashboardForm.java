import com.example.demo2.Register;
import com.example.demo2.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DashboardForm extends JFrame{
    private JButton btnstudent;
    private JLabel Admin;
    private JPanel dashboardpanel;

    public DashboardForm(){
        setTitle("Dashboard");
        setContentPane(dashboardpanel);
        setMinimumSize(new Dimension(450, 475));
        setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boolean hasRegisteredUsers = connectToDatabase();
        if(hasRegisteredUsers){
            LoginForm loginForm =new LoginForm(this);
            User user = loginForm.user;

            if(user != null){
                Admin.setText("User: "+user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else{
                dispose();
            }
        }
        else{
            Register register =new Register(this);
            User user= register.user;

            if(user != null) {
                Admin.setText("User: " + user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
        btnstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register(DashboardForm.this);
                User user = register.user;

                if(user!=null){
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "New user: " +user.name,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private boolean connectToDatabase(){
        boolean hasRegisteredUsers = false;

        final String MYSQL_SERVER_URL = "jdbc:mysql://localhost/";
        final String DB_URL = "jdbc:mysql://localhost/Mystore?serverTimezone=UTC";
        final String USERNAME ="root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);

            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS Mystore");
            statement.close();
            conn.close();

            conn = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users("
                    + "id INT (10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(200) NOT NULL UNIQUE,"
                    + "gender VARCHAR(200),"
                    + "address VARCHAR(200),"
                    + "course VARCHAR(200),"
                    + "fee VARCHAR(200),"
                    + "contact VARCHAR(200),"
                    + "password VARCHAR(200) NOT NULL,"
                    + ")";
            statement.executeUpdate(sql);

            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
            if(resultSet.next()){
                int numUsers = resultSet.getInt(1);
                if(numUsers > 0){
                    hasRegisteredUsers = true;
                }
            }
            statement.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return hasRegisteredUsers;
    }

    public static void main(String[] args) {

        DashboardForm myForm=new DashboardForm();
    }
}
