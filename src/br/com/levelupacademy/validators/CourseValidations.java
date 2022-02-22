package br.com.levelupacademy.validators;

import br.com.levelupacademy.models.subcategory.Subcategory;

import static br.com.levelupacademy.validators.Validations.*;

public class CourseValidations {

    public static boolean courseIsValid(String name, String code, String instructor,  Subcategory subcategory) {
        if(isCourseValid(name,code,instructor) && subcategory != null) {
            return true;
        } else {
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
    private static boolean isInstructorValid(String instructor) {
        cantBeEmptyOrNull(instructor,"Instructor can't be empty or null");
        return true;
    }

    private static boolean isCourseValid(String name, String code, String instructor) {
        isNameValid(name);
        isCodeValid(code);
        isInstructorValid(instructor);
        return true;
    }
}