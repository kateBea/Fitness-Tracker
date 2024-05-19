package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseGetListRutinas extends BaseResponse {

    @JsonProperty(value = "data")
    private List<ResponseGetRutina.ResponseGetRutinaData> data;

}
