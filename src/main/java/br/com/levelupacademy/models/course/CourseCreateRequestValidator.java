package br.com.levelupacademy.models.course;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CourseCreateRequestValidator implements Validator {

    private final CourseRepository courseRepository;

    public CourseCreateRequestValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseCreateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseCreateRequest form = (CourseCreateRequest) target;
        if(courseRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "code.already.exist");
        }
    }
}
