import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Courses  implements ActionListener {
    JFrame frame = new JFrame();
    JFrame passwordscreen = new JFrame();
    JButton submit = new JButton("Submit");
    JButton Go = new JButton("Go");
    JButton BackToLogin = new JButton("Back to Login");
    JTextField username = new JTextField();

    JLabel messageLabel = new JLabel("");


    HashMap<String,String> loginfo= new HashMap<String,String>();




    public  Courses (HashMap<String,String> logininfooriginal) {


         String userName1 = "";

        loginfo = logininfooriginal;




        JLabel usernameLabel = new JLabel("JupiterID");



        HashMap<String, String> loginfo = new HashMap<String, String>();//A Hashmap is needed to store the information we enter into the textfields


            frame.setTitle("Jupiter");

            frame.setLocation(900, 300);
            usernameLabel.setBounds(50, 100, 75, 25);


            messageLabel.setBounds(125, 20, 300, 35);
            messageLabel.setFont(new Font(null, Font.ITALIC, 15));

            username.setBounds(125, 100, 200, 25);


        submit.setBounds(105, 200, 100, 25);
        submit.setFocusable(false);
        submit.addActionListener(this);


        Go.setBounds(30, 200, 100, 25);
        Go.setFocusable(false);
        Go.addActionListener(this);


            frame.add(submit);

            frame.add(username);

            frame.add(messageLabel);
            frame.add(usernameLabel);


            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(420, 420); //nice
            frame.setLayout(null);
            frame.setVisible(true);


        messageLabel.setBounds(125, 20, 300, 35);
        BackToLogin.setBounds(200, 200, 135, 25);

        passwordscreen.setSize(500,200);
        passwordscreen.setLocation(700,300);
        passwordscreen.setLayout(null);
        BackToLogin.addActionListener(this);

        passwordscreen.add(BackToLogin);
        passwordscreen.add(messageLabel);
        frame.add(BackToLogin);


        }





    public void Recovery(String user) {

        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                +"jklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));

        try {
            //Read the xml file
            File XmLReader = new File("src\\main\\XMLFILES\\Students.xml");
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
            Document doc = dbuilder.parse(XmLReader);
            doc.getDocumentElement().normalize();


            NodeList nlist = doc.getElementsByTagName("Student");

            HashMap<String, String> newmap = new HashMap<String, String>();

            for (int temp = 0; temp < nlist.getLength(); temp++) {

                Node nNode = nlist.item(temp);



                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;


                    if (eElement.getAttribute("Jupider_Code_ID").equals(user)) { //In case we want to delete our account this if statement check, if we are working with the right account details




                        // First part of deleting a student (Delete Child Nodes)

                        passwordscreen.add(BackToLogin);
                        eElement.getElementsByTagName("Password").item(0).setTextContent(sb.toString());
                        System.out.println(sb.toString());
                        BackToLogin.setBounds(50, 90, 135, 25);
                        messageLabel.setText("Your new password is  "+ sb.toString());
                        passwordscreen.setVisible(true);

                    }




                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = null;
                try {
                    transformer = transformerFactory.newTransformer();
                } catch (TransformerConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                DOMSource domSource = new DOMSource((Node) doc);
                StreamResult streamResult = new StreamResult(XmLReader);
                try {
                    transformer.transform(domSource, streamResult);
                } catch (TransformerException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {



    if (e.getSource()==submit)
    {
        String userName1 = username.getText();


        if (loginfo.containsKey(userName1))
        {
            Recovery(userName1);
            frame.dispose();



        }

        else {

            messageLabel.setText("User not found");
        }

    }

if   (e.getSource()==BackToLogin){


    UsrnPass user = new UsrnPass();


    LoginPage loginPage = new LoginPage(user.getLogin_Info());
    frame.dispose();
    passwordscreen.dispose();
}

    }
}
