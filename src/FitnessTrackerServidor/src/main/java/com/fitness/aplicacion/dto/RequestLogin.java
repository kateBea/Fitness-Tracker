package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login request model.
 * @version 1.0
 * */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestLogin {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;
}
