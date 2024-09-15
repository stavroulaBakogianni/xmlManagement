package gr.europeandynamics.xmlmanagement;

import gr.europeandynamics.xmlmanagement.fileconvertorservice.FileConvertorImpl;
import gr.europeandynamics.xmlmanagement.xmlmanipulationservice.XmlValidator;
import gr.europeandynamics.xmlmanagement.xmlmanipulationservice.XsdGenerator;

import java.io.IOException;

public class XmlManagement {

    public static void main(String[] args) throws IOException {

        FileConvertorImpl fileConvertor = new FileConvertorImpl();

        fileConvertor.convertFile("txt_files/sample-lorem-ipsum-text-file.txt", "xml_files/book.xml");
        final String xsdFileName = "xml_files/book-schema.xsd";
        XsdGenerator.xsdGenerator(xsdFileName);
        final String xmlFileName = "xml_files/book.xml";
        XmlValidator.xmlValidator(xmlFileName, xsdFileName);
    }
}
