package com.fitness.aplicacion.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetDatosUsuarioData {
    @JsonProperty(value = "correo_electronico")
    private String email;

    @JsonProperty(value = "nombre_usuario")
    private String nombreUsuario;

    @JsonProperty(value = "nombre")
    private String nombre;

    @JsonProperty(value = "primer_apellido")
    private String primerApellido;

    @JsonProperty(value = "segundo_apellido")
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
}
