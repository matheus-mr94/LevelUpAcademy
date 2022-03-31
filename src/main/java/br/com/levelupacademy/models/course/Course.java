package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.models.subcategory.Subcategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    private static final int MINIMUM_TIME_TO_FINISH = 1;
    private static final int MAXIMUM_TIME_TO_FINISH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @Column(name = "estimated_time_in_hours")
    private Integer estimatedTimeInHours;
    private boolean visible;
    private String target;
    private String instructor;
    @Column(columnDefinition = "TEXT")
    private String syllabus;
    @Column(name = "developed_skills", columnDefinition = "TEXT")
    private String developedSkills;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private Subcategory subcategory;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sectionList = new ArrayList<>();

    @Deprecated
    public Course() {
    }

    public Course(String name, String code, Integer estimatedTimeInHours, String target, boolean visible, String instructor, String syllabus, String developedSkills, Subcategory subcategory) {
        this.name = name;
        this.code = code;
        this.estimatedTimeInHours = estimatedTimeInHours;
        this.visible = visible;
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

    public String getSubcategoryName() {
        return subcategory.getName();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }



//    @Override
//    public String toString() {
//        return "Course{" +
//                "name='" + name + '\'' +
//                ", code='" + code + '\'' +
//                ", estimatedTime=" + estimatedTimeInHours +
//                ", visible=" + visible +
//                ", target='" + target + '\'' +
//                ", instructor='" + instructor + '\'' +
//                ", syllabus='" + syllabus + '\'' +
//                ", developedSkills='" + developedSkills + '\'' +
//                '}';
//    }


}
