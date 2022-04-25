package br.com.levelupacademy.models.course;

import java.util.List;

public class CourseSimpleDTO {

    private String name;
    private Integer estimatedTimeInHours;

    public CourseSimpleDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
    }

    public String getName() {
        return name;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public static List<CourseSimpleDTO> toDTO(List<Course> courses) {
        return courses.stream().map(CourseSimpleDTO::new).toList();
    }
}
