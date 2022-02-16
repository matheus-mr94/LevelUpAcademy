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
    public static void cantBeNull(Integer field, String error) {
        if(field == null) {
            throw new NullPointerException(error);
        }
    }

    public static void codeValidation(String code, String error) {
        if(!code.matches("[a-z0-9^-]+")) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void sizeValidation(int field, int start, int end, String error) {
        if(field < start || field > end) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void objectIsNotNull(Object object, String error) {
        if(object == null) {
            throw new NullPointerException(error);
        }
    }

}
