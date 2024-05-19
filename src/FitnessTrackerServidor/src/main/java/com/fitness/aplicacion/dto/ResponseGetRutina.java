package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.documentos.Alimento;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseGetRutina extends BaseResponse {

    @JsonProperty(value = "data")
    private ResponseGetRutinaData data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseGetRutinaData {
        @JsonProperty(value = "id")
        private String id;

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

        @JsonProperty(value = "presion_arterial")
        private float presionArterial;

        @JsonProperty(value = "fecha_seguimiento")
        private LocalDateTime fechaSeguimiento;

        @JsonProperty(value = "ultima_modificacion")
        private LocalDateTime fechaUltimaModificacion;

        @JsonProperty(value = "comidas_consumidas")
        private List<RequestModificarRutina.AlimentoInfo> comidasConsumidas;
    }
}
