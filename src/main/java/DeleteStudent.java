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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class DeleteStudent implements ActionListener {
    JFrame DeleteFrame = new JFrame();
    JButton confirm = new JButton("confirm");
    JButton Cancel = new JButton("Cancel");




    DeleteStudent(String user) {


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
                System.out.println(temp);



                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;


                    if(eElement.getAttribute("Jupider_Code_ID").equals(user)) { //In case we want to delete our account this if statement check, if we are working with the right account details




                        // First part of deleting a student (Delete Child Nodes)


                        nNode.removeChild(eElement.getElementsByTagName("Password").item(0));
                        nNode.removeChild(eElement.getElementsByTagName("Courses").item(0));
                        nNode.removeChild(eElement.getElementsByTagName("Full_Name").item(0));
                       ((Element) nNode).removeAttributeNode(eElement.getAttributeNode("Jupider_Code_ID"));





                    }
/*



                    }

*/


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
                    //Second part of deleting Students (Clear the empty nodes), this will delete the parent nodes
            Clear clear=new Clear();


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


        DeleteFrame.add(confirm);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

