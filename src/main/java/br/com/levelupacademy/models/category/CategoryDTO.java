package br.com.levelupacademy.models.category;

public class CategoryDTO {

    private String name;
    private String code;
    private String description;
    private String studyGuide;
    private boolean active;
    private int sequence;
    private String urlImage;
    private String hexCode;

    public CategoryDTO(String name, String code, String description, String studyGuide, boolean active, int sequence, String urlImage, String hexCode) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.sequence = sequence;
        this.urlImage = urlImage;
        this.hexCode = hexCode;
    }

    public Category toEntity() {
        return new Category(name, code, description, studyGuide, active, sequence, urlImage, hexCode);
    }
}
