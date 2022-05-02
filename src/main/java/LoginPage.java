import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage  implements ActionListener {
    JFrame frame = new JFrame();
    public String  userName1="";
    JButton loginbutton = new JButton("Login");
    JButton resetbutton = new JButton("Register");



    JTextField username = new JTextField();
    JPasswordField userPassword = new JPasswordField();
    JButton EDITPASS = new JButton("Recover my password");
    JLabel usernameLabel = new JLabel("JupiterID");
    JLabel passworLabel = new JLabel("Password");

    JLabel messageLabel = new JLabel("");

    HashMap<String,String> loginfo= new HashMap<String,String>();//A Hashmap is needed to store the information we enter into the textfields

    LoginPage(HashMap<String,String> logininfooriginal)  {
        frame.setTitle("Jupiter");
        loginfo = logininfooriginal;
        frame.setLocation(700,300);
        usernameLabel.setBounds(50,100,75,25);
        passworLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        EDITPASS.setBounds(125,175,180,25);
        EDITPASS.setFocusable(false);
        EDITPASS.addActionListener(this);

        username.setBounds(125,100,200,25);
        userPassword.setBounds(125,150,200,25);

        loginbutton.setBounds(125,200,100,25);
        loginbutton.setFocusable(false);
        loginbutton.addActionListener(this);

        resetbutton.setBounds(225,200,100,25);
        resetbutton.setFocusable(false);
        resetbutton.addActionListener(this);

        frame.add(loginbutton);
        frame.add(resetbutton);
        frame.add(username);
        frame.add(userPassword);
        frame.add(messageLabel);
        frame.add(usernameLabel);
        frame.add(passworLabel);
        frame.add(EDITPASS);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420); //nice
        frame.setLayout(null);
        frame.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==resetbutton)
        {


            //A meglévő adat törlése
            frame.dispose();
          RegisterPage regi = new RegisterPage();
        }

        if (e.getSource()==loginbutton)
        {
            userName1 = username.getText();
            String passWORD = String.valueOf(userPassword.getPassword());


            if (loginfo.containsKey(userName1))
            {
                if (loginfo.get(userName1).equals(passWORD))
                {
                    messageLabel.setForeground(Color.CYAN);
                    messageLabel.setText("Sikeres Bejelentkezés");
                    frame.dispose();
                    String courses ="";

                    Welcome_Page welcome_page = new Welcome_Page(userName1);


                }

                else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Sikertelen Bejelentkezés");

                }
            }
            else
            {

                messageLabel.setText("Nincs ilyen felhasznalo");
            }

        }

        if (e.getSource()==EDITPASS)
        {

            frame.dispose();

            UsrnPass user = new UsrnPass();



            Courses courses = new Courses(user.getLogin_Info());


        }


    }
}
