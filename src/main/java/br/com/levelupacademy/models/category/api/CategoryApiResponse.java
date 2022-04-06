package br.com.levelupacademy.models.category.api;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;
import br.com.levelupacademy.models.subcategory.SubcategorySimpleResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryApiResponse {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private String urlImage;
    private String hexCode;
    private int totalOfCourses;
    private List<SubcategorySimpleResponse> subcategoryList = new ArrayList<>();

    @Deprecated
    public CategoryApiResponse() {
    }

    public CategoryApiResponse(Category category) {
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.sequence = category.getSequence();
        this.urlImage = category.getUrlImage();
        this.hexCode = category.getHexCode();
        this.totalOfCourses =  category.countCourses();
        this.subcategoryList = SubcategorySimpleResponse.toDTO(category.getActiveSubcategories());
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

    public int getSequence() {
        return sequence;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getHexCode() {
        return hexCode;
    }

    public int getTotalOfCourses() {
        return totalOfCourses;
    }

    public List<SubcategorySimpleResponse> getSubcategoryList() {
        return subcategoryList;
    }

    public static List<CategoryApiResponse> toDTO(List<Category> categories) {
        return categories.stream().map(CategoryApiResponse::new).toList();
    }
}
