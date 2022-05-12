package br.com.levelupacademy.models.course;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseUpdateRequestValidator implements Validator {

    private final CourseRepository courseRepository;

    public CourseUpdateRequestValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseUpdateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseUpdateRequest form = (CourseUpdateRequest) target;
        if(courseRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
            errors.rejectValue("code", "code.already.exist");
        }
    }
}
