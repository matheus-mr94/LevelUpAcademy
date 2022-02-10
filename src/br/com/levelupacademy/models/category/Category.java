package br.com.levelupacademy.models.category;

public class Category {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active = false;
    private int order;
    private String url;
    private String hexCode;


    public Category(String name, String code, String description, String studyGuide, int order, String url, String hexCode) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.order = order;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public String getHexCode() {
        return hexCode;
    }
}