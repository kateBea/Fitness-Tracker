package com.fitness.aplicacion.documentos;

public enum Tipo {
    DESAYUNO,
    MERIENDA_MATUTINA,
    ALMUERZO,
    MERIENDA_VESPERTINA,
    CENA;

    public static Tipo fromStr(String tipo) {
        return switch (tipo) {
            case "DESAYUNO" -> DESAYUNO;
            default -> ALMUERZO;
        };
    }
}
