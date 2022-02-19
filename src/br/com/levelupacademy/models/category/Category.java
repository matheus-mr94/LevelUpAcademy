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


    public Category(String name, String code, String description, String studyGuide,boolean active, int orderInSystem, String urlImage , String hexCode) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        cantBeEmptyOrNull(code,"Code can't be empty or null");
        codeValidation(code,"Invalid characters");
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.orderInSystem = orderInSystem;
        this.urlImage = urlImage ;
        this.hexCode = hexCode;
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