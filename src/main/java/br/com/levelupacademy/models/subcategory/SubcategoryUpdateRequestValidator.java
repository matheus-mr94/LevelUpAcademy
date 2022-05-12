package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.course.CourseRepository;
import br.com.levelupacademy.models.course.CourseUpdateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SubcategoryUpdateRequestValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryUpdateRequestValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SubcategoryUpdateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubcategoryUpdateRequest form = (SubcategoryUpdateRequest) target;
        if(subcategoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
            errors.rejectValue("code", "code.already.exist");
        }
    }
}
