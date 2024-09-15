package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
@XmlType(propOrder = {"numberOfParagraphs", "numberOfSentences", "numberOfWords", "numberOfDistinctWords", "dateTime", "author", "applicationName"})
public class Statistics {

    private final int numberOfParagraphs;
    private final int numberOfSentences;
    private final int numberOfWords;
    private final int numberOfDistinctWords;
    private final LocalDateTime dateTime;
    private final String author;
    private final String applicationName;

    public Statistics() {
        this.numberOfParagraphs = 0;
        this.numberOfSentences = 0;
        this.numberOfWords = 0;
        this.numberOfDistinctWords = 0;
        this.dateTime = LocalDateTime.now();
        this.author = "";
        this.applicationName = "";
    }

    public Statistics(int numberOfParagraphs, int numberOfSentences, int numberOfWords, int numberOfDistinctWords, LocalDateTime dateTime, String author, String applicationName) {
        this.numberOfParagraphs = numberOfParagraphs;
        this.numberOfSentences = numberOfSentences;
        this.numberOfWords = numberOfWords;
        this.numberOfDistinctWords = numberOfDistinctWords;
        this.dateTime = dateTime;
        this.author = author;
        this.applicationName = applicationName;
    }

    @XmlElement(name = "numberOfParagraphs")
    public int getNumberOfParagraphs() {
        return numberOfParagraphs;
    }

    @XmlElement(name = "numberOfSentences")
    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    @XmlElement(name = "numberOfWords")
    public int getNumberOfWords() {
        return numberOfWords;
    }

    @XmlElement(name = "numberOfDistinctWords")
    public int getNumberOfDistinctWords() {
        return numberOfDistinctWords;
    }

    @XmlElement(name = "dateTime")
    public String getDateTime() {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_DATE_TIME) : null;
    }

    @XmlElement(name = "author")
    public String getAuthor() {
        return author;
    }

    @XmlElement(name = "applicationName")
    public String getApplicationName() {
        return applicationName;
    }
}
