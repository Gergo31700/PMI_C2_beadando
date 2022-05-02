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

public class UsrnPass {

    HashMap<String,String> Login_Info = new HashMap<>();



    public   String nev;

    UsrnPass (){


        try {
            File XmLReader = new File("src\\main\\XMLFILES\\Students.xml");
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
            Document doc = dbuilder.parse(XmLReader);
            doc.getDocumentElement().normalize();


            NodeList nlist = doc.getElementsByTagName("Student");



            String password;

            for (int temp = 0; temp< nlist.getLength();temp++)
            {

                Node nNode = nlist.item(temp);

                Element eElement = (Element)nNode;


                if (nNode.getNodeType()==Node.ELEMENT_NODE)
                {



                    nev =  eElement.getAttribute("Jupider_Code_ID");



                    password = eElement.getElementsByTagName("Password").item(0).getTextContent();

                    Login_Info.put(nev,password);


                }



            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }




    protected HashMap<String, String> getLogin_Info() {
        return Login_Info;
    }
}



