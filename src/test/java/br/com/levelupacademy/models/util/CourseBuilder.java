package br.com.levelupacademy.models.util;

import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;

public class CourseBuilder {

    private String name;
    private String code;
    private Integer estimatedTimeInHours;
    private boolean visible;
    private String target;
    private String instructor;
    private String syllabus;
    private String developedSkills;
    private Subcategory subcategory;

    public CourseBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder withEstimatedTimeInHours(int estimatedTimeInHours) {
        this.estimatedTimeInHours = estimatedTimeInHours;
        return this;
    }

    public CourseBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public CourseBuilder withTarget(String target) {
        this.target = target;
        return this;
    }

    public CourseBuilder withInstructor(String instructor) {
        this.instructor = instructor;
        return this;
    }

    public CourseBuilder withSyllabus(String syllabus) {
        this.syllabus = syllabus;
        return this;
    }

    public CourseBuilder withDevelopedSkills(String developedSkills) {
        this.developedSkills = developedSkills;
        return this;
    }

    public CourseBuilder withSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public Course create () {
        return  new Course(name, code, estimatedTimeInHours, target, visible,
                instructor, syllabus, developedSkills, subcategory);
    }
}
