package br.com.levelupacademy.models.category;

import br.com.levelupacademy.models.subcategory.Subcategory;

import java.util.List;

public class CategoryPageResponse {

    private String code;
    private String name;
    private String urlImage;
    private List<Subcategory> subcategories;

    public CategoryPageResponse(Category category) {
        this.code = category.getCode();
        this.name = category.getName();
        this.urlImage = category.getUrlImage();
        this.subcategories = category.getActiveSubcategories();
    }

    public String getCode() {
        return code;
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
}
