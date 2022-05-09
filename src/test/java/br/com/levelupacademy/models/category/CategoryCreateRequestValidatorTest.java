package br.com.levelupacademy.models.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class CategoryCreateRequestValidatorTest {

    private CategoryRepository repository;
    private CategoryCreateRequestValidator formValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        formValidator = new CategoryCreateRequestValidator(repository);
        errors = mock(Errors.class);
    }

    @Test
    void shouldReturnErrorWhenCodeExist() {
        when(repository.existsByCode("programacao")).thenReturn(true);

        CategoryCreateRequest form = new CategoryCreateRequest();
        form.setCode("programacao");

        formValidator.validate(form, errors);
        verify(errors).rejectValue("code", "code.already.exist");
    }

    @Test
    void shouldNotReturnErrorWhenCodeNotExist() {
        CategoryCreateRequest form = new CategoryCreateRequest();
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(),anyString());
    }
}