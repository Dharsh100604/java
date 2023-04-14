package com.example.demo2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Register extends JDialog {
    private JTextField tfEmail;
    private JTextField tfName;
    private JTextField tfGender;
    private JTextField tfAddress;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerpanel;
    private JTextField tfCourse;
    private JTextField tfFee;
    private JTextField tfContact;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;

    public Register(JFrame parent)
    {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerpanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String gender = tfGender.getText();
        String address = tfAddress.getText();
        String course = tfCourse.getText();
        String fee = tfFee.getText();
        String contact = tfContact.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmpassword = String.valueOf(pfConfirmPassword.getPassword());

        if(name.isEmpty() || email.isEmpty() || gender.isEmpty() || address.isEmpty() || course.isEmpty() || fee.isEmpty() || contact.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!password.equals(confirmpassword)){
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }



        user = addUserToDatabase(name, email, gender, address, course, fee, contact, password);
        if( user !=null){
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User addUserToDatabase(String name, String email, String gender, String address, String course,String fee,String contact, String password){
        User user=null;
        final String DB_URL = "jdbc:mysql://localhost/Mystore?serverTimezone=UTC";
        final String USERNAME ="root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO users (name, email, gender, address, course, fee, contact, password) "+
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5,course);
            preparedStatement.setString(6,fee);
            preparedStatement.setString(7,contact);
            preparedStatement.setString(8,password);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                user = new User();
                user.name = name;
                user.email = email;
                user.gender = gender;
                user.address = address;
                user.course = course;
                user.fee = fee;
                user.contact = contact;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args)
    {
        Register myform=new Register(null);
        User user = myform.user;
        if(user!= null){
            System.out.println("Successful registration of: "+user.name);
        }
        else{
            System.out.println("Registration cancelled");
        }
    }

}
