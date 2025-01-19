package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModificarDieta {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "dieta_id")
    private String dietaId;

    @JsonProperty(value = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @JsonProperty(value = "fecha_fin")
    private LocalDateTime fechaFin;

    @JsonProperty(value = "activa")
    private boolean activa;
}
