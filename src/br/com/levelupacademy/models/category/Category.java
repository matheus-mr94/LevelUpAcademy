package br.com.levelupacademy.models.category;

import static br.com.levelupacademy.validators.Validations.cantBeEmptyOrNull;
import static br.com.levelupacademy.validators.Validations.codeValidation;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int orderInSystem;
    private String urlImage;
    private String hexCode;


    public Category(String name, String code, String description, String studyGuide, int orderInSystem, String urlImage , String hexCode) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        this.name = name;
        cantBeEmptyOrNull(code,"Code can't be empty or null");
        codeValidation(code,"Invalid characters");
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.orderInSystem = orderInSystem;
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

    public int getOrderInSystem() {
        return orderInSystem;
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
                ", order=" + orderInSystem +
                ", urlImage='" + urlImage + '\'' +
                ", hexCode='" + hexCode + '\'' +
                '}';
    }
}