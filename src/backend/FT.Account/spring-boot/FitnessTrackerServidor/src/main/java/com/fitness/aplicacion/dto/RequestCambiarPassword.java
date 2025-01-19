package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Modelo de petición para cambiar la contraseña de un usuario
 * @version 1.0
 * */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestCambiarPassword {

    /**
     * Email
     * */
    @NotBlank(message = "El campo 'email' no puede estar vacío.")
    @JsonProperty(value = "email")
    private String email;

    /**
     * Nueva contraseña
     * */
    @NotBlank(message = "El campo 'new_password' no puede estar vacío.")
    @JsonProperty(value = "new_password")
    private String newPassword;

    /**
     * Contraseña antigua
     * */
    @NotBlank(message = "El campo 'old_password' no puede estar vacío.")
    @JsonProperty(value = "old_password")
    private String oldPassword;
}
