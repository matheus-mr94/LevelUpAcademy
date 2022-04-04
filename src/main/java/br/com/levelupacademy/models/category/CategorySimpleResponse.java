package br.com.levelupacademy.models.category;

public class CategorySimpleResponse {

    private String name;
    private String code;
    private String active;

    public CategorySimpleResponse(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.active = category.getStatus();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getActive() {
        return active;
    }
}
