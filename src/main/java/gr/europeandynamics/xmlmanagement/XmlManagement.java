package gr.europeandynamics.xmlmanagement;

import gr.europeandynamics.xmlmanagement.fileconvertorservice.FileConvertorImpl;

import java.io.IOException;

public class XmlManagement {

    public static void main(String[] args) throws IOException {

        FileConvertorImpl fileConvertor = new FileConvertorImpl();

        fileConvertor.convertFile("txt_files/sample-lorem-ipsum-text-file.txt", "xml_files/book.xml");

    }
}
