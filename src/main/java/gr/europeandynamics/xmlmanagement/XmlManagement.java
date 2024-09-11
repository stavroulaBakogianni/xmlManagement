package gr.europeandynamics.xmlmanagement;

import gr.europeandynamics.xmlmanagement.importfileservice.TXTParser;
import java.io.IOException;

public class XmlManagement {

    public static void main(String[] args) throws IOException {

        TXTParser txtParser = new TXTParser();

        txtParser.readFile("txt_files/sample-lorem-ipsum-text-file.txt");
    }

}
