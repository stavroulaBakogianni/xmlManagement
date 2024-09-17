package gr.europeandynamics.xmlmanagement;

import gr.europeandynamics.xmlmanagement.fileconvertorservice.FileConvertorImpl;
import gr.europeandynamics.xmlmanagement.xmlmanipulationservice.XmlManipulator;
import gr.europeandynamics.xmlmanagement.xmlmanipulationservice.XmlManipulatorImpl;
import gr.europeandynamics.xmlmanagement.xmlmanipulationservice.XmlValidator;
import gr.europeandynamics.xmlmanagement.xmlmanipulationservice.XsdGenerator;

import java.io.IOException;

public class XmlManagement {

    public static void main(String[] args) throws IOException {
        final String txtFileName = "txt_files/sample-lorem-ipsum-text-file.txt";
        final String xmlFileName = "xml_files/book.xml";
        final String xsdFileName = "xml_files/book-schema.xsd";
        final String xmlFragmentFileName = "xml_files/fragment-chapters.xml";
        FileConvertorImpl fileConvertor = new FileConvertorImpl();

        fileConvertor.convertFile(txtFileName, xmlFileName);

        XsdGenerator.xsdGenerator(xsdFileName);
        XmlValidator.xmlValidator(xmlFileName, xsdFileName);
        XmlManipulator xmlManipulator = new XmlManipulatorImpl();
        xmlManipulator.readXml(xmlFileName);
        xmlManipulator.writeXml(xmlFragmentFileName, 5, 8);
        xmlManipulator.calculateStatistics();
    }

}
