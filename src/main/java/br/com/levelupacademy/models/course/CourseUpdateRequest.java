package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.subcategory.Subcategory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
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

    public Course toEntity(Subcategory subcategory) {
        return new Course(name, code, estimatedTimeInHours, target, visible, instructor,
                syllabus, developedSkills, subcategory);
    }
}
