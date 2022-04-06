package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.course.CourseSimpleResponse;

import java.util.ArrayList;
import java.util.List;

public class SubcategorySimpleResponse {

    private String name;
    private String code;
    private String studyGuide;
    private List<CourseSimpleResponse> courseResponseList = new ArrayList<>();

    @Deprecated
    public SubcategorySimpleResponse() {
    }

    public SubcategorySimpleResponse(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.studyGuide = subcategory.getStudyGuide();
        this.courseResponseList = CourseSimpleResponse.toDTO(subcategory.getActiveCourses());
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

    public List<CourseSimpleResponse> getCourseResponseList() {
        return courseResponseList;
    }


    public static List<SubcategorySimpleResponse> toDTO(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategorySimpleResponse::new).toList();
    }
}
