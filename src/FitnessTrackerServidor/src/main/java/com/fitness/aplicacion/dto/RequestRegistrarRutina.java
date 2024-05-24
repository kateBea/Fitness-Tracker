package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.documentos.Alimento;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestRegistrarRutina {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "tiempo_suenio")
    private float tiempoDeSuenio;

    @JsonProperty(value = "calorias_quemadas")
    private float caloriasQuemadas;

    @JsonProperty(value = "pasos_realizados")
    private int pasosRealizados;

    @JsonProperty(value = "frecuencia_cardiaca")
    private float frecuenciaCardiaca;

    @JsonProperty(value = "nivel_oxigeno_sangre")
    private float nivelOxigenoSangre;
}
