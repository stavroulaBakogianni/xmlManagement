package gr.europeandynamics.xmlmanagement.xmlmanipulationservice;

import gr.europeandynamics.xmlmanagement.domain.Chapter;
import gr.europeandynamics.xmlmanagement.domain.Paragraph;
import gr.europeandynamics.xmlmanagement.domain.Sentence;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XmlManipulatorImpl implements XmlManipulator {

    private List<Chapter> chapters = new ArrayList<>();
    private Paragraph currentParagraph = null;
    private Chapter currentChapter = null;

    @Override
    public void readXml(String fileName) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(fileName));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    switch (elementName) {
                        case "chapter":
                            currentChapter = new Chapter();
                            break;
                        case "paragraph":
                            currentParagraph = new Paragraph();
                            break;
                        case "sentence":

                            event = eventReader.nextEvent();
                            if (event instanceof Characters) {
                                String content = event.asCharacters().getData().trim();
                                if (!content.isEmpty()) {
                                    Sentence sentence = new Sentence(content);
                                    if (currentChapter != null) {
                                        currentParagraph.addSentence(sentence);
                                    }
                                }
                            }
                            break;
                    }
                } else if (event.isEndElement()) {
                    String endElement = event.asEndElement().getName().getLocalPart();

                    switch (endElement) {
                        case "paragraph":
                            if (currentChapter != null && currentParagraph != null) {
                                currentChapter.addParagraph(currentParagraph);
                            }
                            currentParagraph = null;
                            break;
                        case "chapter":
                            if (currentChapter != null) {
                                chapters.add(currentChapter);
                            }
                            currentChapter = null;
                            break;
                    }
                }
            }
            log.info("Successfully parsed the Xml document.");
        } catch (FileNotFoundException | XMLStreamException e) {
            log.error("Xml parsing failed.", e.getMessage());
        }
    }

    @Override
    public void writeXml(String fileName, int startChapter, int endChapter) {
        List<Chapter> fragmentChapters = chapters.subList(startChapter, endChapter);

        try {

            StringWriter stringWriter = new StringWriter();
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(stringWriter);

            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeStartElement("fragment");

            if (fragmentChapters.isEmpty()) {
                log.warn("No chapters found in the specified range.");
                return;
            }

            for (Chapter chapter : fragmentChapters) {
                if (chapter == null) {
                    continue;
                }

                writer.writeStartElement("chapter");

                for (Paragraph paragraph : chapter.getParagraphs()) {
                    if (paragraph == null) {
                        continue;
                    }

                    writer.writeStartElement("paragraph");

                    for (Sentence sentence : paragraph.getSentences()) {
                        if (sentence == null) {
                            continue;
                        }
                        writer.writeStartElement("sentence");
                        writer.writeCharacters(sentence.getContent());
                        writer.writeEndElement();
                    }

                    writer.writeEndElement();
                }

                writer.writeEndElement();
            }
            
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
            prettyWriteXmlToFile(fileName, stringWriter);
            log.info("Successfully wrote XML document to: {}", fileName);
        } catch (XMLStreamException | TransformerException | IOException e) {
            log.error("File not found: {}", fileName, e);
        }
    }

    private void prettyWriteXmlToFile(String xmlFilePath, StringWriter stringWriter) throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        StreamSource xmlSource = new StreamSource(new StringReader(stringWriter.toString()));
        StreamResult result = new StreamResult(new FileWriter(xmlFilePath));
        transformer.transform(xmlSource, result);
    }
}
