package com.fitness.aplicacion.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetDietaUsuarioData {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "calorias_target")
    private float caloriasTarget;

    @JsonProperty(value = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @JsonProperty(value = "fecha_fin")
    private LocalDateTime fechaFin;

    @JsonProperty(value = "comidas_sugeridas")
    private List<ResponseGetDietaUsuarioDataComida> comidasSugeridasResult;

    @JsonProperty(value = "activa")
    private boolean activa;

    @JsonProperty(value = "consumo_agua")
    private float consumoDeAgua;

    @JsonProperty(value = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @JsonProperty(value = "ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
}
