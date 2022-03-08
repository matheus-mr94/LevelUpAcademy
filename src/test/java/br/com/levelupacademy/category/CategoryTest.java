package br.com.levelupacademy.category;


import br.com.levelupacademy.models.category.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CategoryTest {

    @Test
    void shouldInstantiateTheObject() {
        assertDoesNotThrow(() -> new Category("Programação", "java", "descrição",
                "guia de estudos", false,1,
                "http://teste.com", "#a123"));
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowNullPointerExceptionBecauseFieldIsNull(String input) {
        assertThrows(NullPointerException.class,() -> {
            Category category = new Category(input, "java", "descrição",
                    "guia de estudos", false,1,
                    "http://teste.com", "#a123");
        });

        assertThrows(NullPointerException.class,() -> {
            Category category = new Category("Programação", input, "descrição",
                    "guia de estudos", false, 1,
                    "http://teste.com", "#a123");
        });
    }

    @ParameterizedTest
    @EmptySource
    void shouldThrowIllegalArgumentExceptionFieldIsEmpty(String input) {
        assertThrows(IllegalArgumentException.class,() -> {
            Category category = new Category(input,"java","descrição",
                    "guia de estudos",false,1,"http://teste.com","#a123");
        });

        assertThrows(IllegalArgumentException.class,() -> {
            Category category = new Category("Programação", input, "descrição",
                    "guia de estudos", false, 1,
                    "http://teste.com", "#a123");
        });
    }

    @ParameterizedTest
    @CsvSource({"programação", "java_e_oop", "#java17","Programacao"})
    void shouldReturnIllegalArgumentExceptionBecauseHasInvalidCharacters (String input) {
        assertThrows(IllegalArgumentException.class,() -> {
            Category category = new Category("Programação", input, "descrição",
                    "guia de estudos", false, 1,
                    "http://teste.com", "#a123");
        });
    }
}
