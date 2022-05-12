package br.com.levelupacademy.models.subcategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class SubcategoryUpdateRequestValidatorTest {

    private SubcategoryRepository repository;
    private SubcategoryUpdateRequestValidator formValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(SubcategoryRepository.class);
        formValidator = new SubcategoryUpdateRequestValidator(repository);
        errors = mock(Errors.class);
        when(repository.existsByCodeWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);
    }

    @Test
    void shouldNotReturnErrorWhenCodeExistAndHasTheSameId() {
        SubcategoryUpdateRequest form = new SubcategoryUpdateRequest();
        form.setId(1L);
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldReturnErrorWhenCodeExistButDifferentId() {
        SubcategoryUpdateRequest form = new SubcategoryUpdateRequest();
        form.setId(2L);
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors).rejectValue("code", "code.already.exist");
    }

    @Test
    void shouldNotReturnErrorWhenNotExistCodeForId() {
        SubcategoryUpdateRequest form = new SubcategoryUpdateRequest();
        form.setId(1L);
        form.setCode("devops");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldNotReturnErrorWhenNotExistCodeNeitherId() {
        SubcategoryUpdateRequest form = new SubcategoryUpdateRequest();
        form.setId(2L);
        form.setCode("devops");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}