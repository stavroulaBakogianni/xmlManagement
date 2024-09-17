package gr.europeandynamics.xmlmanagement.xmlmanipulationservice;

public interface XmlManipulator {

    void readXml(String fileName);

    void writeXml(String fileName, int startChapter, int endChapter);

    void calculateStatistics();

}
