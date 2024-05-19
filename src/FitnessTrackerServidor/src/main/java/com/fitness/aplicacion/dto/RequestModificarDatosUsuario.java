package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.documentos.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModificarDatosUsuario {
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

    // En centímetros
    @JsonProperty(value = "altura")
    private float altura;

    // En kilogramos
    @JsonProperty(value = "peso")
    private float peso;

    @JsonProperty(value = "sexo")
    private String sexo;
}
