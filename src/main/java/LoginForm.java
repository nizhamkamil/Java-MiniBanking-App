import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginForm extends JFrame {
    public String IdUser ;
    public String FullName;
    public String rek;
    public String NoRek;
    public String Saldo;




    public LoginForm() {
        setTitle("Login Form");
        setSize(700,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setName("Panel 1");


        JLabel appTitle = new JLabel("Sistem Manajemen Perbankan");
        JButton button = new JButton("Login");
        JLabel userLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();

        ImageIcon icon = new ImageIcon(getClass().getResource("Bank Account_48px.png"));
        JLabel image = new JLabel(icon);

        loginPanel.setBackground(new Color(48, 54, 76));
        userLabel.setForeground(Color.white);
        passLabel.setForeground(Color.white);
        appTitle.setForeground(Color.WHITE);




        loginPanel.setLayout(null);

        appTitle.setFont(new Font("Serif", Font.PLAIN,30 ));
        loginPanel.add(appTitle);

        loginPanel.add(image);
        image.setBounds(275,10,150,50);

        loginPanel.add(appTitle);
        appTitle.setBounds(175,60,400,50);

        loginPanel.add(button);
        button.setBounds(275,250,200,50);

        loginPanel.add(userLabel);
        userLabel.setBounds(150,150,100,25);

        loginPanel.add(passLabel);
        passLabel.setBounds(150,200,100,25);

        loginPanel.add(userField);
        userField.setBounds(275,150,200,25);

        loginPanel.add(passField);
        passField.setBounds(275, 200,200,25);



        setResizable(false);
        add(loginPanel);
        setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MainPage mainPage = new MainPage();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/m-banking",
                            "root",
                            "");
                    String user_name = userField.getText();
                    String pass =  passField.getText();

                    Statement stmt = con.createStatement();

                    String sql = "select * from user where userName = '"+ user_name +"' and password = '"+ pass +"'";

                    ResultSet rs = stmt.executeQuery(sql);



                    if(rs.next()){
                        IdUser = rs.getString(1);
                        NoRek = rs.getString(2);
                        FullName = rs.getString(3);

                        dispose();

                        mainPage.id_user2= IdUser;
                        mainPage.No_Rek2 = NoRek;
                        mainPage.full_name2 = FullName;

                    }else{
                        mainPage.dispose();
                        JOptionPane.showMessageDialog(null,"Username or Password wrong...");
                        //userField.setText("");
                        passField.setText("");

                    }

                    String sql2 = "select * from rekening where noRekening = '"+NoRek+"'";
                    ResultSet rs2 = stmt.executeQuery(sql2);

                    if(rs2.next()){
                        Saldo = rs2.getString(2);

                        mainPage.rek2 = Saldo;
                    }else{
                        //userField.setText("");
                        passField.setText("");
                    }

                    con.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });




    }

    public static void main(String[] args) {
       new LoginForm();
    }

}
