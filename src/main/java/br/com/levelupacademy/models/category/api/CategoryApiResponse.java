package br.com.levelupacademy.models.category.api;

import br.com.levelupacademy.models.category.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryApiResponse {

    private final String name;
    private final String code;
    private final String description;
    private final String studyGuide;
    private final boolean active;
    private final int sequence;
    private final String urlImage;
    private final String hexCode;
    private final int totalOfCourses;
    private  List<SubcategorySimpleResponse> subcategoryList = new ArrayList<>();

    @Deprecated
    public CategoryApiResponse(String name, String code, String description, String studyGuide, boolean active, int sequence, String urlImage, String hexCode, int totalOfCourses) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.urlImage = urlImage;
        this.hexCode = hexCode;
        this.totalOfCourses = totalOfCourses;
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
