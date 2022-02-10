package br.com.levelupacademy.models.course;

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
        this.name = name;
        this.code = code;
        this.estimatedTime = estimatedTime;
        this.target = target;
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
