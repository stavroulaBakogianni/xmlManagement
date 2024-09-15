package gr.europeandynamics.xmlmanagement.xmlmanipulationservice;

import gr.europeandynamics.xmlmanagement.domain.Book;
import gr.europeandynamics.xmlmanagement.domain.Chapter;
import gr.europeandynamics.xmlmanagement.domain.Paragraph;
import gr.europeandynamics.xmlmanagement.domain.Sentence;
import gr.europeandynamics.xmlmanagement.domain.Statistics;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XsdGenerator {

    public static void xsdGenerator(String xsdFileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Book.class, Chapter.class, Paragraph.class, Sentence.class, Statistics.class);
            context.generateSchema(new MySchemaOutputResolver(xsdFileName));

            log.info("Succesfully created XSD schema for xml.");
        } catch (JAXBException | IOException e) {
            log.error(e.getMessage());
        }
    }

    static class MySchemaOutputResolver extends SchemaOutputResolver {

        private final String xsdFileName;

        public MySchemaOutputResolver(String xsdFileName) {
            this.xsdFileName = xsdFileName;
        }

        @Override
        public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
            File file = new File(xsdFileName);
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toString());
            return result;
        }
    }
}
