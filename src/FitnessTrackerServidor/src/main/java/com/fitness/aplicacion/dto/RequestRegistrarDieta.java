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

    @JsonProperty(value = "comidas_sugeridas")
    private List<ComidaSugeridaData> comidasSugeridas;

    @JsonProperty(value = "activa")
    private boolean activa;

    @JsonProperty(value = "consumo_agua")
    private float consumoDeAgua;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComidaSugeridaData {
        @JsonProperty(value = "id")
        @EqualsAndHashCode.Include
        private String id;

        @JsonProperty(value = "orden")
        private String orden;

        @JsonProperty(value = "tipo")
        private String tipo;

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

        @JsonProperty(value = "carbohidratos")
        private float carbohidratos; // gramos

        @JsonProperty(value = "vitaminas")
        private List<String> vitaminas;
    }
}
