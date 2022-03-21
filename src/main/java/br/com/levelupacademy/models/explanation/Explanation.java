package br.com.levelupacademy.models.explanation;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

import javax.persistence.*;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Explanation extends Activity {

    private String text;

    public Explanation() {
    }

    public Explanation(String title, String code, int sequence, Section section, boolean active, String text) {
        super(title, code, sequence, section, active);
        cantBeEmptyOrNull(text, "Explanation should have a text, can't be empty or null");
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "text='" + text + '\'' +
                '}';
    }
}
