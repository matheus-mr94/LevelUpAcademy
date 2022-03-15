package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.subcategory.Subcategory;

import static br.com.levelupacademy.validators.Validations.*;

public class Course {

    private static final int MINIMUM_TIME_TO_FINISH = 1;
    private static final int MAXIMUM_TIME_TO_FINISH = 20;

    private Long id;
    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private boolean visible;
    private String target;
    private String instructor;
    private String syllabus;
    private String developedSkills;
    private Subcategory subcategory;

    public Course(String name, String code, Integer estimatedTimeInHours, String target, boolean visibile, String instructor, String syllabus, String developedSkills, Subcategory subcategory) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        cantBeEmptyOrNull(code,"Code can't be empty or null");
        codeValidation(code,"Invalid characters");
        cantBeNull(estimatedTimeInHours, "Estimated time can't be empty or null");
        sizeValidation(estimatedTimeInHours, MINIMUM_TIME_TO_FINISH, MAXIMUM_TIME_TO_FINISH,"estimated time should be between 1 and 20");
        cantBeEmptyOrNull(instructor,"Course must have an instructor name");
        objectIsNotNull(subcategory, "Subcategory can't be null");
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.visible = visibile;
        this.target = target;
        this.instructor = instructor;
        this.syllabus = syllabus;
        this.developedSkills = developedSkills;
        this.subcategory = subcategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTimeInHours() {
        return estimatedTimeInHours;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public String getCategoryCode() {
        return subcategory.getCategoryCode();
    }

    public boolean isVisible() {
        return visible;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTarget() {
        return target;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }

    public String getSubcategoryCode() {
        return subcategory.getCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", estimatedTime=" + estimatedTimeInHours +
                ", visible=" + visible +
                ", target='" + target + '\'' +
                ", instructor='" + instructor + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", developedSkills='" + developedSkills + '\'' +
                '}';
    }
}
