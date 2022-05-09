package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
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
    private Long categoryId;
    private String categoryCode;

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
        this.categoryId = subcategory.getCategoryId();
        this.categoryCode = subcategory.getCategoryCode();
    }

    public Subcategory toEntity(Category category) {
        return new Subcategory(id, name, code, description, studyGuide, active, sequence, category);
    }
}
