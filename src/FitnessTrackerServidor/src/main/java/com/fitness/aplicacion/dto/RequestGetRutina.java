package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestGetRutina {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "rutina_id")
    private String rutinaId;
}
