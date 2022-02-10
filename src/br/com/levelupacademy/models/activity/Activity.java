package br.com.levelupacademy.models.activity;

import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.validators.Validations;

public abstract class Activity {

    private String title;
    private String code;
    private boolean active = false;
    private int order;
    private Section section;

    public Activity(String title, String code, Section section) {
        Validations.cantBeEmptyOrNull(title,"title can't be empty or null");
        this.title = title;
        Validations.codeValidation(code,"Invalid characters");
        this.code = code;
        this.section = section;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public int getOrder() {
        return order;
    }

    public Section getSection() {
        return section;
    }
}
