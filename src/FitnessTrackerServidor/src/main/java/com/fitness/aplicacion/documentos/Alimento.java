package com.fitness.aplicacion.documentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Modelo que representa un alimento.
 *
 * @version 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document
public class Alimento {

    @Id
    @Field()
    @JsonProperty(value = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Field()
    @JsonProperty(value = "comida")
    private Comida comida;

    @Field()
    @JsonProperty(value = "tipo")
    private Tipo tipo;

    @Field(name = "hora_consumo")
    @JsonProperty(value = "hora_consumo")
    private LocalDateTime horaConsumo;

    @Field()
    @JsonProperty(value = "orden")
    private Orden orden;

    @Field(value = "fecha_registro")
    @JsonProperty(value = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Field(value = "ultima_modificacion")
    @JsonProperty(value = "ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
}
