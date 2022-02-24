package br.com.levelupacademy.models.activity;

import br.com.levelupacademy.models.section.Section;

import static br.com.levelupacademy.validators.Validations.*;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active;
    private int sequence;
    private Section section;

    public Activity(String title, String code, int sequence, Section section) {
        cantBeEmptyOrNull(title,"Title can't be empty or null");
        codeValidation(code,"Invalid characters");
        objectIsNotNull(section,"Activity must have a section");
        this.title = title;
        this.code = code;
        this.sequence = sequence;
        this.section = section;
    }

}
