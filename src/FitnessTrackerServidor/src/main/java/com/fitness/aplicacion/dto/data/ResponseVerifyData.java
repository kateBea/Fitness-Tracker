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
    @JsonProperty(value = "usuario")
    private String nombreUsuario;

    /**
     * Nombre del cliente
     * */
    @JsonProperty(value = "nombre")
    private String nombre;

    /**
     * Primer apellido
     * */
    @JsonProperty(value = "primer_apellido")
    private String primerApellido;

    /**
     * Segundo apellido
     * */
    @JsonProperty(value = "segundo_apellido")
    private String segundoApellido;

    /**
     * Fecha de nacimiento
     * */
    @JsonProperty(value = "fecha_nacimiento")
    private LocalDate fechaDeNacimiento;

    /**
     * Fecha de registro del cliente
     * */
    @JsonProperty(value = "fecha_alta")
    private LocalDateTime fechaRegistro;

    /**
     * Foto de perfil
     * */
    @JsonProperty(value = "imagen")
    private String imagen;

    /**
     * Peso objetivo
     * */
    @JsonProperty(value = "objetivo_peso")
    private float objetivoPeso;

    /**
     * Altura en centímetros
     * */
    @JsonProperty(value = "altura")
    private float altura;

    /**
     * Peso en kilogramos
     * */
    @JsonProperty(value = "peso")
    private float peso;

    /**
     * Sexo
     * */
    @JsonProperty(value = "sexo")
    private String sexo;

    /**
     * Fecha de inicio de sesión
     * */
    @JsonProperty(value = "logged_at")
    private LocalDateTime loggedAt;
}

