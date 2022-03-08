package br.com.levelupacademy.course;


import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.subcategory.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

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
    @NullSource
    void shouldThrowNullPointerException(String input) {
        assertThrows(NullPointerException.class,() -> {
            Course course = new Course(input, "oop", 10,"iniciantes em java",
                    true,"Nico", "ementa", "conhecimentos em OO",
                    this.subcategory);
        });

        assertThrows(NullPointerException.class,() -> {
            Course course = new Course("Collections", "oop", 10,
                    "iniciantes em java", true,"Nico", "ementa",
                    "conhecimentos em OO", null);
        });

        assertThrows(NullPointerException.class,() -> {
            Course course = new Course("Java e orientação a objetos", "oop", 10,
                    "iniciantes em java", true, input, "ementa",
                    "conhecimentos em OO", this.subcategory);
        });

        assertThrows(NullPointerException.class,() -> {
            Course course = new Course("Java e orientação a objetos", input, 10,
                    "iniciantes em java", true,"Nico", "ementa",
                    "conhecimentos em OO", this.subcategory);
        });

        assertThrows(NullPointerException.class,() -> {
            Course course = new Course("Java e orientação a objetos", "oop", null,
                    "iniciantes em java", true,"Nico", "ementa",
                    "conhecimentos em OO", this.subcategory);
        });
    }

    @ParameterizedTest
    @EmptySource
    void shouldThrowIllegalArgumentException(String input) {
        assertThrows(IllegalArgumentException.class,() -> {
            Course course = new Course(input, "oop", 10,"iniciantes em java",
                    true,"Nico", "ementa", "conhecimentos em OO",
                    this.subcategory);
        });

        assertThrows(IllegalArgumentException.class,() -> {
            Course course = new Course("Java e orientação a objetos", "oop", 10,
                    "iniciantes em java", true,input, "ementa",
                    "conhecimentos em OO", this.subcategory);
        });

        assertThrows(IllegalArgumentException.class,() -> {
            Course course = new Course("Java e orientação a objetos", input, 10,
                    "iniciantes em java", true,"Nico", "ementa",
                    "conhecimentos em OO", this.subcategory);
        });
    }

    @ParameterizedTest
    @CsvSource({"programação", "java_e_oop", "#java17","Programacao"})
    void shouldReturnIllegalArgumentException(String input) {
        assertThrows(IllegalArgumentException.class,() -> {
            Course course = new Course("Java e orientação a objetos", input, 10,
                    "iniciantes em java", true,"Nico", "ementa",
                    "conhecimentos em OO", this.subcategory);
        });
    }

    @ParameterizedTest
    @CsvSource({"0","22","23","50"})
    void shouldReturnIllegalArgumentException(Integer input) {
        assertThrows(IllegalArgumentException.class,() -> {
            Course course = new Course("Java e orientação a objetos","oop" ,input,
                    "iniciantes em java", true, "Nico", "ementa",
                    "conhecimentos em OO", this.subcategory);
        });
    }

    @Test
    void shouldReturnSubcategoryCode() {
        Course course = new Course("Java e orientação a objetos", "oop" ,10,
                "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory);
        String subcategoryCode = course.getSubcategoryCode();
        assertEquals("java-oo", subcategoryCode);
    }

    @Test
    void shouldReturnCategoryCode() {
        Course course = new Course("Java e orientação a objetos", "oop" ,10,
                "iniciantes em java", true,"Nico", "ementa",
                "conhecimentos em OO", this.subcategory);
        String categoryCode = course.getCategoryCode();
        assertEquals("programacao", categoryCode);
    }
}
