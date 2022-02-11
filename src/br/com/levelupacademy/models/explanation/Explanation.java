package br.com.levelupacademy.models.explanation;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.validators.Validations;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String code, int order, Section section, String text) {
        super(title, code, order, section);
        Validations.cantBeEmptyOrNull(text, "Explanation should have a text, can't be empty or null");
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
