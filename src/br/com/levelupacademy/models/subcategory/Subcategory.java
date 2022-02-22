package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;

import static br.com.levelupacademy.validators.Validations.*;

public class Subcategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private Category category;

    public Subcategory(String name, String code, String description, String studyGuide, boolean active, int sequence, Category category) {
        cantBeEmptyOrNull(name,"Name can't be empty or null");
        codeValidation(code,"Invalid characters");
        objectIsNotNull(category, "Subcategory should have a category");
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + sequence +
                ", category=" + category +
                '}';
    }
}
