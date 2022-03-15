package br.com.levelupacademy.models.category;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;
import static br.com.levelupacademy.validators.Validations.codeValidation;

public class Category {

    private Long id;
    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private String urlImage;
    private String hexCode;


    public Category(String name, String code, String description, String studyGuide, boolean active, int sequence, String urlImage , String hexCode) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        cantBeEmptyOrNull(code,"Code can't be empty or null");
        codeValidation(code,"Invalid characters");
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.urlImage = urlImage ;
        this.hexCode = hexCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getHexCode() {
        return hexCode;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + sequence +
                ", urlImage='" + urlImage + '\'' +
                ", hexCode='" + hexCode + '\'' +
                '}';
    }
}