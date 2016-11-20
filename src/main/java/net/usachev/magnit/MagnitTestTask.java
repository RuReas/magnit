package net.usachev.magnit;

import net.usachev.magnit.converter.NConverter;
import net.usachev.magnit.converter.SAXSumElements;
import net.usachev.magnit.converter.XmlConverterImpl;
import net.usachev.magnit.repository.NRepository;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MagnitTestTask {

    private static final Logger LOG = getLogger(MagnitTestTask.class);

    private int n;

    private NRepository repository;

    private NConverter converter;

    public MagnitTestTask() {
        repository = AppConfig.get().getRepository();
        LOG.info("Get instance of Repository");
        repository.createTable();
        LOG.info("Create table TEST");
        converter = new XmlConverterImpl();
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void insertRecords() {
        repository.insertRecords(getN());
        LOG.info("Insert {} records in table", getN());
    }

    public void createXml(String filePath) throws TransformerException, IOException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(converter.createXML(repository.getRecords())),
                new StreamResult(new FileOutputStream(filePath)));
        LOG.info("Create XML <{}>", filePath);
    }

    public void convertXML(String fromXml, String toXml, String xsltRules) throws TransformerException {
        Source xslt = new StreamSource(new File(xsltRules));
        Transformer transformer = TransformerFactory.newInstance().newTransformer(xslt);
        Source xml = new StreamSource(new File(fromXml));
        transformer.transform(xml, new StreamResult(new File(toXml)));
        LOG.info("Convert XML from <{}> to <{}>", fromXml, toXml);
    }

    public long parseToSum(String fromXml) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        SAXSumElements saxSumElements = new SAXSumElements();
        parser.parse(new File(fromXml), saxSumElements);
        LOG.info("Parse and sum of elements XML <{}>", fromXml);
        return saxSumElements.getSum();
    }
}
