package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Modelo de respuesta para Recoger datos de usuario
 * @version 1.0
 * */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestGetDatosUsuario {

    /**
     * Email
     * */
    @NotBlank(message = "El campo 'email' no puede estar vacío.")
    @Email(message = "El valor no es un correo electrónico válido.")
    @JsonProperty(value = "email")
    private String email;
}
