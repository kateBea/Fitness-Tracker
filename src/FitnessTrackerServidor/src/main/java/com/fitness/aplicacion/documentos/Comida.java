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

@Document(value = "Comidas")
public class Comida {

    @Id
    @Field()
    @JsonProperty(value = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Field()
    @JsonProperty(value = "nombre")
    private String nombre;

    @Field()
    @JsonProperty(value = "descripcion")
    private String descripcion;

    @Field()
    @JsonProperty(value = "unidades")
    private int unidades;

    @Field()
    @JsonProperty(value = "calorias")
    private float calorias; //kcal

    @Field()
    @JsonProperty(value = "carbohidratos")
    private float carbohidratos; // gramos

    @Field()
    @JsonProperty(value = "vitaminas")
    private List<String> vitaminas;

    @Field()
    @JsonProperty(value = "grasas")
    private float grasas;

    @Field(value = "fecha_registro")
    @JsonProperty(value = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Field(value = "ultima_modificacion")
    @JsonProperty(value = "ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
}
