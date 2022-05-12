package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.course.CourseSimpleDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubcategorySimpleDTO {

    private String name;
    private String code;
    private List<CourseSimpleDTO> activeCourses;

    public SubcategorySimpleDTO(Subcategory subcategory) {
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.activeCourses = CourseSimpleDTO.toDTO(subcategory.getActiveCourses());
    }

    public static List<SubcategorySimpleDTO> toDTO(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategorySimpleDTO::new).toList();
    }
}
