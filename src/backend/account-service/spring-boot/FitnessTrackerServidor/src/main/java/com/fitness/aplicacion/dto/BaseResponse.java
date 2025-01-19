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
    private List<String> errors = Collections.emptyList();

    /**
     * Estado
     * */
    @Builder.Default
    @JsonProperty(value = "status")
    private int status = HttpStatus.OK.value();

    /**
     * Título respuesta
     * */
    @Builder.Default
    @JsonProperty(value = "title")
    private String title = HttpStatus.OK.getReasonPhrase();

    /**
     * Datos
     * */
    @Builder.Default
    @JsonProperty(value = "data")
    private T data = null;

    /**
     * Configura la respuesta con código de estado OK.
     * @param message Mensaje de respuesta
     * @param data Cuerpo de la respuesta
     * */
    public void setupOk(String message, T data) {
        this.responseDescription = message;
        this.success = true;
        this.errors = Collections.emptyList();

        this.status = HttpStatus.OK.value();
        this.title = HttpStatus.OK.getReasonPhrase();

        this.data = data;
    }

    /**
     * Configura la respuesta con código de estado BAD_REQUEST.
     * @param message Mensaje de respuesta
     * @param errors Lista de errores
     * */
    public void setupBadRequest(String message, List<String> errors) {
        this.responseDescription = message;
        this.success = false;
        this.errors = errors == null ? Collections.emptyList() : errors;

        this.status = HttpStatus.BAD_REQUEST.value();
        this.title = HttpStatus.BAD_REQUEST.getReasonPhrase();

        this.data = null;
    }

    /**
     * Configura la respuesta con código de estado INTERNAL_SERVER_ERROR.
     * @param message Mensaje de respuesta
     * @param errors Lista de errores
     * */
    public void setupInternalError(String message, List<String> errors) {
        this.responseDescription = message;
        this.success = false;
        this.errors = errors == null ? Collections.emptyList() : errors;

        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.title = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

        this.data = null;
    }
}
