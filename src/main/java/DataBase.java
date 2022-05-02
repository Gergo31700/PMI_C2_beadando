import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DataBase {

    public void Data ()
    {
        try {
            File XmLReader = new File("src\\main\\XMLFILES\\Students.xml");
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
            Document doc = dbuilder.parse(XmLReader);
            doc.getDocumentElement().normalize();

            NodeList nlist = doc.getElementsByTagName("Student");

            HashMap<String,String> newmap = new HashMap<String,String>();
            String nev;
            String password;

            for (int temp = 0; temp< nlist.getLength();temp++)
            {

                Node nNode = nlist.item(temp);

                if (nNode.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element eElement = (Element)nNode;
                    nev =  eElement.getElementsByTagName("Jupiter_Code").item(0).getTextContent();
                    password = eElement.getElementsByTagName("Student_Name").item(0).getTextContent();
                    newmap.put(nev,password);

                }


            }





        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


    }

}
