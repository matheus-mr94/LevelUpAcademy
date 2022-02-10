package br.com.levelupacademy.validators;

public class Validations {

    public static void cantBeEmptyOrNull(String field, String error) {
        if(field == null) {
            throw new NullPointerException(error);
        }
        if(field.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void codeValidation(String code, String error) {
        if(!code.matches("[a-z0-9^-]+")) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void sizeValidation(int field, String error) {
        if(field < 1 || field > 20) {
            throw new IllegalArgumentException(error);
        }
    }

}
