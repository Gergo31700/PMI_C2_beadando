import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Welcome_Page implements ActionListener {
    public String DeleteUSer="";

    JFrame ConfirmationWindow  = new JFrame();
    JLabel ConformationMessage = new JLabel();

    JButton GPA = new JButton("GPA");


    JFrame frame = new JFrame();
    JLabel welcomelabel = new JLabel("Hello");

    JButton Edit = new JButton("Delete My Account");


    public Welcome_Page(String username){
        welcomelabel.setBounds(0,0,300,35);
        welcomelabel.setFont(new Font(null,Font.PLAIN,25));
        welcomelabel.setText("Hello\n  "+username);
        JLabel adatok = new JLabel();
        JLabel courses = new JLabel();
frame.setLocation(700,300);

        Edit.setBounds(125,100,250,25);
        Edit.setFocusable(false);
        Edit.addActionListener(this);

        GPA.setBounds(125,200,82,25);
        GPA.setFocusable(false);
        GPA.addActionListener(this);



        frame.add(GPA);
        frame.add(courses);
        frame.add(Edit);
        frame.add(adatok);
        frame.add(welcomelabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,280);
        frame.setLayout(null);
        frame.setVisible(true);

        ConfirmationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConfirmationWindow.setSize(600,280);
        ConfirmationWindow.setLocation(700,300);
        ConfirmationWindow.setLayout(null);
        ConformationMessage.setFont(new Font(null,Font.PLAIN,25));
        ConformationMessage.setBounds(0,0,500,35);
        ConformationMessage.setText("The account "+username+" has been deleted");
        ConfirmationWindow.add(ConformationMessage);






//Read the courses and other details about the student
        try {
            File XmLReader = new File("src\\main\\XMLFILES\\Students.xml");
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
            Document doc = dbuilder.parse(XmLReader);
            doc.getDocumentElement().normalize();
            String courseHolder;

            NodeList nlist = doc.getElementsByTagName("Student");







            for (int temp = 0; temp< nlist.getLength();temp++)
            {

                Node nNode = nlist.item(temp);



                if (nNode.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element eElement = (Element)nNode;

                    DeleteUSer=username;
                    if (eElement.getAttribute("Jupider_Code_ID").equals(username))  {




                        //DELETE DETAILS ABOUT THE STUDENT

                        courseHolder = eElement.getElementsByTagName("Courses").item(0).getTextContent();

                        //Jupiter_Kód kiiráasa
                        adatok.setText("Courses");
                        adatok.setBounds( 50,50,300,35);
                        adatok.setFont(new Font(null,Font.ITALIC,10));


                        courses.setText(courseHolder);
                        courses.setBounds( 50,70,400,35);
                        courses.setFont(new Font(null,Font.ITALIC,10));

                    }





                }



            }



        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }








    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==Edit)
        {
            frame.dispose();
          new DeleteStudent(DeleteUSer);
            ConfirmationWindow.setVisible(true);

        }

        if(e.getSource()==GPA)
        {
            GPA_Calculator gpa_calculator =new GPA_Calculator();
        }



    }
}
