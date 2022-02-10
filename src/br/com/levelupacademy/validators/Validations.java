package br.com.levelupacademy.validators;

public class Validations {

    public static void cantBeEmptyOrNull(String title, String error) {
        if(title.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
        if(title == null) {
            throw new NullPointerException(error);
        }
    }

    public static void codeValidation(String code, String error) {
        if(!code.matches("[a-z0-9^-]+")) {
            throw new IllegalArgumentException(error);
        }
    }

}
