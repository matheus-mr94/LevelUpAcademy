package br.com.levelupacademy.models.category;

import java.util.List;

public class CategorySimpleResponse {

    private final String name;
    private final String code;
    private final String active;

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
        return this.active;
    }

    public static List<CategorySimpleResponse> toDTO(List<Category> categories) {
        return categories.stream().map(CategorySimpleResponse::new).toList();
    }
}
