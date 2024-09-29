package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseLogin extends BaseResponse {

    @JsonProperty(value = "data")
    private ResponseLoginData data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseLoginData {
        @JsonProperty(value = "email")
        private String email;

        @JsonProperty(value = "username")
        private String nombreUsuario;

        @JsonProperty(value = "name")
        private String nombre;

        @JsonProperty(value = "first_name")
        private String primerApellido;

        @JsonProperty(value = "second_name")
        private String segundoApellido;

        @JsonProperty(value = "fecha_nacimiento")
        private LocalDate fechaDeNacimiento;

        @JsonProperty(value = "fecha_alta")
        private LocalDateTime fechaRegistro;

        @JsonProperty(value = "imagen")
        private String imagen;

        @JsonProperty(value = "objetivo_peso")
        private float objetivoPeso;

        // En centímetros
        @JsonProperty(value = "altura")
        private float altura;

        // En kilogramos
        @JsonProperty(value = "peso")
        private float peso;

        @JsonProperty(value = "sexo")
        private String sexo;

        @JsonProperty(value = "logged_at")
        private LocalDateTime loggedAt;
    }

}
