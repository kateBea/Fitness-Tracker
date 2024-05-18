package com.fitness.aplicacion.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class BaseResponse {

    @JsonProperty(value = "response_description")
    private String responseDescription;

    @JsonProperty(value = "success")
    private boolean success;

}
