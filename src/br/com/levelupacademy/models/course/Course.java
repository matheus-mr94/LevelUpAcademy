package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.subcategory.Subcategory;

import static br.com.levelupacademy.validators.Validations.*;

public class Course {

    private static final int MINIMUM_TIME_TO_FINISH = 1;
    private static final int MAXIMUM_TIME_TO_FINISH = 20;

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private boolean visible;
    private String target;
    private String instructor;
    private String syllabus;
    private String developedSkills;
    private Subcategory subcategory;

    public Course(String name, String code, Integer estimatedTimeInHours, String target, String instructor, String syllabus, String developedSkills, Subcategory subcategory) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        this.name = name;
        cantBeEmptyOrNull(code,"Code can't be empty or null");
        codeValidation(code,"Invalid characters");
        this.code = code;
        cantBeNull(estimatedTimeInHours, "Estimated time can't be empty or null");
        sizeValidation(estimatedTimeInHours, MINIMUM_TIME_TO_FINISH, MAXIMUM_TIME_TO_FINISH,"estimated time should be between 1 and 20");
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.target = target;
        cantBeEmptyOrNull(instructor,"Course must have an instructor name");
        this.instructor = instructor;
        this.syllabus = syllabus;
        this.developedSkills = developedSkills;
        objectIsNotNull(subcategory, "Subcategory can't be null");
        this.subcategory = subcategory;
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
