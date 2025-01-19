package com.fitness.aplicacion.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Modelo de respuesta para Verificación de usuario
 * @version 1.0
 * */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVerifyData {

    /**
     * Email
     * */
    @JsonProperty(value = "email")
    private String email;

    /**
     * Nombre de la cuenta
     * */
    @JsonProperty(value = "account_name")
    private String nombreUsuario;

    /**
     * Nombre del cliente
     * */
    @JsonProperty(value = "client_name")
    private String nombre;

    /**
     * Primer apellido
     * */
    @JsonProperty(value = "first_surname")
    private String primerApellido;

    /**
     * Segundo apellido
     * */
    @JsonProperty(value = "second_surname")
    private String segundoApellido;

    /**
     * Fecha de nacimiento
     * */
    @JsonProperty(value = "birthday")
    private LocalDate fechaDeNacimiento;

    /**
     * Fecha de registro del cliente
     * */
    @JsonProperty(value = "register_date")
    private LocalDateTime fechaRegistro;

    /**
     * Foto de perfil
     * */
    @JsonProperty(value = "image")
    private String imagen;

    /**
     * Peso objetivo
     * */
    @JsonProperty(value = "target_weight")
    private float objetivoPeso;

    /**
     * Altura en centímetros
     * */
    @JsonProperty(value = "height")
    private float altura;

    /**
     * Peso en kilogramos
     * */
    @JsonProperty(value = "weight")
    private float peso;

    /**
     * Sexo
     * */
    @JsonProperty(value = "sex")
    private String sexo;

    /**
     * Fecha de inicio de sesión
     * */
    @JsonProperty(value = "logged_at")
    private LocalDateTime loggedAt;
}

