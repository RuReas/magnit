package net.usachev.magnit.converter;

import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.nio.file.Path;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class XmlConverterImpl implements NConverter {

    private static final Logger LOG = getLogger(XmlConverterImpl.class);

    public XmlConverterImpl() {
    }

    @Override
    public Document createXML(List<Integer> records) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error("Document Builder error " + e.getMessage());
            return null;
        }

        factory.setNamespaceAware(true);
        Document document = builder.newDocument();
        Element rootElement = document.createElement("entries");

        for (Integer item : records) {
            Element entry = document.createElement("entry");
            Element field = document.createElement("field");
            field.appendChild(document.createTextNode(Integer.toString(item)));
            entry.appendChild(field);
            rootElement.appendChild(entry);
        }

        document.appendChild(rootElement);

        return document;
    }

    @Override
    public int ParseAndSum(Path path) {
        return 0;
    }
}
