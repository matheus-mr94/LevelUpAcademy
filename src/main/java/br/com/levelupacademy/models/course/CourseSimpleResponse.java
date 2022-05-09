package br.com.levelupacademy.models.course;

import lombok.Getter;

import java.util.List;

@Getter
public class CourseSimpleResponse {

    private final String name;
    private final String code;
    private final String active;

    public CourseSimpleResponse(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.active = course.isVisible() ? "Ativo" : "Inativo";
    }

    public static List<CourseSimpleResponse> toDTO(List<Course> courses) {
        return courses.stream().map(CourseSimpleResponse::new).toList();
    }
}
