package com.fitness.aplicacion.documentos;

public enum Orden {
    PRIMER_PLATO,
    SEGUNDO_PLATO,
    TERCER_PLATO,
    PLATO_UNICO,
    ;

    public static Orden fromStr(String orden) {
        return switch (orden) {
            case "PRIMER_PLATO", "Primer plato", "primer_plato" -> PRIMER_PLATO;
            case "SEGUNDO_PLATO", "Segundo plato", "segundo_plato" -> SEGUNDO_PLATO;
            case "TERCER_PLATO", "Tercer plato", "tercer_plato" -> TERCER_PLATO;
            default -> PLATO_UNICO;
        };
    }
}
