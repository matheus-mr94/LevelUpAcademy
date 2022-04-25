package br.com.levelupacademy.models.category;

import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.subcategory.SubcategorySimpleDTO;

import java.util.List;

public class CategoryDTOResponse {

    private String name;
    private String urlImage;
    private String code;
    private List<SubcategorySimpleDTO> subcategories;

    public CategoryDTOResponse(Category category) {
        this.name = category.getName();
        this.urlImage = category.getUrlImage();
        this.code = category.getCode();
        this.subcategories = SubcategorySimpleDTO.toDTO(category.getActiveSubcategories());
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public List<SubcategorySimpleDTO> getSubcategories() {
        return subcategories;
    }

    public String getCode() {
        return code;
    }

    public void setSubcategories(List<SubcategorySimpleDTO> subcategories) {
        this.subcategories = subcategories;
    }

    public static List<CategoryDTOResponse> toDTO(List<Category> categories) {
        return categories.stream().map(CategoryDTOResponse::new).toList();
    }
}
