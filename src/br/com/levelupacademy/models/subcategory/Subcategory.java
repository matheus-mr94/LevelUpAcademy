package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.validators.Validations;

public class Subcategory {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private Category category;

    public Subcategory(String name, String code, String description, String studyGuide, boolean active, int order, Category category) {
        Validations.cantBeEmptyOrNull(name,"Name can't be empty or null");
        this.name = name;
        Validations.codeValidation(code,"Invalid characters");
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
        Validations.objectValidation(category, "Subcategory should have a category");
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public int getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
    }
}
