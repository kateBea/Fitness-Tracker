package com.fitness.aplicacion.documentos;

public enum Orden {
    PRIMER_PLATO,
    SEGUNDO_PLATO,
    TERCER_PLATO,
    PLATO_UNICO,
    ;

    public static Orden fromStr(String orden) {
        return switch (orden) {
            case "PRIMER_PLATO" -> PRIMER_PLATO;
            default -> PRIMER_PLATO;
        };
    }
}
