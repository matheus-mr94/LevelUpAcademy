package br.com.levelupacademy.models.subcategory;

import lombok.Getter;

import java.util.List;

@Getter
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

    public static List<SubcategorySimpleResponse> toDTO(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategorySimpleResponse::new).toList();
    }
}
