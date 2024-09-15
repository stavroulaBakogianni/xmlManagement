package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement(name = "book")
public class Book {

    List<Chapter> chapters;
    Statistics statistics;

    @XmlElement(name = "chapter")
    public List<Chapter> getChapters() {
        return this.chapters;
    }

    @XmlElement(name = "statistics")
    public Statistics getStatistics() {
        return this.statistics;
    }
}
