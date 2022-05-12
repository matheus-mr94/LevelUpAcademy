package br.com.levelupacademy.models.section;

import br.com.levelupacademy.models.activity.Activity;
import br.com.levelupacademy.models.course.Course;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.*;

@NoArgsConstructor
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private int sequence;
    private boolean active;
    private boolean exam;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    List<Activity> activities = new ArrayList<>();

    public Section(String name, String code, int sequence, Course course) {
        hasText(name, "name can't be empty or null");
        hasText(code, "name can't be empty or null");
        isTrue(code.matches("[a-z0-9-]+"),"Invalid characters");
        notNull(course, "Must have a course");
        this.name = name;
        this.code = code;
        this.sequence = sequence;
        this.course = course;
    }
}
