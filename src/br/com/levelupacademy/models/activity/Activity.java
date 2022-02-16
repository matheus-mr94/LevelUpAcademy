package br.com.levelupacademy.models.activity;

import br.com.levelupacademy.models.section.Section;

import static br.com.levelupacademy.validators.Validations.*;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active;
    private int order;// TODO alterar o nome
    private Section section;

    public Activity(String title, String code, int order, Section section) {
        cantBeEmptyOrNull(title,"Title can't be empty or null");
        this.title = title;
        codeValidation(code,"Invalid characters");
        this.code = code;
        this.order = order;
        objectIsNotNull(section,"Activity must have a section");
        this.section = section;
    }

}
