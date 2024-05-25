package com.fitness.aplicacion.utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UtilidadesFechas {

    public static boolean isBetween(LocalDate target, LocalDate start, LocalDate end) {
        if (Stream.of(target, start, end).anyMatch(Objects::isNull)) {
            return false;
        }

        return target.isAfter(start) && target.isBefore(end);
    }

    public static boolean intervalsOverlap(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        if (Stream.of(startA, endA, startB, endB).anyMatch(Objects::isNull)) {
            return false;
        }

        return !(endA.isBefore(startB) || endB.isBefore(startA));
    }
}
