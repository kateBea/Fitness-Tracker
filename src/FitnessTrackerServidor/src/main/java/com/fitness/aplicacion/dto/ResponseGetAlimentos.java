package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAlimentos extends BaseResponse {

    @JsonProperty(value = "data")
    private List<GetAlimentoListItem> data;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetAlimentoListItem {

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

        @JsonProperty(value = "carbohidratos")
        private float carbohidratos; // gramos

        @JsonProperty(value = "vitaminas")
        private List<String> vitaminas;

        @JsonProperty(value = "fecha_registro")
        private LocalDateTime fechaRegistro;

        @JsonProperty(value = "ultima_modificacion")
        private LocalDateTime fechaUltimaModificacion;
    }
}
