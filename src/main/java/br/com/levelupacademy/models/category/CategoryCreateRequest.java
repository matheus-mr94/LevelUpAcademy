package br.com.levelupacademy.models.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CategoryCreateRequest {

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

    public Category toEntity() {
        return new Category(name, code, description, studyGuide, active, sequence, urlImage, hexCode);
    }
}
