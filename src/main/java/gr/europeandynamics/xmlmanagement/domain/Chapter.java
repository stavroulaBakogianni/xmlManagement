package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class Chapter {

    private List<Paragraph> paragraphs;

    @XmlElement(name = "paragraph")
    List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }
}
