package br.com.levelupacademy.models.explanation;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.section.Section;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String code, Section section, String text) {
        super(title, code, section);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
