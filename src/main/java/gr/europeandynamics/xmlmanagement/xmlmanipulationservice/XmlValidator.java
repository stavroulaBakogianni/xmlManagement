package gr.europeandynamics.xmlmanagement.xmlmanipulationservice;

import gr.europeandynamics.xmlmanagement.domain.Book;
import gr.europeandynamics.xmlmanagement.domain.Chapter;
import gr.europeandynamics.xmlmanagement.domain.Paragraph;
import gr.europeandynamics.xmlmanagement.domain.Sentence;
import gr.europeandynamics.xmlmanagement.domain.Statistics;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.SAXException;

@Slf4j
public class XmlValidator {

    /**
     * Validates the structure and content of an XML file against the provided
     * XSD schema. The XML file is unmarshalled into Java objects, and the
     * validation occurs based on the schema.
     *
     * @param xmlFileName The path to the XML file that needs to be validated.
     * @param xsdFileName The path to the XSD file that defines the XML schema.
     */
    public static void xmlValidator(String xmlFileName, String xsdFileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Book.class, Chapter.class, Paragraph.class, Sentence.class, Statistics.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = schemaFactory.newSchema(new File(xsdFileName));
            unmarshaller.setSchema(schema);
            File xmlFile = new File(xmlFileName);

            Object object = unmarshaller.unmarshal(xmlFile);
            log.info("Xml validation passed!", object);
        } catch (JAXBException | SAXException e) {
            log.error("Xml is invalid!", e);
        }

    }
}
