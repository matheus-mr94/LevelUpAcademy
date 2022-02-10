package br.com.levelupacademy.models.section;

import br.com.levelupacademy.models.course.Course;

public class Section {

    private String sectionName;
    private String code;
    private int order;
    private boolean active = false;
    private boolean test = false;
    private Course course;

    public Section(String sectionName, String code, int order, Course course) {
        this.sectionName = sectionName;
        this.code = code;
        this.order = order;
        this.course = course;
    }

    public String getSectionName() {
        return sectionName;
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
}
