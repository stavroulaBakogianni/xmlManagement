package gr.europeandynamics.xmlmanagement;

import gr.europeandynamics.xmlmanagement.fileconvertorservice.ConvertTXTToXML;
import java.io.IOException;

public class XmlManagement {

    public static void main(String[] args) throws IOException {

        ConvertTXTToXML txtParser = new ConvertTXTToXML();

        txtParser.convertFile("txt_files/sample-lorem-ipsum-text-file.txt","xml_files/book.xml");
    }

}
