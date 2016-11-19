package net.usachev.magnit.converter;

import org.w3c.dom.Document;

import javax.xml.transform.TransformerException;
import java.nio.file.Path;
import java.util.List;

public interface NConverter {

    Document createXML(List<Integer> records);

    int ParseAndSum(Path path);
}
