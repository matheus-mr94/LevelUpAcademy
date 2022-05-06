package br.com.levelupacademy.models.subcategory;

import br.com.levelupacademy.models.course.CourseCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SubcategoryCreateRequestValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    public SubcategoryCreateRequestValidator(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SubcategoryCreateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubcategoryCreateRequest form = (SubcategoryCreateRequest) target;
        if(subcategoryRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "code.already.exist");
        }
    }
}
