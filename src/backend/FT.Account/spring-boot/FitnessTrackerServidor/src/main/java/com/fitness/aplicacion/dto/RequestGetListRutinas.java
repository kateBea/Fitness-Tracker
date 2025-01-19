package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestGetListRutinas {
    @JsonProperty(value = "email")
    private String email;

    // Si es cierto se recogen todas las rutinas del usuario,
    // si no recogen entre las fechas [fechaInicio, fechaFin),
    // estas por ello deben ser no nulas para este caso
    @JsonProperty(value = "fetch_all")
    private boolean fetchAll;

    @JsonProperty(value = "fecha_inicio")
    private LocalDate fechaInicio;

    @JsonProperty(value = "fecha_fin")
    private LocalDate fechaFin;
}
