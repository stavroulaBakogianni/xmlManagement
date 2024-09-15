package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Data;

@Data
public class Paragraph {

    private List<Sentence> sentences;

    @XmlElement(name = "sentence")
    List<Sentence> getSentences() {
        return this.sentences;
    }
}
