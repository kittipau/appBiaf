package com.example.appBiaf;

/**
 * Clase para almacenar los métodos que se usaran para validar el registro
 */
public class Validaciones {

    /**
     * Método para validar el nombre de usuario
     * @param nombre del usuario
     * @return ret devolvera "x", en caso de que que la validación sea correcta, y "a", "b" o "c" según el error que pueda haber
     */
    public static String validarnombre(String nombre) {
        String ret = "x";
        if (nombre.isEmpty()) {
            ret ="a" ; // a si está vacío
        } else if (nombre.length() < 1 || nombre.length() > 20) {
            ret = "b"; //b si contiene más 20 caracteres
        } else {
            for (char c : nombre.toCharArray()) {
                if (!Character.isLetter(c) && !Character.isDigit(c)) {
                    ret = "c"; // si alguno de los caracteres no es un dígito o una letra
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * Método para validar la contraseña de los usuarios.
     * @param contra la contraseña a validar
     * @return ret devolvera "x" en caso de que que la validación sea correcta, y "a", "b" o "c" según el error que pueda haber
     */
    public static String validarcontra(String contra) {
        String ret = "x";
        if (contra.isEmpty()) {
            ret = "a"; /// a si está vacío
        } else if (contra.length() < 1 || contra.length() > 10) {
            ret = "b"; // a si contiene más de 10 caracteres
        } else {
            for (char c : contra.toCharArray()) {
                if (!Character.isLetter(c) && !Character.isDigit(c)) {
                    ret = "c"; //c si contiene un caracter diferente a una letra o un dígito
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * Método para validar el mail de los usuarios
     * @param mail el mail a validar
     * @return ret devolvera "x" en caso de que que la validación sea correcta, y "a", "b" o "c" según el error que pueda haber
     */

    public static String validarMail(String mail) {
        String ret = "x";
        if (mail.isEmpty()) {
            ret = "a"; // a si está vacío
        } else if (mail.length() < 5 || mail.length() > 40) {
            ret = "b"; //b si contiene más de 40 caracteres o menos de 5
        } else {
            for (char c : mail.toCharArray()) {
                if (!Character.isLetter(c) && c != '@' && !Character.isDigit(c) && c != '.' && c != '_') {
                    ret = "c"; //c si contiene algun caracter diferente a un numero, letra, "_", "." o "@"
                    break;
                }
            }
        }
        return ret;
    }


}
