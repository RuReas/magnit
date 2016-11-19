package net.usachev.magnit;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TransformerException, IOException, ParserConfigurationException, SAXException {
        MagnitTestTask magnitTestTask = new MagnitTestTask();
        magnitTestTask.setN(100);
        magnitTestTask.insertRecords();
        String fromXML = "1.xml";
        String toXML = "2.xml";
        String xsltRules = "transformer.xsl";
        magnitTestTask.createXml(fromXML);
        magnitTestTask.convertXML(fromXML, toXML, xsltRules);
        System.out.println("Sum of elements: " + magnitTestTask.parseToSum(toXML));
    }
}
