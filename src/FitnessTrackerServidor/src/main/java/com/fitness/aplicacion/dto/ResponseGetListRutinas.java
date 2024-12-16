package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.dto.data.ResponseGetRutinaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseGetListRutinas extends BaseResponse<ResponseGetRutinaData> {

    @JsonProperty(value = "rutinas")
    private List<ResponseGetRutinaData> rutinas;

}
