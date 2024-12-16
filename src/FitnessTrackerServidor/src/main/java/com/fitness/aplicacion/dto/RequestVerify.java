package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Modelo de petición para Verificación de usuario
 * @version 1.0
 * */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestVerify {

    /**
     * Email
     * */
    @NotBlank(message = "El campo 'email' no puede estar vacío.")
    @JsonProperty(value = "email")
    private String email;

    /**
     * Password
     * */
    @NotBlank(message = "El campo 'password' no puede estar vacío.")
    @JsonProperty(value = "password")
    private String password;

}
