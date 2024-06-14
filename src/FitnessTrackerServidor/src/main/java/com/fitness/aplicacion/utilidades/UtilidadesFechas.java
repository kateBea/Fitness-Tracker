package com.fitness.aplicacion.utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Utilidades para la manipulación y comparación de fechas.
 */
public class UtilidadesFechas {

    /**
     * Verifica si una fecha objetivo está entre dos fechas dadas.
     *
     * @param target la fecha objetivo
     * @param start  la fecha de inicio
     * @param end    la fecha de fin
     * @return {@code true} si la fecha objetivo está entre la fecha de inicio y la fecha de fin (exclusivo), {@code false} en caso contrario
     */
    public static boolean isBetween(LocalDate target, LocalDate start, LocalDate end) {
        if (Stream.of(target, start, end).anyMatch(Objects::isNull)) {
            return false;
        }

        return target.isAfter(start) && target.isBefore(end);
    }

    /**
     * Verifica si dos intervalos de fechas y horas se solapan.
     *
     * @param startA el inicio del primer intervalo
     * @param endA   el fin del primer intervalo
     * @param startB el inicio del segundo intervalo
     * @param endB   el fin del segundo intervalo
     * @return {@code true} si los intervalos se solapan, {@code false} en caso contrario
     */
    public static boolean intervalsOverlap(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        if (Stream.of(startA, endA, startB, endB).anyMatch(Objects::isNull)) {
            return false;
        }

        return !(endA.isBefore(startB) || endB.isBefore(startA));
    }
}
