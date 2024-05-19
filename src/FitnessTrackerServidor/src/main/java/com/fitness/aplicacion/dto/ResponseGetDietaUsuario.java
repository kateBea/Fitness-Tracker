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
public class ResponseGetDietaUsuario extends BaseResponse {

    @JsonProperty(value = "data")
    private ResponseGetDietaUsuarioData data;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseGetDietaUsuarioData {
        @JsonProperty(value = "id")
        private String id;

        @JsonProperty(value = "calorias_target")
        private float caloriasTarget;

        @JsonProperty(value = "fecha_inicio")
        private LocalDateTime fechaInicio;

        @JsonProperty(value = "fecha_fin")
        private LocalDateTime fechaFin;

        @JsonProperty(value = "comidas_sugeridas")
        private List<Comida> comidasSugeridas;

        @JsonProperty(value = "consumo_agua")
        private float consumoDeAgua;

        // Podemos tener registrada una dieta para un cliente,
        // pero posiblemente él o ella quiera empezarla tarde a la fecha
        // de registro
        @JsonProperty(value = "fecha_registro")
        private LocalDateTime fechaRegistro;

        @JsonProperty(value = "ultima_modificacion")
        private LocalDateTime fechaUltimaModificacion;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseGetDietaUsuarioDataComida {
        @JsonProperty(value = "id")
        private String id;

        @JsonProperty(value = "nombre")
        private String nombre;

        @JsonProperty(value = "descripcion")
        private String descripcion;

        @JsonProperty(value = "unidades")
        private int unidades;

        @JsonProperty(value = "calorias")
        private float calorias; //kcal

        @JsonProperty(value = "carbohidratos")
        private float carbohidratos; // gramos

        @JsonProperty(value = "vitaminas")
        private List<String> vitaminas;

        @JsonProperty(value = "grasas")
        private float grasas;

        @JsonProperty(value = "fecha_registro")
        private LocalDateTime fechaRegistro;

        @JsonProperty(value = "ultima_modificacion")
        private LocalDateTime fechaUltimaModificacion;
    }
}
