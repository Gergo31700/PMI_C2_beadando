import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class RegisterPage  implements ActionListener {
    public JFrame frame = new JFrame();
    public  JLabel messageLabel = new JLabel("Register Page");
    public JButton registerbutton = new JButton("Register");

    public JButton Switchtologin = new JButton("Switch to Login");


    public JTextField username = new JTextField();
    public JTextField fullname  = new JTextField();
    public JPasswordField userPassword = new JPasswordField();

    public  JLabel usernameLabel = new JLabel("JupiterID");
    public  JLabel fullnamelabel = new JLabel("Full name");
    public   JLabel passworLabel = new JLabel("Password");

    boolean tester = true;


    public RegisterPage()
    {
        frame.setLocation(700,300);
        frame.setTitle("Jupiter");
        usernameLabel.setBounds(50,100,75,25);
        passworLabel.setBounds(50,150,75,25);
        fullnamelabel.setBounds(50,50,75,25);

        messageLabel.setBounds(150,20,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,16));

        username.setBounds(125,100,200,25);
        userPassword.setBounds(125,150,200,25);
        fullname.setBounds(125,50,200,25);

        registerbutton.setBounds(150,350,100,25);
        registerbutton.setFocusable(false);
        registerbutton.addActionListener(this);

        Switchtologin.setBounds(250,350,150,25);
        Switchtologin.setFocusable(false);
        Switchtologin.addActionListener(this);


        frame.add(Switchtologin);
        frame.add(registerbutton);
        frame.add(username);
        frame.add(fullname);
        frame.add(fullnamelabel);
        frame.add(userPassword);
        frame.add(messageLabel);
        frame.add(usernameLabel);
        frame.add(passworLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420); //nice
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public  void WriteIntoXMl() {

        try {
            File xmlFile = new File("src\\main\\XMLFILES\\Students.xml");
            DocumentBuilderFactory newdbfactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder documentBuilder = null;
            try {
                documentBuilder = newdbfactory.newDocumentBuilder();
            }
            catch (ParserConfigurationException e)
            {
                e.printStackTrace();
            }

        try
        {
            Document doc = documentBuilder.parse(xmlFile);
            Element documentElment = doc.getDocumentElement();
            Element rootelemnt = doc.createElementNS("TTK","UniversityofPecs");
          //  doc.appendChild(rootelemnt);

            documentElment.appendChild(getClient(doc,username.getText(),fullname.getText() ,String.valueOf(userPassword.getPassword()),"Calculus 1","Programming 1","Data Structures and Algorithms"));

            doc.replaceChild(documentElment, documentElment);
            Transformer tFormer =
                    TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.INDENT, "Yes");
            DOMSource source = new DOMSource(doc);
            Result result = new StreamResult(xmlFile);
            StreamResult file = new StreamResult(new File("src\\main\\XMLFILES\\Students.xml"));
            tFormer.transform(source, file);

        } catch (TransformerException | SAXException | IOException e) {

            e.printStackTrace();
        }

        } catch (DOMException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private static Node getClient(Document doc, String Jupiter_Code, String Student_Name, String lname, String Course, String Course2, String Course3) {


        Element client = doc.createElement("Student");
        Element test =doc.createElement("Courses");


        client.setAttribute("Jupider_Code_ID",Jupiter_Code); //Each user will have an id so it will be easier to , find them, work with the accounts

        client.appendChild(getClientDetails(doc,client,"Full_Name",Student_Name));
        client.appendChild(getClientDetails(doc,client,"Password",lname));
        client.appendChild(test);
        test.appendChild(getClientDetails(doc,client,"Course",Course));
        test.appendChild(getClientDetails(doc,client,"Course",Course2));
        test.appendChild(getClientDetails(doc,client,"Course",Course3));
        return client;
    }


        public boolean SpecialCharacthers (String Wordtocheck){
            String inputString =Wordtocheck;
            String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}1234567890";
            for (int i=0; i < inputString.length() ; i++)
            {
                char ch = inputString.charAt(i);
                if(specialCharactersString.contains(Character.toString(ch))) {
                    System.out.println(inputString+ " contains special character");
                    tester=false;
                    return false;

                }


            }
            tester=true;
            return true;
        }



    private static Node getClientDetails(Document doc, Element client, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return  node;
    }




    @Override
    public void actionPerformed(ActionEvent e) {


        String test = fullname.getText();
        String test2 = String.valueOf(userPassword.getPassword());
        String test3 = username.getText();

        SpecialCharacthers(test);


        if (test.equals("")) {
    test=null;
        }

        if (test2.equals("")) {
            test2=null;
        }

        if (test3.equals("")) {
            test3=null;
        }

        if (test != null && tester == true && test2!=null&& test3!=null) {

            if (e.getSource() == registerbutton) {
                WriteIntoXMl();

                frame.dispose();
                UsrnPass user = new UsrnPass();
                LoginPage loginPage = new LoginPage(user.getLogin_Info());

            }
        }
        else {
            messageLabel.setBounds(1,60,500,500);

            messageLabel.setText("<html>Full name might"+" contain numbers or special characthers,<br/> Make sure password and id is not empty</html> ");
        }

        if (e.getSource()==Switchtologin)
        {   frame.dispose();
            UsrnPass user = new UsrnPass();


            LoginPage loginPage = new LoginPage(user.getLogin_Info());
        }
    }

}
