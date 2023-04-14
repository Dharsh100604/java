import com.example.demo2.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JPasswordField pfPassword;
    private JTextField tfEmail;
    private JButton loginButton;
    private JPanel loginpanel;
    private JButton btncancel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginpanel);
        setMinimumSize(new Dimension(550,500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = getAuthenticationUser(email, password);

                if( user !=null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this,
                            "Email or Password Invalid",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public User user;
    private User getAuthenticationUser(String email,String password){
        User user =null;
        final String DB_URL = "jdbc:mysql://localhost/mystore?serverTimezone=UTC";
        final String USERNAME ="root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND password";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.gender = resultSet.getString("gender");
                user.address = resultSet.getString("address");
                user.course = resultSet.getString("course");
                user.fee = resultSet.getString("fee");
                user.contact = resultSet.getString("contact");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {

        LoginForm loginForm = new LoginForm(null);
        User user = loginForm.user;
        if(user!= null){
            System.out.println("Successful authentication of: "+user.name);
            System.out.println("            Email: "+user.email);
            System.out.println("            Phone: "+user.fee);
            System.out.println("            Address: "+user.address);


        }
        else{
            System.out.println("Authentication cancelled");
        }
    }
}
