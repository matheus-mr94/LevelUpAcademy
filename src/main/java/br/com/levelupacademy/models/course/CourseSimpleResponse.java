package br.com.levelupacademy.models.course;

import java.util.List;

public class CourseSimpleResponse {

    private String name;
    private String code;
    private int estimatedTimeInHours;
    private String developedSkills;

    @Deprecated
    public CourseSimpleResponse() {
    }

    public CourseSimpleResponse(Course course) {
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

    public static List<CourseSimpleResponse> toDTO(List<Course> courses) {
        return courses.stream().map(CourseSimpleResponse::new).toList();
    }
}
