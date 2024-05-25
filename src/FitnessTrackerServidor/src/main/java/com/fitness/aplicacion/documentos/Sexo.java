package com.fitness.aplicacion.documentos;


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
    };

    public static Sexo fromStr(String sexo) {
        return switch (sexo) {
            case "HOMBRE" -> HOMBRE;
            default -> HOMBRE;
        };
    }
}
