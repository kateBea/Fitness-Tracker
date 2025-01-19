package com.fitness.aplicacion.documentos;


/**
 * Modelo que representa un sexo
 *
 * @version 1.0
 * */
public enum Sexo {
    HOMBRE {
        @Override
        public String toString() {
            return "Hombre";
        }
    },

    MUJER {
        @Override
        public String toString() {
            return "Mujer";
        }
    },

    OTRO {
        @Override
        public String toString() {
            return "Otro";
        }
    };

    public static Sexo fromStr(String sexo) {
        return switch (sexo) {
            case "HOMBRE", "H", "h", "hombre", "Hombre", "masculino", "macho", "" -> HOMBRE;
            case "MUJER", "M", "F", "mujer", "Mujer", "femenino", "hembra" -> MUJER;
            default -> throw new RuntimeException("El sexo debe ser 'Hombre', 'Mujer' u 'Otro' o un string vacío");
        };
    }
}
