package br.com.levelupacademy.dto;

public class CourseDTO {

    private final Long id;
    private final String courseName;
    private final int  estimatedTimeInHours;
    private final Long subcategoryId;
    private final String subcategoryName;

    public CourseDTO(Long id, String courseName, int estimatedTimeInHours, Long subcategoryId, String subcategoryName) {
        this.id = id;
        this.courseName = courseName;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.subcategoryId = subcategoryId;
        this.subcategoryName = subcategoryName;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }
}
