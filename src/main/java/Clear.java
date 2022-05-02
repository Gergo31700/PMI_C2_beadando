import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Clear {

    public static void removeEmptyNodes(Node node) {

        NodeList list = node.getChildNodes();
        List<Node> nodesToRecursivelyCall = new LinkedList();

        for (int i = 0; i < list.getLength(); i++) {
            nodesToRecursivelyCall.add(list.item(i));
        }

        for(Node tempNode : nodesToRecursivelyCall) {
            removeEmptyNodes(tempNode);
        }

        boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE
                && node.getChildNodes().getLength() == 0;
        boolean emptyText = node.getNodeType() == Node.TEXT_NODE
                && node.getNodeValue().trim().isEmpty();

        if (emptyElement || emptyText) {
            if(!node.hasAttributes()) {
                node.getParentNode().removeChild(node);
            }
        }

    }

        Clear() {


            try {

                File XmLReader = new File("src\\main\\XMLFILES\\Students.xml");
                DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newDefaultInstance();
                DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
                Document doc = dbuilder.parse(XmLReader);
                doc.getDocumentElement().normalize();


                NodeList nlist = doc.getElementsByTagName("Student");

                HashMap<String, String> newmap = new HashMap<String, String>();




                for (int temp = 0; temp < nlist.getLength(); temp++) {

                    Node nNode = nlist.item(temp);
                    removeEmptyNodes(nNode);


                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;




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




    }


