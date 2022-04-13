package br.com.levelupacademy.models.category.api;

import br.com.levelupacademy.models.subcategory.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class SubcategorySimpleResponse {

    private final String name;
    private final String code;
    private final String studyGuide;
    private List<CourseApiSimpleResponse> courseResponseList = new ArrayList<>();

    @Deprecated
    public SubcategorySimpleResponse(String name, String code, String studyGuide) {
        this.name = name;
        this.code = code;
        this.studyGuide = studyGuide;
    }

    public SubcategorySimpleResponse(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.studyGuide = subcategory.getStudyGuide();
        this.courseResponseList = CourseApiSimpleResponse.toDTO(subcategory.getActiveCourses());
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public List<CourseApiSimpleResponse> getCourseResponseList() {
        return courseResponseList;
    }


    public static List<SubcategorySimpleResponse> toDTO(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategorySimpleResponse::new).toList();
    }
}
