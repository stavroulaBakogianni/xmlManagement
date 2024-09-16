package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@XmlRootElement(name = "book")
public class Book {

    List<Chapter> chapters = new ArrayList<>();
    Statistics statistics;

    @XmlElement(name = "chapter")
    public List<Chapter> getChapters() {
        return this.chapters;
    }

    @XmlElement(name = "statistics")
    public Statistics getStatistics() {
        return this.statistics;
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    public int getNumberofChapters() {
        return this.chapters.size();
    }
}
