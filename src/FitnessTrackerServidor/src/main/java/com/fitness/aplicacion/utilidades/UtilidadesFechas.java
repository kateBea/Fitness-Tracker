package com.fitness.aplicacion.utilidades;

import java.time.LocalDateTime;

public class UtilidadesFechas {

    public static boolean isBetween(LocalDateTime target, LocalDateTime start, LocalDateTime end) {
        return target.isAfter(start) && target.isBefore(end);
    }

    public static boolean intervalsOverlap(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        return !(endA.isBefore(startB) || endB.isBefore(startA));
    }
}
