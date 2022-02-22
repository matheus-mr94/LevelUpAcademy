package br.com.levelupacademy.validators;

import br.com.levelupacademy.models.course.Course;

public class CourseValidations {

    public static boolean courseIsValid(String name, String code, String instructor,  String subcategoryName) {
            if(!name.isEmpty() && !code.isEmpty() && !instructor.isEmpty() && !subcategoryName.isEmpty()){
                return true;
            }else {
                System.out.println("The file wasn't read correctly");
                return false;
            }
        }

}
