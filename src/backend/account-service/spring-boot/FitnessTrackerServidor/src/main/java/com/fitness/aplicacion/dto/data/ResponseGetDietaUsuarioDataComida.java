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
public class ResponseGetDietaUsuarioDataComida {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "nombre")
    private String nombre;

    @JsonProperty(value = "descripcion")
    private String descripcion;

    @JsonProperty(value = "calorias")
    private float calorias; //kcal

    @JsonProperty(value = "proteinas")
    private float proteinas;

    @JsonProperty(value = "grasas")
    private float grasas;

    @JsonProperty(value = "tipo")
    private String tipo;

    @JsonProperty(value = "orden")
    private String orden;

    @JsonProperty(value = "carbohidratos")
    private float carbohidratos; // gramos

    @JsonProperty(value = "vitaminas")
    private List<String> vitaminas;

    @JsonProperty(value = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
