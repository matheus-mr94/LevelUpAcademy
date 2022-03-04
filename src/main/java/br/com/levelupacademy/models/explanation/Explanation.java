package br.com.levelupacademy.models.explanation;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String code, int sequence, Section section, String text) {
        super(title, code, sequence, section);
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
