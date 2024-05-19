package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.documentos.Comida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class RequestRegistrarDieta {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "calorias_target")
    private float caloriasTarget;

    @JsonProperty(value = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @JsonProperty(value = "fecha_fin")
    private LocalDateTime fechaFin;

    @JsonProperty(value = "id_comidas_sugeridas")
    private List<String> comidasSugeridas;

    @JsonProperty(value = "consumo_agua")
    private float consumoDeAgua;
}
