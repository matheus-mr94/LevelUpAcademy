package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
@Setter
public class SubcategoryCreateRequest {

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

    public Subcategory toEntity(Category category) {
        return new Subcategory(name, code, description, studyGuide, active, sequence, category);
    }
}
