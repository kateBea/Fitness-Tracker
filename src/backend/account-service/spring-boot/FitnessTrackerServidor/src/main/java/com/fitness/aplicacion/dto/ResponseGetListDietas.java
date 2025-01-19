package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.dto.data.ResponseGetDietaUsuarioData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetListDietas extends BaseResponse<ResponseGetDietaUsuarioData> {

    @JsonProperty(value = "dietas")
    private List<ResponseGetDietaUsuarioData> dietas;
}
