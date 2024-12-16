package com.fitness.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fitness.aplicacion.dto.data.GetAlimentoListData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAlimentos extends BaseResponse<GetAlimentoListData> {

    @JsonProperty(value = "alimentos")
    private List<GetAlimentoListData> alimentos;

}
