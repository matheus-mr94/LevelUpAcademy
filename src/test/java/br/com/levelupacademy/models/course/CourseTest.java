package br.com.levelupacademy.models.course;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    private Category category;
    private Subcategory subcategory;

    @BeforeEach
    public void initialize() {
        this.category = new Category("Programação","programacao","descrição",
                "guia de estudos",false,1,"http://teste.com","#a123");
        this.subcategory = new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category);
    }

    @Test
    void shouldInstantiateTheObject() {
        assertDoesNotThrow(()->  new Course("Java e OO", "oop", 10,
                "iniciantes em java", true, "Nico",
                "ementa", "conhecimentos em OO", this.subcategory));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionWhenFieldIsEmptyOrNull(String input) {
        assertThrows(IllegalArgumentException.class,() -> new Course(input, "oop", 10,
                "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory));

        assertThrows(IllegalArgumentException.class,() -> new Course("Collections", "oop",
                10, "iniciantes em java", true, "Nico",
                "ementa", "conhecimentos em OO", null));

        assertThrows(IllegalArgumentException.class,() -> new Course("Java e orientação a objetos", "oop",
                10, "iniciantes em java", true, input, "ementa",
                "conhecimentos em OO", this.subcategory));

        assertThrows(IllegalArgumentException.class,() -> new Course("Java e orientação a objetos", input,
                10, "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory));

        assertThrows(IllegalArgumentException.class,() -> new Course("Java e orientação a objetos", "oop",
                null, "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory));
    }

    @ParameterizedTest
    @CsvSource({"programação", "java_e_oop", "Programacao", "java e orientação a objetos", "$java17"})
    void shouldReturnIllegalArgumentExceptionWhenCharactersAreOutOfStandard(String input) {
        assertThrows(IllegalArgumentException.class,() -> new Course("Java e orientação a objetos", input,
                10, "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory));
    }

    @ParameterizedTest
    @CsvSource({"programacao", "java-e-oop", "novidades-java-8"})
    void shouldInitiateObjectWhenCharactersAreInStandard(String input) {
        assertDoesNotThrow(() -> new Course("Java e orientação a objetos", input,
                10, "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory));
    }

    @ParameterizedTest
    @CsvSource({"0","21","22","23","50"})
    void shouldThrowIllegalArgumentExceptionWhenIsOutOfRange(Integer input) {
        assertThrows(IllegalArgumentException.class,() -> new Course("Java e orientação a objetos",
                "oop" ,input, "iniciantes em java", true, "Nico", "ementa",
                "conhecimentos em OO", this.subcategory));
    }

    @Test
    void getSubcategoryCode__should_return_subcategoryCode() {
        Course course = new Course("Java e orientação a objetos", "oop" ,10,
                "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory);
        String subcategoryCode = course.getSubcategoryCode();
        assertEquals("java-oo", subcategoryCode);
    }

    @Test
    void getSubcategoryName__should_return_subcategoryName() {
        Course course = new Course("Java e orientação a objetos", "oop" ,10,
                "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory);
        String subcategoryName = course.getSubcategoryName();
        assertEquals("Java", subcategoryName);
    }

    @Test
    void getCategoryCode__should_return_categoryCode() {
        Course course = new Course("Java e orientação a objetos", "oop" ,10,
                "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory);
        String categoryCode = course.getCategoryCode();
        assertEquals("programacao", categoryCode);
    }

}