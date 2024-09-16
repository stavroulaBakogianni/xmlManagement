package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Paragraph {

    private List<Sentence> sentences = new ArrayList<>();

    @XmlElement(name = "sentence")
    public List<Sentence> getSentences() {
        return this.sentences;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public int getNumberofSentences() {
        return this.sentences.size();
    }
}
