package br.com.levelupacademy.dto;

public class CourseDTO {

    private Long id;
    private String courseName;
    private int  estimatedTimeInHours;
    private Long subcategoryId;
    private String subcategoryName;

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
