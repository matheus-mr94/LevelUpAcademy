package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Deprecated
    public Subcategory() {
    }

    public Subcategory(String name, String code, String description, String studyGuide, boolean active, int sequence, Category category) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public int getSequence() {
        return sequence;
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public String getCategoryName() {
        return category.getName();
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", studyGuide='" + studyGuide + '\'' +
                ", active=" + active +
                ", order=" + sequence +
                ", category=" + category +
                '}';
    }


}
