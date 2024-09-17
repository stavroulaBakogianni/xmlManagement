package gr.europeandynamics.xmlmanagement.fileconvertorservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileConvertorImpl implements FileConvertor {

    private int paragraphCount = 0;
    private int sentenceCount = 0;
    private int chapterCount = 0;
    private int wordCount = 0;
    private Set<String> uniqueWords = new HashSet<>();
    final String regex = "[\\p{Punct}]";
    private static final int PARAGRAPHS_PER_CHAPTER = 20;

    @Override
    public boolean convertFile(String txtFilePath, String xmlFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath))) {
            String line;
            int paragraphInChapter = 0;
            StringWriter stringWriter = new StringWriter();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);

            xmlWriter.writeStartDocument("UTF-8", "1.0");
            xmlWriter.writeStartElement("book");
            xmlWriter.writeStartElement("chapter");
            chapterCount++;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    paragraphCount++;
                    paragraphInChapter++;
                    xmlWriter.writeStartElement("paragraph");
                    String[] sentences = line.split("\\.\\s*");

                    for (String sentence : sentences) {
                        if (!sentence.trim().isEmpty()) {
                            sentenceCount++;
                            xmlWriter.writeStartElement("sentence");
                            xmlWriter.writeCharacters(sentence.trim() + ".");
                            xmlWriter.writeEndElement();
                            String[] words = sentence.split("\\s+");

                            for (String word : words) {
                                wordCount++;
                                String wordWithoutPunctuation = word.replaceAll(regex, "");
                                uniqueWords.add(wordWithoutPunctuation.toLowerCase());
                            }
                        }
                    }

                    xmlWriter.writeEndElement();

                    if (paragraphInChapter == PARAGRAPHS_PER_CHAPTER) {
                        xmlWriter.writeEndElement();
                        xmlWriter.writeStartElement("chapter");
                        chapterCount++;
                        paragraphInChapter = 0;
                    }
                }
            }

            xmlWriter.writeEndElement();

            writeStatistics(xmlWriter);

            xmlWriter.writeEndElement();

            xmlWriter.writeEndDocument();
            xmlWriter.flush();
            xmlWriter.close();

            writeXmlToFile(xmlFilePath, stringWriter);
            log.info("File conversion completed successfully.");
            return true;
        } catch (IOException e) {
            log.error("Error reading or writing the file: {}", e.getMessage(), e);
            return false;
        } catch (XMLStreamException e) {
            log.error("Error generating the XML: {}", e.getMessage(), e);
            return false;
        } catch (TransformerException e) {
            log.error("Error transforming the XML: {}", e.getMessage(), e);
            return false;
        }
    }

    private void writeStatistics(XMLStreamWriter xmlWriter) throws XMLStreamException {
        xmlWriter.writeStartElement("statistics");

        xmlWriter.writeStartElement("numberOfParagraphs");
        xmlWriter.writeCharacters(String.valueOf(paragraphCount));
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement("numberOfSentences");
        xmlWriter.writeCharacters(String.valueOf(sentenceCount));
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement("numberOfWords");
        xmlWriter.writeCharacters(String.valueOf(wordCount));
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement("numberOfDistinctWords");
        xmlWriter.writeCharacters(String.valueOf(uniqueWords.size()));
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement("dateTime");
        xmlWriter.writeCharacters(LocalDateTime.now().toString());
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement("author");
        xmlWriter.writeCharacters("Stavroula Bakogianni");
        xmlWriter.writeEndElement();

        xmlWriter.writeStartElement("applicationName");
        xmlWriter.writeCharacters("XML Management Application");
        xmlWriter.writeEndElement();

        xmlWriter.writeEndElement();
    }

    private void writeXmlToFile(String xmlFilePath, StringWriter stringWriter) throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        StreamSource xmlSource = new StreamSource(new StringReader(stringWriter.toString()));
        StreamResult result = new StreamResult(new FileWriter(xmlFilePath));
        transformer.transform(xmlSource, result);
    }
}
