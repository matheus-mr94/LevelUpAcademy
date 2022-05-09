package br.com.levelupacademy.models.category;

import lombok.Getter;

import java.util.List;

@Getter
public class CategorySimpleResponse {

    private final Long id;
    private final String name;
    private final String code;
    private final String active;

    public CategorySimpleResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.active = category.isActive() ? "Ativa" : "Inativa";
    }

    public static List<CategorySimpleResponse> toDTO(List<Category> categories) {
        return categories.stream().map(CategorySimpleResponse::new).toList();
    }
}
