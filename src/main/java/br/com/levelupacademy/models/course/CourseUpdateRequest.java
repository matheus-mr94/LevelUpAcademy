package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.subcategory.Subcategory;

import javax.validation.constraints.*;

public class CourseUpdateRequest {

    private Long id;
    @NotBlank(message = "{name.emptyOrNull}")
    private String name;
    @NotBlank(message = "{code.emptyOrNull}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{code.invalidCharacters}")
    private String code;
    @NotNull(message = "{size.validation}")
    @Min(1)
    @Max(20)
    private Integer estimatedTimeInHours;
    private boolean visible;
    private String target;
    @NotBlank(message = "{instructor.emptyOrNull}")
    private String instructor;
    private String syllabus;
    private String developedSkills;
    @NotNull(message = "{subcategory.emptyOrNull}")
    private Long subcategoryId;
    private String subcategoryCode;
    private String categoryCode;

    @Deprecated
    public CourseUpdateRequest() {
    }

    public CourseUpdateRequest(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.code = course.getCode();
        this.estimatedTimeInHours = course.getEstimatedTimeInHours();
        this.visible = course.isVisible();
        this.target = course.getTarget();
        this.instructor = course.getInstructor();
        this.syllabus = course.getSyllabus();
        this.developedSkills = course.getDevelopedSkills();
        this.subcategoryId = course.getSubcategoryId();
        this.subcategoryCode = course.getSubcategoryCode();
        this.categoryCode = course.getCategoryCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public void setEstimatedTimeInHours(Integer estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public void setDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
    }

    public String getSubcategoryCode() {
        return subcategoryCode;
    }

    public void setSubcategoryCode(String subcategoryCode) {
        this.subcategoryCode = subcategoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public Course toEntity(Subcategory subcategory) {
        return new Course(name, code, estimatedTimeInHours, target, visible, instructor,
                syllabus, developedSkills, subcategory);
    }
}
