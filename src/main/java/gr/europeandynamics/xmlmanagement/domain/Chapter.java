package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Chapter {

    private List<Paragraph> paragraphs = new ArrayList<>();;

    @XmlElement(name = "paragraph")
    public List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    public void addParagraph(Paragraph paragraph) {
        paragraphs.add(paragraph);
    }

    public int getNumberofParagraphs() {
        return this.paragraphs.size();
    }

}
