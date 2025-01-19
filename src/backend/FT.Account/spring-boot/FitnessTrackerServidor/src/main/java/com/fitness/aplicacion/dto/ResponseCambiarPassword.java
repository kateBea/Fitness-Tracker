package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Modelo de respuesta para cambiar contraseña
 * @version 1.0
 * */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseCambiarPassword extends BaseResponse<Boolean> {

    /**
     * Fecha de última modificación
     * */
    @JsonProperty(value = "change_date")
    private LocalDateTime changeDate;
}
