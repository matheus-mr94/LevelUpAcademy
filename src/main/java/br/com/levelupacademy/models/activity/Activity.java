package br.com.levelupacademy.models.activity;

import br.com.levelupacademy.models.section.Section;

import javax.persistence.*;

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
        this.title = title;
        this.code = code;
        this.sequence = sequence;
        this.section = section;
        this.active = active;
    }

}
