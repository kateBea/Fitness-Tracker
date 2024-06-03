package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.documentos.Orden;
import com.fitness.aplicacion.documentos.Tipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestModificarRutina {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "rutina_id")
    private String rutinaId;

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

    @JsonProperty(value = "alimentos")
    private List<AlimentoInfo> alimentoInfos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AlimentoInfo {
        @JsonProperty(value = "comida_id")
        private String comidaId;

        @JsonProperty(value = "nombre")
        private String nombre;

        @JsonProperty(value = "descripcion")
        private String descripcion;

        @JsonProperty(value = "tipo")
        private String tipo;

        @JsonProperty(value = "hora_consumo")
        private LocalDateTime horaConsumo;

        @JsonProperty(value = "orden")
        private String orden;

        @JsonProperty(value = "calorias")
        private float calorias;

        @JsonProperty(value = "proteinas")
        private float proteinas;

        @JsonProperty(value = "grasas")
        private float grasas;

        @JsonProperty(value = "carbohidratos")
        private float carbohidratos;

        @JsonProperty(value = "vitaminas")
        private List<String> vitaminas;
    }
}
