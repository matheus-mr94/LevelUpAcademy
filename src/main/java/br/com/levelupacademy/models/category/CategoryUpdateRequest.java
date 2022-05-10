package br.com.levelupacademy.models.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class CategoryUpdateRequest {

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
    private String urlImage;
    private String hexCode;

    public CategoryUpdateRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.sequence = category.getSequence();
        this.urlImage = category.getUrlImage();
        this.hexCode = category.getHexCode();
    }

    public Category toEntity() {
        return new Category(id, name, code, description, studyGuide, active, sequence, urlImage, hexCode);
    }
}
