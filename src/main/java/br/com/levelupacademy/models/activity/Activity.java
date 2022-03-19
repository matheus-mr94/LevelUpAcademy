package br.com.levelupacademy.models.activity;

import br.com.levelupacademy.models.section.Section;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static br.com.levelupacademy.validators.Validations.*;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Activity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private boolean active;
    private int sequence;
    @ManyToOne
    private Section section;

    public Activity() {
    }

    public Activity(String title, String code, int sequence, Section section, boolean active) {
        cantBeEmptyOrNull(title,"Title can't be empty or null");
        codeValidation(code,"Invalid characters");
//        objectIsNotNull(section,"Activity must have a section");
        this.title = title;
        this.code = code;
        this.sequence = sequence;
        this.section = section;
        this.active = active;
    }

}
