package br.com.levelupacademy.models.explanation;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "activity_id")
public class Explanation extends Activity {

    private String text;

    @Deprecated
    public Explanation() {
    }

    public Explanation(String title, String code, int sequence, Section section, boolean active, String text) {
        super(title, code, sequence, section, active);
        this.text = text;
    }

    @Override
    public String toString() {
        return "Explanation{" +
                "text='" + text + '\'' +
                '}';
    }
}
