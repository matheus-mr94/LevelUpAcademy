package br.com.levelupacademy.models.category;

import br.com.levelupacademy.validators.Validations;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active = false;
    private int order;
    private String urlImage;
    private String hexCode;


    public Category(String name, String code, String description, String studyGuide, int order, String urlImage , String hexCode) {
        Validations.cantBeEmptyOrNull(name,"name can't be empty or null");
        this.name = name;
        Validations.cantBeEmptyOrNull(code,"Code can't be empty or null");
        Validations.codeValidation(code,"Invalid characters");
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.order = order;
        this.urlImage = urlImage ;
        this.hexCode = hexCode;
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

    public String getUrlImage() {
        return urlImage;
    }

    public String getHexCode() {
        return hexCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + order +
                ", urlImage='" + urlImage + '\'' +
                ", hexCode='" + hexCode + '\'' +
                '}';
    }
}