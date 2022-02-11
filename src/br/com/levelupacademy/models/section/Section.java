package br.com.levelupacademy.models.section;

import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.validators.Validations;

public class Section {

    private String name;
    private String code;
    private int order;
    private boolean active = false;
    private boolean test = false;
    private Course course;

    public Section(String name, String code, int order, Course course) {
        Validations.cantBeEmptyOrNull(name,"name can't be empty or null");
        this.name = name;
        Validations.codeValidation(code,"Invalid characters");
        this.code = code;
        this.order = order;
        Validations.objectValidation(course, "Must have a course");
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getOrder() {
        return order;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isTest() {
        return test;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + order +
                ", active=" + active +
                ", test=" + test +
                ", course=" + course +
                '}';
    }
}
