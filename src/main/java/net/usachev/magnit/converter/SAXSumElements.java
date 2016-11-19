package net.usachev.magnit.converter;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXSumElements extends DefaultHandler {
    private int sum = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("entry")) {
            sum += Integer.valueOf(attributes.getValue(0));
        }
    }

    public int getSum() {
        return sum;
    }
}
