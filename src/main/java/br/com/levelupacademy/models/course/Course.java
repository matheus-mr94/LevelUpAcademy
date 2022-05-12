package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.section.Section;
import br.com.levelupacademy.models.subcategory.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.*;

@Getter
@NoArgsConstructor
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

    public Course(String name, String code, Integer estimatedTimeInHours, String target, boolean visible, String instructor, String syllabus, String developedSkills, Subcategory subcategory) {
        hasText(name, "name can't be empty or null");
        hasText(code, "name can't be empty or null");
        isTrue(code.matches("[a-z0-9-]+"), "Invalid characters");
        isTrue(estimatedTimeInHours != null, "Estimated time can't be empty or null");
        isTrue(estimatedTimeInHours >= MINIMUM_TIME_TO_FINISH && estimatedTimeInHours <= MAXIMUM_TIME_TO_FINISH,
                "estimated time should be between 1 and 20");
        hasText(instructor, "instructor can't be empty or null");
        notNull(subcategory, "Subcategory can't be null");
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

    public String getCategoryCode() {
        return subcategory.getCategoryCode();
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

    public Long getSubcategoryId() {
        return subcategory.getId();
    }

    public void update(CourseUpdateRequest courseUpdateRequest, Subcategory subcategory) {
        this.name = courseUpdateRequest.getName();
        this.code = courseUpdateRequest.getCode();
        this.estimatedTimeInHours = courseUpdateRequest.getEstimatedTimeInHours();
        this.visible = courseUpdateRequest.isVisible();
        this.target = courseUpdateRequest.getTarget();
        this.instructor = courseUpdateRequest.getInstructor();
        this.syllabus = courseUpdateRequest.getSyllabus();
        this.developedSkills = courseUpdateRequest.getDevelopedSkills();
        this.subcategory = subcategory;
    }
}
