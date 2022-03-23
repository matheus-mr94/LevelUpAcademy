package br.com.levelupacademy.util.builder;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;

public class SubcategoryBuilder {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private Category category;


    public SubcategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SubcategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public SubcategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public SubcategoryBuilder withStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
        return this;
    }

    public SubcategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public SubcategoryBuilder withSequence(int sequence) {
        this.sequence = sequence;
        return this;
    }

    public SubcategoryBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public Subcategory create() {
        return new Subcategory(name, code, description, studyGuide, active, sequence, category);
    }
}
