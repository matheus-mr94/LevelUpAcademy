package br.com.levelupacademy.models.course;

import lombok.Getter;

import java.util.List;

@Getter
public class CourseSimpleDTO {

    private String name;
    private Integer estimatedTimeInHours;

    public CourseSimpleDTO(Course course) {
        this.name = course.getName();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
    }

    public static List<CourseSimpleDTO> toDTO(List<Course> courses) {
        return courses.stream().map(CourseSimpleDTO::new).toList();
    }
}
