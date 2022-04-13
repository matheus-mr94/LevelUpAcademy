package br.com.levelupacademy.models.course;

import java.util.List;

public class CourseSimpleResponse {

    private final String name;
    private final String code;
    private final String active;

    public CourseSimpleResponse(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.active = course.getStatus();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getActive() {
        return active;
    }

    public static List<CourseSimpleResponse> toDTO(List<Course> courses) {
        return courses.stream().map(CourseSimpleResponse::new).toList();
    }


}
