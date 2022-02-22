package br.com.levelupacademy.validators;

import br.com.levelupacademy.models.category.Category;

import static br.com.levelupacademy.validators.Validations.*;

public class SubcategoryValidations {

    public static boolean subcategoryIsValid(String name, String code, Category category) {
        if(isSubcategoryValid(name, code, category)) {
            return true;
        }else {
            System.out.println("The file wasn't read correctly, some field is empty");
            return false;
        }
    }

    private static boolean isNameValid(String name) {
        cantBeEmptyOrNull(name,"Can't be empty or null");
        return true;
    }
    private static boolean isCodeValid(String code) {
        codeValidation(code,"Invalid characters");
        return true;
    }

    private static boolean isCategoryValid(Category category) {
        objectIsNotNull(category,"Course can't be empty or null");
        return true;
    }

    private static boolean isSubcategoryValid(String name, String code, Category category) {
        isNameValid(name);
        isCodeValid(code);
        isCategoryValid(category);
        return true;
    }
}
