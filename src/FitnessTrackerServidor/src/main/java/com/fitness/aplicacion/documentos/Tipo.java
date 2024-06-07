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
            case "MERIENDA_VESPERTINA", "merienda vespertina", "merienda", "MERIENDA", "merienda_vespertina" -> MERIENDA_VESPERTINA;
            case "MERIENDA_MATUTINA", "merienda matutina", "media mañana", "merienda_matutina", "media_manana" -> MERIENDA_MATUTINA;
            case "ALMUERZO", "Almuerzo", "comida", "COMIDA" -> ALMUERZO;
            default -> CENA;
        };
    }
}
