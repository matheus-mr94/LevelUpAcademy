package br.com.levelupacademy.models.section;

import br.com.levelupacademy.models.course.Course;

import static br.com.levelupacademy.validators.Validations.*;

public class Section {

    private String name;
    private String code;
    private int sequence;
    private boolean active;
    private boolean exam;
    private Course course;

    public Section(String name, String code, int sequence, Course course) {
        cantBeEmptyOrNull(name,"name can't be empty or null");
        codeValidation(code,"Invalid characters");
        objectIsNotNull(course, "Must have a course");
        this.name = name;
        this.code = code;
        this.sequence = sequence;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", order=" + sequence +
                ", active=" + active +
                ", test=" + exam +
                ", course=" + course +
                '}';
    }
}
