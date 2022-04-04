package br.com.levelupacademy.models.category;

import javax.validation.constraints.*;

public class CategoryCreateRequest {

    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotNull(message = "Code can't be null")
    @NotEmpty(message = "Code can't be empty")
    @Pattern(regexp = "[a-z0-9-]+", message = "Invalid characters")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private String urlImage;
    private String hexCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public Category toEntity() {
        return new Category(name, code, description, studyGuide, active, sequence, urlImage, hexCode);
    }
}
