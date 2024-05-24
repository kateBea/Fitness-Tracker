package com.fitness.aplicacion.documentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document
public class Comida {

    @Id
    @Field()
    @JsonProperty(value = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Field(value = "nombre")
    @JsonProperty(value = "nombre")
    private String nombre;

    @Field("descripcion")
    @JsonProperty(value = "descripcion")
    private String descripcion;

    @Field(value = "calorias")
    @JsonProperty(value = "calorias")
    private float calorias; //kcal

    @Field(value = "proteinas")
    @JsonProperty(value = "proteinas")
    private float proteinas;

    @Field(value = "grasas")
    @JsonProperty(value = "grasas")
    private float grasas;

    @Field(value = "carbohidratos")
    @JsonProperty(value = "carbohidratos")
    private float carbohidratos; // gramos

    @Field(value = "vitaminas")
    @JsonProperty(value = "vitaminas")
    private List<String> vitaminas;

    @Field(value = "fecha_registro")
    @JsonProperty(value = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Field(value = "ultima_modificacion")
    @JsonProperty(value = "ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
}
