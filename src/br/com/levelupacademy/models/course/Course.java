package br.com.levelupacademy.models.course;

import br.com.levelupacademy.validators.Validations;

public class Course {

    private String name;
    private String code;
    private Integer estimatedTime;
    private boolean visible = false;
    private String target;
    private String instructor;
    private String syllabus;
    private String developedSkills;

    public Course(String name, String code, Integer estimatedTime, String target, String instructor, String syllabus, String developedSkills) {
        Validations.cantBeEmptyOrNull(name,"name can't be empty or null");
        this.name = name;
        Validations.cantBeEmptyOrNull(code,"Code can't be empty or null");
        Validations.codeValidation(code,"Invalid characters");
        this.code = code;
        Validations.cantBeNull(estimatedTime, "Estimated time can't be empty or null");
        Validations.sizeValidation(estimatedTime, "estimated time should be between 1 and 20");
        this.estimatedTime = estimatedTime;
        this.target = target;
        Validations.cantBeEmptyOrNull(instructor,"Course must have an instructor name");
        this.instructor = instructor;
        this.syllabus = syllabus;
        this.developedSkills = developedSkills;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public String getTarget() {
        return target;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getDevelopedSkills() {
        return developedSkills;
    }
}
