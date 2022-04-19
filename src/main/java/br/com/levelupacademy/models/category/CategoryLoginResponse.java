package br.com.levelupacademy.models.category;

import br.com.levelupacademy.models.subcategory.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryLoginResponse {

    private String name;
    private String urlImage;
    private String code;
    private List<Subcategory> subcategories;

    public CategoryLoginResponse(Category category) {
        this.name = category.getName();
        this.urlImage = category.getUrlImage();
        this.code = category.getCode();
        this.subcategories = category.getActiveSubcategories();
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public String getCode() {
        return code;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public static List<CategoryLoginResponse> toDTO(List<Category> categories) {
        return categories.stream().map(CategoryLoginResponse::new).toList();
    }
}
