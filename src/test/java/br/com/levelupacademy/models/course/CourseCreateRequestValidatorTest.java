package br.com.levelupacademy.models.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CourseCreateRequestValidatorTest {

    private CourseRepository repository;
    private CourseCreateRequestValidator formValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(CourseRepository.class);
        formValidator = new CourseCreateRequestValidator(repository);
        errors = mock(Errors.class);
    }

    @Test
    void shouldReturnErrorWhenCodeExist() {
        when(repository.existsByCode("programacao")).thenReturn(true);

        CourseCreateRequest form = new CourseCreateRequest();
        form.setCode("programacao");

        formValidator.validate(form, errors);
        verify(errors).rejectValue("code", "code.already.exist");
    }

    @Test
    void shouldNotReturnErrorWhenCodeNotExist() {
        CourseCreateRequest form = new CourseCreateRequest();
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(),anyString());
    }

}