package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.*;

@Getter
@NoArgsConstructor
@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
    @Column(name = "study_guide")
    private String studyGuide;
    private boolean active;
    private int sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "subcategory", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public Subcategory(String name, String code, String description, String studyGuide, boolean active, int sequence, Category category) {
        hasText(name, "name can't be empty or null");
        hasText(code, "name can't be empty or null");
        isTrue(code.matches("[a-z0-9-]+"),"Invalid characters");
        notNull(category, "Subcategory should have a category");
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.category = category;
    }

    public Subcategory(Long id, String name, String code, String description, String studyGuide, boolean active, int sequence, Category category) {
        this(name, code, description, studyGuide, active, sequence, category);
        this.id = id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public String getCategoryName() {
        return category.getName();
    }

    public List<Course> getActiveCourses() {
        return  courses.stream().filter(Course::isVisible).toList();
    }

    public Long getCategoryId() {
        return category.getId();
    }

    public void disable() {
        this.active = false;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void update(SubcategoryUpdateRequest subcategoryUpdateRequest, Category category) {
        this.name = subcategoryUpdateRequest.getName();
        this.code = subcategoryUpdateRequest.getCode();
        this.description = subcategoryUpdateRequest.getDescription();
        this.studyGuide = subcategoryUpdateRequest.getStudyGuide();
        this.active = subcategoryUpdateRequest.isActive();
        this.sequence = subcategoryUpdateRequest.getSequence();
        this.category = category;
    }
}
