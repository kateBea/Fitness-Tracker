package com.fitness.aplicacion.documentos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document
public class Dieta {

    @Id
    @Field()
    @JsonProperty(value = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Field(value = "calorias_target")
    @JsonProperty(value = "calorias_target")
    private float caloriasTarget;

    @Field(value = "fecha_inicio")
    @JsonProperty(value = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Field(value = "fecha_fin")
    @JsonProperty(value = "fecha_fin")
    private LocalDateTime fechaFin;

    @Field(value = "comidas_sugeridas")
    @JsonProperty(value = "comidas_sugeridas")
    private List<Comida> comidasSugeridas;

    @Field(value = "consumo_agua")
    @JsonProperty(value = "consumo_agua")
    private float consumoDeAgua;

    // Podemos tener registrada una dieta para un cliente,
    // pero posiblemente él o ella quiera empezarla tarde a la fecha
    // de registro
    @Field(value = "fecha_registro")
    @JsonProperty(value = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Field(value = "ultima_modificacion")
    @JsonProperty(value = "ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
}
