package com.fitness.aplicacion.documentos;

public enum Tipo {
    DESAYUNO,
    MERIENDA_MATUTINA,
    ALMUERZO,
    MERIENDA_VESPERTINA,
    CENA;

    public static Tipo fromStr(String tipo) {
        return switch (tipo) {
            case "DESAYUNO", "desayuno" -> DESAYUNO;
            case "merienda vespertina", "merienda", "MERIENDA", "merienda_vespertina" -> MERIENDA_VESPERTINA;
            case "merienda matutina", "media mañana", "merienda_matutina", "media_manana" -> MERIENDA_MATUTINA;
            case "Almuerzo", "ALMUERZO", "comida", "COMIDA" -> ALMUERZO;
            default -> CENA;
        };
    }
}
