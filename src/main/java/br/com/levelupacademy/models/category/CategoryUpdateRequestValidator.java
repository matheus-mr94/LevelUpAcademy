package br.com.levelupacademy.models.category;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryUpdateRequestValidator implements Validator {

    private final CategoryRepository categoryRepository;

    public CategoryUpdateRequestValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryUpdateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryUpdateRequest form = (CategoryUpdateRequest) target;
        if(categoryRepository.existsByCodeWithDifferentId(form.getCode(), form.getId())) {
            errors.rejectValue("code", "code.already.exist");
        }
    }
}
