package com.fitness.aplicacion.documentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document
public class Rutina {

    @Id
    @Field()
    @JsonProperty(value = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Field(value = "tiempo_suenio")
    @JsonProperty(value = "tiempo_suenio")
    private float tiempoDeSuenio;

    @Field(value = "calorias_quemadas")
    @JsonProperty(value = "calorias_quemadas")
    private float caloriasQuemadas;

    @Field(value = "pasos_realizados")
    @JsonProperty(value = "pasos_realizados")
    private int pasosRealizados;

    @Field(value = "frecuencia_cardiaca")
    @JsonProperty(value = "frecuencia_cardiaca")
    private float frecuenciaCardiaca;

    @Field(value = "nivel_oxigeno_sangre")
    @JsonProperty(value = "nivel_oxigeno_sangre")
    private float nivelOxigenoSangre;

    @Field(value = "presion_arterial")
    @JsonProperty(value = "presion_arterial")
    private float presionArterial;

    @Field(value = "fecha_seguimiento")
    @JsonProperty(value = "fecha_seguimiento")
    private LocalDateTime fechaSeguimiento;

    @Field(value = "comidas_consumidas")
    @JsonProperty(value = "comidas_consumidas")
    private List<Alimento> comidasConsumidas;
}
