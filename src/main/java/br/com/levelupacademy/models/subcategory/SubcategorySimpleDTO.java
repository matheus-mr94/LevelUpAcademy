package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.course.CourseSimpleDTO;

import java.util.List;

public class SubcategorySimpleDTO {

    private String name;
    private String code;
    private List<CourseSimpleDTO> activeCourses;

    public SubcategorySimpleDTO(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.activeCourses = CourseSimpleDTO.toDTO(subcategory.getActiveCourses());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CourseSimpleDTO> getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(List<CourseSimpleDTO> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public static List<SubcategorySimpleDTO> toDTO(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategorySimpleDTO::new).toList();
    }
}
