package com.fitness.aplicacion.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.dto.RequestModificarRutina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetRutinaData {
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

    @JsonProperty(value = "fecha_seguimiento")
    private LocalDate fechaSeguimiento;

    @JsonProperty(value = "ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;

    @JsonProperty(value = "comidas_consumidas")
    private List<RequestModificarRutina.AlimentoInfo> comidasConsumidas;
}