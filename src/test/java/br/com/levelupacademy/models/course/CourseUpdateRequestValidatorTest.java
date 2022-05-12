package br.com.levelupacademy.models.course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CourseUpdateRequestValidatorTest {

    private CourseRepository repository;
    private CourseUpdateRequestValidator formValidator;
    private Errors errors;

    @BeforeEach
    void setUp() {
        repository = mock(CourseRepository.class);
        formValidator = new CourseUpdateRequestValidator(repository);
        errors = mock(Errors.class);
        when(repository.existsByCodeWithDifferentId(eq("programacao"), not(eq(1L)))).thenReturn(true);
    }

    @Test
    void shouldNotReturnErrorWhenCodeExistAndHasTheSameId() {
        CourseUpdateRequest form = new CourseUpdateRequest();
        form.setId(1L);
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldReturnErrorWhenCodeExistButDifferentId() {

        CourseUpdateRequest form = new CourseUpdateRequest();
        form.setId(2L);
        form.setCode("programacao");

        formValidator.validate(form, errors);

        verify(errors).rejectValue("code", "code.already.exist");
    }

    @Test
    void shouldNotReturnErrorWhenNotExistCodeForId() {
        CourseUpdateRequest form = new CourseUpdateRequest();
        form.setId(1L);
        form.setCode("devops");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }

    @Test
    void shouldNotReturnErrorWhenNotExistCodeNeitherId() {
        CourseUpdateRequest form = new CourseUpdateRequest();
        form.setId(2L);
        form.setCode("devops");

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}