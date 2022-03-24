package br.com.levelupacademy.util.builder;

import br.com.levelupacademy.models.category.Category;

public class CategoryBuilder {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private String urlImage;
    private String hexCode;

    public CategoryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder withStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
        return this;
    }

    public CategoryBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public CategoryBuilder withSequence(int sequence) {
        this.sequence = sequence;
        return this;
    }

    public CategoryBuilder withUrlImage(String urlImage) {
        this.urlImage = urlImage;
        return this;
    }

    public CategoryBuilder withHexCode(String hexCode) {
        this.hexCode = hexCode;
        return this;
    }

    public Category create() {
        return new Category(name, code, description, studyGuide, active, sequence, urlImage, hexCode);
    }
}
