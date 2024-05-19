package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestGetDietaUsuario {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "email")
    private String idDieta;
}
