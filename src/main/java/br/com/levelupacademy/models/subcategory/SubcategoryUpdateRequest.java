package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SubcategoryUpdateRequest {

    private Long id;
    @NotBlank(message = "{name.emptyOrNull}")
    private String name;
    @NotBlank(message = "{code.emptyOrNull}")
    @Pattern(regexp = "[a-z0-9-]+", message = "{code.invalidCharacters}")
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private Category category;

    @Deprecated
    public SubcategoryUpdateRequest() {
    }

    public SubcategoryUpdateRequest(Subcategory subcategory) {
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.description = subcategory.getDescription();
        this.studyGuide = subcategory.getStudyGuide();
        this.active = subcategory.isActive();
        this.sequence = subcategory.getSequence();
        this.category = subcategory.getCategory();
    }

    public String getCategoryCode() {
        return category.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory toEntity() {
        return new Subcategory(id, name, code, description, studyGuide, active, sequence, category);
    }
}
