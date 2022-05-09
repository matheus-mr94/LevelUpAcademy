package br.com.levelupacademy.models.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class CategoryUpdateRequestValidatorTest {

    private CategoryRepository repository;
    private CategoryUpdateRequestValidator formValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        formValidator = new CategoryUpdateRequestValidator(repository);
        errors = mock(Errors.class);
        when(repository.existsByCodeWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);
    }

    @Test
    void shouldNotReturnErrorWhenCodeExistAndHasTheSameId() {
        CategoryUpdateRequest form = new CategoryUpdateRequest();
        form.setId(1L);
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
     }

    @Test
    void shouldReturnErrorWhenCodeExistButDifferentId() {

        CategoryUpdateRequest form = new CategoryUpdateRequest();
        form.setId(2L);
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors).rejectValue("code", "code.already.exist");
    }

    @Test
    void shouldNotReturnErrorWhenNotExistCodeForId() {
        CategoryUpdateRequest form = new CategoryUpdateRequest();
        form.setId(1L);
        form.setCode("devops");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldNotReturnErrorWhenNotExistCodeNeitherId() {
        CategoryUpdateRequest form = new CategoryUpdateRequest();
        form.setId(2L);
        form.setCode("devops");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}