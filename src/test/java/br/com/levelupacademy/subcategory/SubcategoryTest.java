package br.com.levelupacademy.subcategory;

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
    void shouldReturnCategoryCode() {
        Subcategory subcategory = new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category);
        String categoryCode = subcategory.getCategoryCode();
        assertEquals("programacao",categoryCode);
    }

    @Test
    void shouldReturnHasDescription() {
        Subcategory subcategory = new Subcategory("Java", "java-oo", "descrição",
                "guia de estudos", false, 1, this.category);
        boolean hasDescription = subcategory.hasDescription();
        assertTrue(hasDescription);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldReturnHasNotDescription(String input) {
        Subcategory subcategory = new Subcategory("Java", "java-oo", input,
                "guia de estudos", false, 1, this.category);
        boolean hasDescription = subcategory.hasDescription();
        assertFalse(hasDescription);
    }

    @ParameterizedTest
    @EmptySource
    void shouldThrowIllegalArgumentException(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory(input, "java-oo",
                "descrição", "guia de estudos", false, 1, this.category));

        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", input,
                "descrição", "guia de estudos", false, 1, this.category));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointerException(String input) {
        assertThrows(NullPointerException.class, () -> new Subcategory(input, "java-oo", "descrição",
                    "guia de estudos", false, 1, this.category));

        assertThrows(NullPointerException.class, () -> new Subcategory("Java", input, "descrição",
                    "guia de estudos", false, 1, this.category));

        assertThrows(NullPointerException.class, () -> new Subcategory("Java", "java-oo",
                "descrição", "guia de estudos", false, 1, null));
    }

    @ParameterizedTest
    @CsvSource({"programação", "java_e_oop", "#java17","Programacao"})
    void shouldReturnIllegalArgumentException(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Subcategory("Java", input,
                "descrição", "guia de estudos", false, 1, this.category));
    }
}