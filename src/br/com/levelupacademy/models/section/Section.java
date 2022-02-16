package br.com.levelupacademy.models.section;

import br.com.levelupacademy.models.course.Course;

import static br.com.levelupacademy.validators.Validations.*;

public class Section {

    private String name;
    private String code;
    private int orderInSystem;
    private boolean active;
    private boolean exam;
    private Course course;

    public Section(String name, String code, int orderInSystem, Course course) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        this.name = name;
        codeValidation(code,"Invalid characters");
        this.code = code;
        this.orderInSystem = orderInSystem;
        objectIsNotNull(course, "Must have a course");
        this.course = course;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + orderInSystem +
                ", active=" + active +
                ", test=" + exam +
                ", course=" + course +
                '}';
    }
}
