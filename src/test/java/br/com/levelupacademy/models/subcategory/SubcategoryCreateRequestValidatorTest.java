package br.com.levelupacademy.models.subcategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SubcategoryCreateRequestValidatorTest {

    private SubcategoryRepository repository;
    private SubcategoryCreateRequestValidator formValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(SubcategoryRepository.class);
        formValidator = new SubcategoryCreateRequestValidator(repository);
        errors = mock(Errors.class);
    }

    @Test
    void shouldReturnErrorWhenCodeExist() {
        when(repository.existsByCode("programacao")).thenReturn(true);

        SubcategoryCreateRequest form = new SubcategoryCreateRequest();
        form.setCode("programacao");

        formValidator.validate(form, errors);
        verify(errors).rejectValue("code", "code.already.exist");
    }

    @Test
    void shouldNotReturnErrorWhenCodeNotExist() {
        SubcategoryCreateRequest form = new SubcategoryCreateRequest();
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(),anyString());
    }
}