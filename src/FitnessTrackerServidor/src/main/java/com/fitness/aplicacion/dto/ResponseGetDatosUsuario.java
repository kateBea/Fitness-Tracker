package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.documentos.Sexo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseGetDatosUsuario extends BaseResponse {
    @JsonProperty(value = "data")
    private ResponseGetDatosUsuarioData data;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseGetDatosUsuarioData {
        @JsonProperty(value = "correo_electronico")
        private String email;

        @Field(value = "nombre_usuario")
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
        private LocalDate fechaRegistro;

        // En centímetros
        @JsonProperty(value = "altura")
        private float altura;

        // En kilogramos
        @JsonProperty(value = "peso")
        private float peso;

        @JsonProperty(value = "sexo")
        private Sexo sexo;
    }
}
