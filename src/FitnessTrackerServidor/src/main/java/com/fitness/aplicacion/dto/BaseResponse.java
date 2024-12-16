package com.fitness.aplicacion.dto;


import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * Modelo de respuesta para Genérico
 * @version 1.0
 * */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class BaseResponse<T> {

    /**
     *Descripción respuesta
     * */
    @Builder.Default
    @JsonProperty(value = "detail")
    private String responseDescription = "Operación exitosa";

    /**
     * Operación exitosa o no
     * */
    @Builder.Default
    @JsonProperty(value = "success")
    private boolean success = true;

    /**
     * Listado de errores
     * */
    @Builder.Default
    @JsonProperty(value = "errors")
    private List<String> erros = Collections.emptyList();

    /**
     * Título respuesta
     * */
    @Builder.Default
    @JsonProperty(value = "title")
    private String title = HttpStatus.OK.getReasonPhrase();

    /**
     * Estado
     * */
    @Builder.Default
    @JsonProperty(value = "status")
    private int status = HttpStatus.OK.value();

    /**
     * Datos
     * */
    @Builder.Default
    @JsonProperty(value = "data")
    private T data = null;
}
