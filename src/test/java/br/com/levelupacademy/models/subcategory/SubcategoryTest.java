package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.category.Category;
import br.com.levelupacademy.models.subcategory.Subcategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

public class SubcategoryTest {

    private Category category;

    @BeforeEach
    public void initialize() {
        this.category = new Category("Programação", "programacao", "descrição",
                "guia de estudos", false, 1, "http://teste.com", "#a123");
    }

    @Test
    void shouldInstantiateTheObject() {
        assertDoesNotThrow(()-> new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category));
    }

    @Test
    void getCategoryCode__should_return_categoryCode() {
        Subcategory subcategory = new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category);
        String categoryCode = subcategory.getCategoryCode();
        assertEquals("programacao",categoryCode);
    }
    @Test
    void getCategoryName__should_return_categoryName() {
        Subcategory subcategory = new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category);
        String categoryName = subcategory.getCategoryName();
        assertEquals("Programação", categoryName);
    }

    @Test
    void hasDescription__should_return_hasDescriptionWhenFieldIsNotEmptyOrNotNull() {
        Subcategory subcategory = new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category);
        boolean hasDescription = subcategory.hasDescription();
        assertTrue(hasDescription);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void hasDescription__should_return_hasNotDescriptionWhenFieldIsEmptyOrNull(String input) {
        Subcategory subcategory = new Subcategory("Java", "java-oo", input,
                "guia de estudos", false, 1, this.category);
        boolean hasDescription = subcategory.hasDescription();
        assertFalse(hasDescription);
    }

    @ParameterizedTest
    @EmptySource
    void shouldThrowIllegalArgumentExceptionWhenIsEmpty(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(input, "java-oo",
                "descrição", "guia de estudos", false, 1, this.category));

        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", input,
                "descrição", "guia de estudos", false, 1, this.category));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointerExceptionWhenIsNull(String input) {
        assertThrows(NullPointerException.class, () -> new Subcategory(input, "java-oo", "descrição",
                    "guia de estudos", false, 1, this.category));

        assertThrows(NullPointerException.class, () -> new Subcategory("Java", input, "descrição",
                    "guia de estudos", false, 1, this.category));

        assertThrows(NullPointerException.class, () -> new Subcategory("Java", "java-oo",
                "descrição", "guia de estudos", false, 1, null));
    }

    @ParameterizedTest
    @CsvSource({"programação", "java_e_oop", "#java17","Programacao", "java e orientação a objetos"})
    void shouldReturnIllegalArgumentExceptionWhenCharactersAreOutOfStandard(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", input,
                "descrição", "guia de estudos", false, 1, this.category));
    }
}