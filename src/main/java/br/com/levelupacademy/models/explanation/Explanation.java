package br.com.levelupacademy.models.explanation;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import static org.springframework.util.Assert.hasText;

@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String code, int sequence, Section section, boolean active, String text) {
        super(title, code, sequence, section, active);
        hasText(text, "Text can't be empty or null");
        this.text = text;
    }
}
