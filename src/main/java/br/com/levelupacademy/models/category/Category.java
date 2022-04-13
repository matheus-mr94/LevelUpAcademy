package br.com.levelupacademy.models.category;

import br.com.levelupacademy.models.subcategory.Subcategory;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;

@Entity
public class Category {

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
    @Column(name = "url_image")
    private String urlImage;
    @Column(name = "hex_code")
    private String hexCode;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subcategories = new ArrayList<>();

    @Deprecated
    public Category() {
    }

    public Category(String name, String code, String description, String studyGuide, boolean active, int sequence, String urlImage , String hexCode) {
        hasText(name, "name can't be empty or null");
        hasText(code, "name can't be empty or null");
        isTrue(code.matches("[a-z0-9-]+"),"Invalid characters");
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.urlImage = urlImage ;
        this.hexCode = hexCode;
    }

    public Category(Long id, String name, String code, String description, String studyGuide, boolean active, int sequence, String urlImage, String hexCode) {
        hasText(name, "name can't be empty or null");
        hasText(code, "name can't be empty or null");
        isTrue(code.matches("[a-z0-9-]+"),"Invalid characters");
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.urlImage = urlImage;
        this.hexCode = hexCode;
    }

    public void toggleStatus() {
        this.active = !isActive();
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

    public String getStudyGuide() {
        return studyGuide;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getHexCode() {
        return hexCode;
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

    public String getStatus() {
        return isActive() ? "Ativa" : "Inativa";
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public List<Subcategory> getActiveSubcategories() {
        return  subcategories.stream().filter(Subcategory::isActive).toList();
    }

    public int countCourses(){
        return  subcategories.stream().map(Subcategory::getCourses).mapToInt(List::size).sum();
    }


    public void update(CategoryUpdateRequest categoryUpdateRequest) {
        this.name = categoryUpdateRequest.getName();
        this.description = categoryUpdateRequest.getDescription();
        this.studyGuide = categoryUpdateRequest.getStudyGuide();
        this.urlImage = categoryUpdateRequest.getUrlImage();
        this.hexCode = categoryUpdateRequest.getHexCode();
        this.code = categoryUpdateRequest.getCode();
        this.active = categoryUpdateRequest.isActive();
        this.sequence = categoryUpdateRequest.getSequence();
    }
}