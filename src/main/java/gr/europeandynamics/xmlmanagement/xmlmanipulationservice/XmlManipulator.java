package gr.europeandynamics.xmlmanagement.xmlmanipulationservice;

public interface XmlManipulator {

    /**
     * Reads and processes the content of an XML file.
     *
     * @param fileName The path to the XML file to be read.
     */
    void readXml(String fileName);

    /**
     * Writes specific content to an XML file. The content can be filtered by
     * specifying a range of chapters to be included in the output.
     *
     * @param fileName The path where the XML file will be saved.
     * @param startChapter The starting chapter number to write.
     * @param endChapter The ending chapter number to write.
     */
    void writeXml(String fileName, int startChapter, int endChapter);

    /**
     * Calculates various statistics based on the content of the XML file, such
     * as the number of chapters, paragraphs, sentences, and words.
     */
    void calculateStatistics();

}
