package br.com.levelupacademy.models.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryCreateRequestValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryCreateRequestValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryCreateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryCreateRequest form = (CategoryCreateRequest) target;
        if(categoryRepository.existsByCode(form.getCode())) {
            errors.rejectValue("code", "code.already.exist");
        }
    }
}
