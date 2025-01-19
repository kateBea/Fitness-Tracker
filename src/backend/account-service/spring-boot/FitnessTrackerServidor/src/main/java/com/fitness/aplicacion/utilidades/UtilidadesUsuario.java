package com.fitness.aplicacion.utilidades;

public class UtilidadesUsuario {

    /**
     * @implNote Queda por implementar usando expresiones regulares
     */
    public static boolean passwordCheck(String password) {
        boolean eightDigitsMin = password.length() >= 8;
        boolean containsNumber = false;
        boolean containsNonAlphaNum = false;

        for (Character c : password.toCharArray()) {
            if (containsNumber && containsNonAlphaNum) {
                break;
            }

            if (!containsNonAlphaNum) {
                containsNonAlphaNum = !Character.isLetterOrDigit(c);
            }

            if (!containsNumber) {
                containsNumber = Character.isDigit(c);
            }
        }

        return eightDigitsMin && containsNumber && containsNonAlphaNum;
    }
}
