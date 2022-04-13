package br.com.levelupacademy.models.subcategory;

import java.util.List;

public class SubcategorySimpleResponse {

    private final Long id;
    private final String name;
    private final String code;
    private final String active;

    public SubcategorySimpleResponse(Subcategory subcategory) {
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.active = subcategory.isActive() ? "Ativa" : "Inativa";
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getActive() {
        return this.active;
    }

    public static List<SubcategorySimpleResponse> toDTO(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategorySimpleResponse::new).toList();
    }
}
