package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestModificarDieta {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "dieta_id")
    private String dietaId;

    @JsonProperty(value = "calorias_target")
    private float caloriasTarget;

    @JsonProperty(value = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @JsonProperty(value = "fecha_fin")
    private LocalDateTime fechaFin;

    // Las comidas no se modifican a través de los cambios sobre la dieta
    // modificar una comida implica una funcionalidad a parte y sería adecuado
    // que tuviese sus propios endpoints, de momento este es el soporte
    // que ofrece nuestra aplicación, si es necesario actualizar una comida en concreto
    // se hará a través de el registro de una dieta, registrando la comida si no existe
    // y modificándola con los datos nuevos en caso de existir.
    //@JsonProperty(value = "id_comidas_sugeridas")
    //private List<String> comidasSugeridas;

    @JsonProperty(value = "consumo_agua")
    private float consumoDeAgua;

    @JsonProperty(value = "activa")
    private boolean activa;
}
