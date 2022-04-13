package br.com.levelupacademy.models.category.api;

import br.com.levelupacademy.models.course.Course;

import java.util.List;

public class CourseApiSimpleResponse {

    private final String name;
    private final String code;
    private final int estimatedTimeInHours;
    private final String developedSkills;

    @Deprecated
    public CourseApiSimpleResponse(String name, String code, int estimatedTimeInHours, String developedSkills) {
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.developedSkills = developedSkills;
    }

    public CourseApiSimpleResponse(Course course) {
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.developedSkills = course.getDevelopedSkills();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public static List<CourseApiSimpleResponse> toDTO(List<Course> courses) {
        return courses.stream().map(CourseApiSimpleResponse::new).toList();
    }
}
