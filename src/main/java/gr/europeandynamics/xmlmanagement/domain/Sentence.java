package gr.europeandynamics.xmlmanagement.domain;

import jakarta.xml.bind.annotation.XmlValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sentence {

    private String content;

    @XmlValue
    public String getContent() {
        return content;
    }
}
