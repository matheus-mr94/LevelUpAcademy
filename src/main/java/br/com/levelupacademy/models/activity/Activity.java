package br.com.levelupacademy.models.activity;

import br.com.levelupacademy.models.section.Section;
import org.springframework.util.Assert;

import javax.persistence.*;

import static org.springframework.util.Assert.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private boolean active;
    private int sequence;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @Deprecated
    public Activity() {
    }

    public Activity(String title, String code, int sequence, Section section, boolean active) {
        hasText(title, "Title can't be empty or null");
        hasText(code, "name can't be empty or null");
        isTrue(code.matches("[a-z0-9-]+"),"Invalid characters");
        notNull(section,"Activity must have a section");
        this.title = title;
        this.code = code;
        this.sequence = sequence;
        this.section = section;
        this.active = active;
    }
}
